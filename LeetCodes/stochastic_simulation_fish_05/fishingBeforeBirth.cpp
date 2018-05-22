#include <iostream>
#include <fstream>
#include <iomanip>
#include <ctime>
#include <cstdlib>
#include <cmath>
#include "mtrand_restartable.h"

#define RANMAX 4294967296
#define MAXFISH 10000
#define DAYSPERYEAR 365
#define AVERAGEFISHPERBIRTH 3
#define ANNUALBIRTHRATE 0.2
#define MAXFISHAGE 13
#define TRIAL 1500
#define NUM_YEARS 100

const int& startBirth    = 91;
const int& endBirth      = 120;
const int& startFishing  = 31;
const int& endFishing    = 60;
const int& INIT_FISH_POP    = 400;
const double& FISHING_RATE  = 0.08;
const std::string& OUT_FILE = "fishingBeforeBirth.csv";
const char *INIT_SEED       = "0"; 


MTRand_int32 irand;

// non-inline function definitions and static member definitions cannot
// reside in header file because of the risk of multiple declarations

// initialization of static private members
unsigned long MTRand_int32::state[n] = {0x0UL};
int MTRand_int32::p     = 0;
bool MTRand_int32::init = false;

void MTRand_int32::gen_state() {  // generate new state vector
    for (int i = 0; i < (n - m); ++i)
        state[i] = state[i + m] ^ twiddle(state[i], state[i + 1]);
    for (int i = n - m; i < (n - 1); ++i)
        state[i] = state[i + m - n] ^ twiddle(state[i], state[i + 1]);
    state[n - 1] = state[m - 1] ^ twiddle(state[n - 1], state[0]);
    p = 0;  // reset position
}

void MTRand_int32::seed(unsigned long s) {  // init by 32 bit seed
    state[0] = s & 0xFFFFFFFFUL;  // for > 32 bit machines
    for (int i = 1; i < n; ++i) {
        state[i] = 1812433253UL * (state[i - 1] ^ (state[i - 1] >> 30)) + i;
        // see Knuth TAOCP Vol2. 3rd Ed. P.106 for multiplier
        // in the previous versions, MSBs of the seed affect only MSBs 
        // of the array state 2002/01/09 modified by Makoto Matsumoto
        state[i] &= 0xFFFFFFFFUL; // for > 32 bit machines
    }
    p = n;  // force gen_state() to be called for next random number
}

double generateGaussianNoise(const double& mean, const double &stdDev) {
    static bool hasSpare = false;
    static double spare;
    double r;
 
    if(hasSpare) {
        hasSpare = false;
        return mean + stdDev * spare;
    }
 
    hasSpare = true;
    static double u, v, s;
    do {
        r = ( (double) irand()) / (double)(RANMAX);
        u = r * 2.0 - 1.0;
        r = ( (double) irand()) / (double)(RANMAX);
        v = r * 2.0 - 1.0;

        s = u * u + v * v;
    } while((s >= 1.0) || (s == 0.0));
 
    s = sqrt(-2.0 * log(s) / s);
    spare = v * s;
    return mean + stdDev * u * s;
}

void write_in_file(const std::string& filePath, 
                    double fishOut[TRIAL][NUM_YEARS]) {
    std::ofstream outFile(filePath);
    // Wring the result in a file.
    // each trial is written in a file in one row
    for (size_t numberOfTrial = 0; numberOfTrial < TRIAL; ++numberOfTrial)
        for (size_t year = 0; year < NUM_YEARS; year++)
            outFile << fishOut[numberOfTrial][year] 
                    << (((year + 1)< NUM_YEARS)?",":"\n");
    outFile.close();
}
  
int main (int argc, char* argv[]) {
    unsigned long seed;
    size_t trial = TRIAL;
    double fishOut[TRIAL][NUM_YEARS];
    int numYears,year,day,newNumberOfFish;
    double annualFishingRate,dailyFishingRate;
    double r,dailyFishingProbability[DAYSPERYEAR];
    double dailyBirthRate,dailyBirthProbability[DAYSPERYEAR];
    double dyingProbability[MAXFISHAGE],pr,dailyAgeIncrement;
    int numberOfFish,fish,fishBorn,fishCount,fishCaught,fishDied,numberOfFish0;
    int newFish,fishTemp,p;
    
    struct fishStruct {
        char gender;
        double age;
        bool isAlive;
    } fishPopulation[MAXFISH];
 
    // Number of years to run
    numYears            = NUM_YEARS;
    // Annual fishing rate
    annualFishingRate   = FISHING_RATE;
    // Initial number of fish
    numberOfFish        = INIT_FISH_POP;
    // Random number seed
    seed                = strtoul(INIT_SEED, NULL, 10);
    irand.seed(seed);
    // Making a filePath for saving the output
    const std::string& filePath = OUT_FILE;
    
    dailyAgeIncrement   = 1.0 / ((double) DAYSPERYEAR);
    for (fish = 0; fish < numberOfFish; fish++) {
        // The fish is alive to start out
        fishPopulation[fish].isAlive = true;

        // Assign the gender randomly
        r = ((double) irand()) / (double)(RANMAX);
        if ( r > 0.5)
            fishPopulation[fish].gender = 'M';
        else
            fishPopulation[fish].gender = 'F';

        fishPopulation[fish].age = -1;
        while (fishPopulation[fish].age < 0)
            fishPopulation[fish].age = generateGaussianNoise(5.0, 1.5);
    }
    
    dailyFishingRate    = exp(log(1.0 + annualFishingRate) / 
                            ((double)(endFishing - startFishing))) - 1.0;
    dailyBirthRate      = exp(log(1.0 + ANNUALBIRTHRATE) / 
                            ((double)(endBirth - startBirth))) - 1.0;

    for (day = 0; day < DAYSPERYEAR; day++) {
        if (day >= startFishing && day <= endFishing)
            dailyFishingProbability[day] = dailyFishingRate;
        else
            dailyFishingProbability[day] = 0;
        if (day >= startBirth && day <= endBirth)
            dailyBirthProbability[day]  = dailyBirthRate;
        else
            dailyBirthProbability[day]  = 0;
    }

    dyingProbability[0] = 0.000001175;
    dyingProbability[1] = 0.000010474;
    dyingProbability[2] = 0.000061633;
    dyingProbability[3] = 0.000239175;
    dyingProbability[4] = 0.000617000;
    dyingProbability[5] = 0.001111481;
    dyingProbability[6] = 0.001530459;
    dyingProbability[7] = 0.001772717;
    dyingProbability[8] = 0.001869435;
    dyingProbability[9] = 0.001895576;
    dyingProbability[10] = 0.001900249;
    dyingProbability[11] = 0.001900794;
    dyingProbability[12] = 1.000000000;

    for (size_t numberOfTrial = 0; numberOfTrial < trial; ++numberOfTrial) {
        numberOfFish    = INIT_FISH_POP;
        fishCount       = numberOfFish;
        for (year = 0; year < numYears; year++) {
            fishBorn    = 0;
            fishCaught  = 0;
            fishDied    = 0;

            for (day = 0; day < DAYSPERYEAR; day++) {
                numberOfFish0 = numberOfFish;
                for (fish = 0; fish < numberOfFish0; fish++) {
                
                    if (fishPopulation[fish].gender == 'F') {
                        pr = dailyBirthProbability[day] * 
                                (1.0 - 0.0001 * fishCount);
                        r = ((double) irand()) / (double)(RANMAX);
                        if (r < pr) {
                            newFish = (int) trunc
                              (generateGaussianNoise(AVERAGEFISHPERBIRTH, 1.5))
                                    + 1;
                            if (newFish > 0) {
                                fishBorn += newFish;
                                for (fishTemp = 0; fishTemp < newFish; 
                                                                fishTemp++) {
                                    fishPopulation[numberOfFish].isAlive = true;
                                    fishPopulation[numberOfFish].age     = 0.0;
                                    r = ((double) irand()) / (double)(RANMAX);
                                    if (r > 0.5)
                                        fishPopulation[numberOfFish].gender 
                                                                        = 'M';
                                    else
                                        fishPopulation[numberOfFish].gender 
                                                                        = 'F';
                                    numberOfFish++;
                                }
                            }
                            fishCount += newFish;
                        }
                    }
                
                    r = ((double) irand()) / (double)(RANMAX);
                    if (r < dailyFishingProbability[day]) {
                        fishPopulation[fish].isAlive = false;
                        fishCount--;
                        fishCaught++;
                    } else {
                        p = (int) trunc(fishPopulation[fish].age);
                        r = ((double) irand())/(double)(RANMAX);
                        if (r < dyingProbability[p] ) {
                            fishPopulation[fish].isAlive = false;
                            fishCount--;
                            fishDied++;
                        }
                    }    
                }

                newNumberOfFish = 0;
                for (fish = 0; fish < numberOfFish; fish++) {
                    if (fishPopulation[fish].isAlive) {
                        fishPopulation[newNumberOfFish].isAlive = true;
                        fishPopulation[newNumberOfFish].gender  = 
                            fishPopulation[fish].gender;
                        fishPopulation[newNumberOfFish].age     = 
                            fishPopulation[fish].age + dailyAgeIncrement;
                        newNumberOfFish++;
                    }
                }
                numberOfFish = newNumberOfFish;
            }
            fishOut[numberOfTrial][year] = fishCount;
        }
        std::cout << "Trial number: " << numberOfTrial + 1 << std::endl;
    }
    // ================ Writing the result in a file =====================
    write_in_file(filePath, fishOut);
    
    return 0;
}

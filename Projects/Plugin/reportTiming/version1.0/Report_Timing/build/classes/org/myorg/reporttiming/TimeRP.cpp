/* 
 * -------------------------------------------------------------------------
 *               Copyright (c) on October 14, 2017, 4:59 PM
 * 
 *                          Timings Report class
 * 
 * This file was Designed to be used as a Plug-in in NetBeans IDE. This class 
 * itself was Designed using NetBeans IDE 8.2 in the following conditions:
 *            Component           |           Details
 * -------------------------------|-----------------------------------------
 *            CPU Model           |     Intel(R) Xeon(R) CPU X5550 @ 2.67GHz
 *                                |
 *           CPU/Core Speed       |     2661.000 Mhz
 *                                |
 *      Main Memory (RAM) size    |     24591648 kB
 *                                |
 *      Operating system used     |     Linux mualhpcp01.hpc.muohio.edu 
 *                                |    2.6.32- 642.el6.x86_64 #1 SMP Tue Oct 14
 *                                |    17:27:01 UTC2016 x86_64 x86_64 x86_64 
 *                                |     GNU/Linux
 *                                |
 *  Name&version of C++ compiler  |     GCC 4.9.2
 *                                |
 *
 * 
 * File:   TimeRP.cpp
 * 
 * Author: Meisam Amajd         amjadm@miamioh.edu
 * -------------------------------------------------------------------------
 */

#include <iostream>
#include <fstream>        // std::ifstream
#include <iterator>       // std::ostream_iterator
#include <string>         // std::string
#include <algorithm>      // accumulate, for-each, copy, etc.
#include <unordered_map>  // std::unordered_map
#include <type_traits>    // std::is_same
#include <utility>        // std::pair
#include <vector>         // std::vector
#include <memory>         // std::unique_ptr
#include <numeric>

/**
  \brief This class designed to read a report file that produced only by
         Linux command "/usr/bin/time -v" to produce a table report as below.
  
  The produced report by this class contains the details as following:
        
        Name of file/s : .....
     ---------------------------------------------------
    |    command      |              ...                |
    |  table number   |       ....                      |
    |-----------------|---------------------------------|
    |  CPU Percentage |              ...                |
    |-----------------|---------------------------------|
    |     Average     |              ...                |
    |-----------------|---------------------------------|
    |    User times   |              ...                |
    |-----------------|---------------------------------|
    |     Average     |              ...                |
    |-----------------|---------------------------------|
    |   System Times  |              ...                |
    |-----------------|---------------------------------|
    |     Average     |              ...                |
    |-----------------|---------------------------------|
    |  Elapsed Times  |              ...                |
    |-----------------|---------------------------------|
    |     Average     |              ...                |
    |-----------------|---------------------------------|
    |   Peak-memory   |              ...                |
    |-----------------|---------------------------------|
    |     Average     |              ...                |
    |-----------------|---------------------------------|
    |     95% CI      |              ...                |
     ---------------------------------------------------
  The number of tables depends on the number of commands that have been used 
  for producing a report file. i.e. if two different commands have been used,
  for instance: /user/bin/time -v ./filename X 
                /user/bin/time -v ./filename Y
  Two tables will be produced, one for the command => ./filename X, and one for 
  the command => ./filename Y. Also if one command repeated a couple of times,
  all details will be showing up in one table. i.e. if there are multiple 
  commands as below:
  /user/bin/time -v ./filename X
  /user/bin/time -v ./filename X 
  /user/bin/time -v ./filename X
  all timing details are put in one table. (because all commands are the same)  
  This class also provides calculating speed up and percentage based on the 
  user preference, that is the user gets to choose between which commands the 
  calculation should be occurred.
  The report will be accessible on the screen also because the operator <<  
  was overloaded, the programmer has an ability to save the report in a file. >
  Also, this class provides three different output format as a file: TXT, HTML,
  and CVS documents.  
  In addition, there is ability to add more report files to the current 
  data by which you can calculate the result based on different report files
  and calculations from different experiences.
 */
class TimeRP;

/** 
  \brief A Helper class for holding necessary details from a report file. 
 
  This class designed to hold only value of the following lines:
    1- "User time (seconds):"
    2- "System time (seconds):"
    3- "Percent of CPU this job got:"
    4- "Elapsed (wall clock) time (h:mm:ss or m:ss):"
    5- "Maximum resident set size (kbytes):"
  Plus, this class keeps the average of all those values above,
  and the value of 95% CI.
 */
class fDetails{
public:
    fDetails() {};                  // Constructor.
    std::vector<unsigned> cpuPrcts;  // Holds all CPU-percentages.
    std::vector<double> userT;      // Holds all User-times.
    std::vector<double> systemT;    // Holds all system-times.
    std::vector<double> times;      // Holds all elapsed-times.
    std::vector<unsigned> peakMems;  // Holds all peak-memories.
    double avgCPU     = 0.0;        // Holds the average value of cpuPrcts.
    double avgUserT   = 0.0;        // Holds the average value of user-times.
    double avgSysT    = 0.0;        // Holds the average value of system-times.
    double avgTime    = 0.0;        // Holds the average value of elapsed-times.
    double avgPeakMem = 0.0;        // Holds the average value of peak-memories.
    double CI         = 0.0;        // Holds the 95% CI value.
    int index         = 0;          // Holds the number of table.
};

using str = std::string;            // Defining an alias for std::string.
using DataMap = std::unordered_map <str, fDetails>;  // An alias.

/** 
  \brief Prototype pattern for having the different output documents.
         The clone method in this class needs to be overloaded for having a 
         desire output as one of the following formats:
            1. TXT-Document.
            2. HTML-Document.
            3. CVS-Document.
 */
class Document {    
public:
    virtual std::ostream& clone(std::ostream& os, TimeRP& F) {return os;}
};

class TimeRP {    
public:
    /**
     * \brief Default Constructor.
     */
    inline 
    TimeRP() {};
    
    /**
     * \brief Constructor.
     * 
     * \param other TimeRP object.
     */
    inline 
    TimeRP(const TimeRP& other): data(other.data), 
                                 fileName(other.fileName),
                                 reportType(other.reportType) {}
    
    /**
     * \brief Loads the given file name. Reads line by line of the file and only 
     *        keeps the necessary data from the file.
     * 
     * \param fileName A file that all data should be read from it.
     * \param Optional choice which is the report Type from 0 to 2 txtDoc, 
     *        HTMLDoc, and spreedsheetDoc respectively.
     *        By default it would be zero or txtDoc.
     */
    inline 
    TimeRP(const str& fileName, const size_t& choice = 0) { 
        loadF(fileName, choice); 
    }
    
    /**
     * \return The list of files names that have been read.
     */
    inline
    str getFile() const {return fileName;}
    
    /**
     * \return All data.
     */
    inline
    DataMap getData() const {return data;}
    
    /**
     * \return the current reportType.
     */
    inline
    size_t getReportType() const {return reportType;}
    
    /**
     * \brief Sets a new value as a reportType.
     *          0. TXT
     *          1. HTML
     *          2. SpreedSheet
     */
    inline
    void setReportType(const size_t& number) {this->reportType = number;}
    
    /**
     * \brief This overloaded operator, adds data from another TimeRP object
     *        to the current data (*this) and returns a new TimeRP object 
     *        containing data from *this and other.
     * 
     * Notice neither *this nor other will be changed at the end.
     * 
     * \param other TimeRP object.
     * 
     * \return A new TimeRP object containing data from *this and other.
     */
    TimeRP 
    operator+(const TimeRP& other) const {
        TimeRP out(*this);  // Make a copy of *this.
        // First, adding the filename.
        out.fileName += other.fileName;
        // Loops through all elements in other object.
        for (auto elm = other.data.cbegin(); 
                 (elm != other.data.cend()); elm++) {
            // Adds each element(elm) into the out.data.
            // If the elm is new in out.data, then .second returns true.
            // Otherwise .second returns false meaning there is duplicates
            // which in this case it just adds data elements to that command.
            if (!out.data.insert(*elm).second) {  // true means duplication.
                std::copy(elm->second.userT.cbegin(), elm->second.userT.cend(),
                          std::back_inserter(out.data[elm->first].userT));
                std::copy(elm->second.systemT.cbegin(), elm->second.systemT.
                    cend(), std::back_inserter(out.data[elm->first].systemT));
                std::copy(elm->second.cpuPrcts.cbegin(), elm->second.cpuPrcts.
                    cend(), std::back_inserter(out.data[elm->first].cpuPrcts));
                std::copy(elm->second.times.cbegin(), elm->second.times.cend(),
                          std::back_inserter(out.data[elm->first].times));
                std::copy(elm->second.peakMems.cbegin(), elm->second.peakMems.
                    cend(), std::back_inserter(out.data[elm->first].peakMems));
            }
        }
        return out;
    }
    
    /**
     * \brief Produces a table report and return it as a reference to the 
     *        given out stream.
     * 
     * \param out reference to the output.
     * 
     * \return out.
     */
    std::ostream&
    report(std::ostream& out) {
        size_t index = 1;  // For producing indeces for each table.
        for (auto element = data.begin(); (element != data.end()); element++)
            calculate(element->second, index);
        return out << (*this);  // the operator overridden.
    }
    
    /**
     * \brief Calculates the speed up and percentage from data with user 
     *        preference, that is user can choose two table numbers and 
     *        calculation is happened between elements of those two tables.
     * \return out The Result would be returned by the given std::ostream.
     * 
     * \param out A reference to the output.
     * \param nT1 A first table number.
     * \param nT2 A second table number.
     */
    void 
    spd_prc(std::ostream& out, int tableNum1, int tableNum2) const {
        // For finding AvgTimes of the given table numbers.
        double t1 = getAvgTime(tableNum1),
               t2 = getAvgTime(tableNum2);  
        double speedUp, percentage;
        // Swapping to have tabnleNum1 and t1 holding bigger values.
        if (t1 < t2) {  
            std::swap(t1, t2);
            std::swap(tableNum1, tableNum2);
        }
        if (t2 == 0) {  // In case either tN2, tN1 not Found/preventing infinity
            out << "Illegal Calculation. Please try another tables.\n";
            return;
        }
        percentage = (t1 - t2) * 100 / t2;  // Calculating percentage.
        speedUp    = t1 / t2;               // Calculating Speed-Up
        out << "The Runtime in table " << tableNum1 << " has a speed up ~" 
           << speedUp  << "X (~" << percentage << "%) over the table " 
           << tableNum2 << ".\n";
    }
    
    /**
     * \brief This method gets a command from the input and runs it on a shell
     *        and shows the result.
     * \param val command
     */
    void
    runCommand(str cmd) const {
        str ch;
        // The following loop is to make sure the whole line have been passed in
        // as a command (cmd) and it will read the rest of the line. 
        while (std::cin.peek() != '\n') {  
            std::cin >> ch;
            cmd += str(' ' + ch);
        }
        system(cmd.c_str());  // Turn the cmd into the char* and runs it.
    }
    
    
private:
    /*
     * \brief Holds all data.
     */
    DataMap data;
    
    /*
     * \brief Holds fileName/s.
     */
    str fileName;
    
    /* \brief Determines the type of the report.
     *        By default it would be txtDoc.
     */
    size_t reportType = 0;
    
    /*
     * \brief Document for the report output.
     */
    Document* doc;
    
    void 
    loadF(const str& fileName, const size_t& choice) {
        std::ifstream is(fileName);
        isFileValid(is);  // Checks if the file is properly opened.
        this->reportType = choice;
        readFile(fileName, is);
    }
    
    /*
     * \brief Reads times in two following formats and convert to seconds.
     *              1- H:M:S
     *              2- M:S.ms
     * 
     * \param is std::ifstream$ format
     *
     * \return a double representing the total seconds.
     */
    double
    getSeconds(std::ifstream& is) {
        unsigned H, M, S; char seperator;
        is >> H >> seperator >> M >> seperator >> S;
        return (seperator == '.')? H * 60.0 + M + S * 0.01  // 0:12.06,02:05.10
                                 : H * 3600.0 + M * 60.0 + S;  // 4:12:34
    }  
    
    /*
     * A helper method for getting a proper value from the is and return it.
     * @param is std::ifstream&
     * @param n Number of chars that will be ignored.
     * @return T Proper value.
     */
    template<typename T> T 
    get(std::ifstream& is, const size_t& numOfSkips) {
        T temp;
        is.ignore(numOfSkips);  // Skipping the characters -> numOfSkips times.
        // If type is unsigned or numOfSkips == 17 (for User or System)
        if ((std::is_same<T, unsigned>::value) || (numOfSkips == 17)) {
            is >> temp;  // for cpu%, peak-memory,UserTime, or SystemTime.
        } else if (std::is_same<T, double>::value) {  // if T is double.
            temp = getSeconds(is);  // only for reading elapse times in second.
        }
        return temp;
    }
    
    /*
     * \brief A helper method that calculates average of all item vectors
     *        inside the given vector.
     * \param vec
     */
    template<class InputIt> 
    void
    calculate(InputIt& vec, size_t& index) {
        vec.avgCPU     = avg(vec.cpuPrcts);
        vec.avgUserT   = avg(vec.userT);
        vec.avgSysT    = avg(vec.systemT);
        vec.avgTime    = avg(vec.times);
        vec.avgPeakMem = avg(vec.peakMems);
        vec.CI         = cI(vec.times, vec.avgTime);
        vec.index      = index++;
    }
    
    /*
     * \brief A helper method that receives a vector and calculates the average 
     *        of all values inside that vector.
     * 
     * \param vec
     * 
     * \return an average of all values inside the given vector.
     */
    template<class InputVec> 
    double 
    avg(InputVec& vec) const {
        double sum = std::accumulate(vec.begin(), vec.end(), 0.0);
        return ((vec.empty())? 0: (sum / vec.size()));
    }
    
    /*
     * \brief Having the given vector of times it calculates 95% CI.
     * 
     * \param times vector<double> containing all times.
     * \param average double average value of all given times.
     * 
     * \return double value representing CI.
     */
    double 
    cI(const std::vector<double>& times, const double& average) {
        double sumSquares = 0.0;    // Initializing.
        const double& t   = 2.776;  // This number is specifically for 5 timings
        const size_t& N   = times.size();
        std::for_each(times.cbegin(), times.cend(),
        [&](double time){return sumSquares += std::pow((average - time), 2);});
        double stanDev = std::sqrt(sumSquares / N);  // Standard Deviation.
        return (t * stanDev) / std::sqrt(N);         // Returns CI.
    }
    
    /*
     * \brief A helper method that return a average time value the given number
     *        of table from the data map.
     * 
     * \param index
     * 
     * \return double average time.
     */
    double
    getAvgTime(const int& index) const {
        for (auto item = data.cbegin(); (item != data.end()); ++item)
            if (item->second.index == index)
                return item->second.avgTime;
        return 0.0;
    }
    
    /*
     * \brief Checks the given file input stream to see if the file is properly
     *        opened and connection is valid.
     * 
     * \param is const std::ifstream&.
     */
    void
    isFileValid(const std::ifstream& is) const { 
        if (!is) {
            std::cerr << "!!Error. File not found.!!\n"; 
            return;
        }
    }
    
    /*
     * \brief Having the given input stream, it reads a file and initializes 
     *        all fields and variables in class.
     * 
     * \param fileName.
     * 
     * \param is.
     */
    void
    readFile(const str& fileName, std::ifstream& is) {
        // Adds ", " at the end of the file name in case later on more
        // file names will be added to this field.
        this->fileName = fileName + ", ";
        str command, word;
        data.clear();  // Erase all elements to load new data.
        while (is >> word)
            if (word == "Command") {
                is.ignore(14);  // Ignore chars until get to the Command.
                std::getline(is, command);  // Read until end of the line.
                data.insert({command, fDetails()});  // Add the command.
            } else if (word == "Percent") {
                data[command].cpuPrcts.push_back(get<unsigned>(is, 22));
            } else if (word == "User") {
                data[command].userT.push_back(get<double>(is, 17));
            } else if (word == "System") {
                data[command].systemT.push_back(get<double>(is, 17)); 
            } else if (word == "Elapsed") {
                data[command].times.push_back(get<double>(is, 38));
            } else if (word == "Maximum") {
                data[command].peakMems.push_back(get<unsigned>(is, 29));
            } else {
                std::getline(is, word);  // Ignoring the line.
            }
    }
    
    /*
     * \brief Overloading Document class for having the output as TXT format.
     */
    class txtDoc : public Document {
        std::ostream& clone(std::ostream& os, TimeRP& F) {
            const str& 
              ln1("\t --------------------------------------------------\n"),
              ln2("\n\t|----------------|---------------------------------|\n"),
            avg_msg("\t|    Average:    |\t\t");
            const char* separator = "\n\t|\t\t |\t\t";
            // Header
            os << "\t\t\tName of file/s = " << F.fileName << "\n";
            // Adding table
            for (auto elms = F.data.cbegin(); (elms != F.data.cend()); elms++) {
                // -- 1st row of the table:
                os << ln1 << "\t|    command:    |   " << elms->first << "\n"
                   << "\t|Number of Table | \t" << elms->second.index << ln2;
            
                // -- 2nd row of the table:
                os << "\t|    User times  |\t\t";
                std::copy(elms->second.userT.begin(), elms->second.userT.end(), 
                    std::ostream_iterator<double>(os, separator));
                os << ln2;
            
                // -- 3rd row of the table:
                os << avg_msg << elms->second.avgUserT << ln2;
            
                // -- 4th row of the table:
                os << "\t|  System Times  |\t\t";
                std::copy(elms->second.systemT.begin(), elms->second.systemT.
                    end(), std::ostream_iterator<double>(os, separator));
                os << ln2;
            
                // -- 5th row of the table:
                os << avg_msg << elms->second.avgSysT << ln2;
            
                // -- 6th row of the table:
                os << "\t|CPU Percentages:|\t\t";
                std::copy(elms->second.cpuPrcts.begin(), elms->second.cpuPrcts.
                    end(), std::ostream_iterator<unsigned>(os, separator));
                os << ln2;
            
                // -- 7th row of the table:
                os << avg_msg << elms->second.avgCPU << ln2;
            
                // -- 8th row of the table:
                os << "\t| Elapsed Times: |\t\t";
                std::copy(elms->second.times.begin(), elms->second.times.end(), 
                    std::ostream_iterator<double>(os, separator));
                os << ln2;
            
                // -- 9th row of the table:
                os << avg_msg << elms->second.avgTime << ln2;
            
                // -- 10th row of the table:
                os << "\t|  Peak-memory:  |\t\t";
                std::copy(elms->second.peakMems.begin(), elms->second.peakMems.
                end(), std::ostream_iterator<unsigned>(os, separator));
                os << ln2;
            
                // -- 11th row of the table:
                os << avg_msg << elms->second.avgPeakMem << ln2;
            
                // -- 12th or Last row of the table:
                os << "\t|     95% CI:    |\t      " << elms->second.
                  avgTime << " +/- " << elms->second.CI << "\n" << ln1;
            }
            return os;
        }
    };
    
    /*
     * \brief Overloading Document class for having the output as HTML format.
     */
    class htmlDoc : public Document {
        std::ostream& clone(std::ostream& os, TimeRP& F) {
            const str& startRow   = "\t<tr>\n",
                       endRow     = "\t</tr>\n",
                       startCol   = "\t\t<td>",
                       endCol     = "</td>\n",
                       startLine  = startRow + startCol,
                       endLine    = endCol + endRow,
                       avg_msg    = "Average:";
            os << "<!DOCTYPE html>\n<html>\n<body>\n<style>\n"
               << "table, th, td {\n"
               << "\tborder: 1px solid black;\n"
               << "\tborder-collapse: collapse;\n}\n"
               << "th, td {\n"
               << "\tpadding: 5px;\n"
               << "\ttext-align: center;\n}\n"
               << "</style>\n</body>\n<body>\n"
               << "<h2 align = center> Name of file/s = " 
                    << F.fileName << "</h2>\n";  // Header.
            // Adding table/s
            for (auto elms = F.data.cbegin(); (elms != F.data.cend()); elms++) {
                os << "<table style=\"width:100%\">\n";
                // -- 1st row of the table:
                os << startLine << "<b>command:</b>" << endCol << startCol 
                   << elms->first << endLine;
                // -- 2nd row of the table:
                os << startLine << "<b>Number of Table:</b>" << endCol 
                   << startCol << elms->second.index << endLine;
                // -- 3rd row of the table:
                os << startLine << "<b>User times:</b>" << endCol << startCol;
                std::copy(elms->second.userT.begin(), elms->second.userT.end(), 
                    std::ostream_iterator<double>(os, "<br>"));
                os << endLine;
                // -- 4th row of the table:
                os << startLine << avg_msg << endCol << startCol
                   << elms->second.avgUserT << endLine;
                // -- 5th row of the table:
                os << startLine << "<b>System Times:</b>" << endCol << startCol;
                std::copy(elms->second.systemT.begin(), elms->second.systemT.
                    end(), std::ostream_iterator<double>(os, "<br>"));
                os << endLine;
                // -- 6th row of the table:
                os << startLine << avg_msg << endCol << startCol
                   << elms->second.avgSysT << endLine;
                // -- 7th row of the table:
                os << startLine << "<b>CPU Percentages:</b>" 
                   << endCol << startCol;
                std::copy(elms->second.cpuPrcts.begin(), elms->second.cpuPrcts.
                    end(), std::ostream_iterator<double>(os, "<br>"));
                os << endLine;
                // -- 8th row of the table:
                os << startLine << avg_msg << endCol << startCol
                   << elms->second.avgCPU << endLine;
                // -- 9th row of the table:
                os << startLine << "<b>Elapsed Times:</b>" << endCol <<startCol;
                std::copy(elms->second.times.begin(), elms->second.times.end(),
                       std::ostream_iterator<double>(os, "<br>"));
                os << endLine;
                // -- 10th row of the table:
                os << startLine << avg_msg << endCol << startCol
                   << elms->second.avgTime << endLine;
                // -- 11th row of the table:
                os << startLine << "<b>Peak-memory:</b>" << endCol << startCol;
                std::copy(elms->second.peakMems.begin(), elms->second.peakMems.
                end(), std::ostream_iterator<unsigned>(os, "<br>"));
                os << endLine;
                // -- 12th row of the table:
                os << startLine << avg_msg << endCol << startCol
                   << elms->second.avgPeakMem << endLine;
                // -- 13th or last row of the table:
                os << startLine << "<b>95% CI:</b>" << endCol << startCol
                   << elms->second.avgTime << " +/- " << elms->second.CI 
                   << endLine;
                os << "</table>\n<br>\n";
            }
            // End of html File.
            os << "</body>\n</html>\n";
            return os;
        }
    };
    
    /*
     * \brief Overloading the operator for having the output as a table with
     *        our desire elements in it.
     */
    friend std::ostream& operator<<(std::ostream& os, TimeRP& F) {
        if (F.getFile().empty())
            return os;
        switch (F.reportType) {
            case 0: {
                txtDoc txtDC;
                F.doc = &txtDC;  // Assigning the txtDoc as the output report.
                return F.doc->clone(os, F);
            }
            case 1: {
                htmlDoc htmlDC;
                F.doc = &htmlDC;  // Assigning the htmlDoc as the output report.
                return F.doc->clone(os, F);
            }
        }
        return os;
    }
};

int main (int argc, char* argv[]) {
    if (argc < 4) 
        return 1;
    const int& reportType = std::stoi(argv[2]);
    TimeRP FRP(argv[3], reportType);
    // for(int i = 4; i < argc; ++i)
    FRP = FRP + TimeRP(argv[1], reportType);
    std::ofstream report_of(argv[1]);  // Set the output file.
    FRP.report(report_of);
    return 0;
}

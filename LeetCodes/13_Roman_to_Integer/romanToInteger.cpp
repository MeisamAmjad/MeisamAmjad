/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * ##########################################################################
 * 
 * File:   RomanToInteger.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  73 ms
 * Rank in leetCode = beats 85.20% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <unordered_map>

static std::string s=[]() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return "";
} ();

class RomanToInteger {    
public:
    int romanToInt(std::string s) {
        std::unordered_map<char, int> romanSigns = {{'I', 1}, {'V', 5}, 
                                                    {'X', 10}, {'L', 50},
                                                    {'C', 100}, {'D', 500}, 
                                                    {'M', 1000}};
        int prevNum = 0,  currentNum, result = 0;
        for (int i = s.size() - 1; i >= 0; --i) {
            currentNum = romanSigns[s[i]];
            if (currentNum < prevNum)
                result -= currentNum;
            else
                result += currentNum;
            prevNum = currentNum;            
        }
        return result;
    }
};

int main(int argc, char** argv) {
    RomanToInteger test;
    assert(test.romanToInt("II") == 2);
    assert(test.romanToInt("IV") == 4);
    assert(test.romanToInt("IX") == 9);
    assert(test.romanToInt("LXIII") == 63);
    assert(test.romanToInt("XC") == 90);
    assert(test.romanToInt("XCIX") == 99);
    assert(test.romanToInt("LXXIV") == 74);
    assert(test.romanToInt("XXXVIII") == 38);
    return 0;
}


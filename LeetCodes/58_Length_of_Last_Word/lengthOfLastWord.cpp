/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a string s consists of upper/lower-case alphabets and empty space 
 * characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * 
 * Example:
 * Input: "Hello World"
 * Output: 5
 * ##########################################################################
 * 
 * File:   lengthOfLastWord.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 3 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int lengthOfLastWord(std::string s) {
        if (s.empty()) return 0;
        return countLength(s);
    }
    
private:
    inline
    int countLength(const std::string& s) {
        int count = 0, i = s.length() - 1;
        while (s[i] == ' ') --i;  // Passing empty spaces at the end of s.
        for (; (i >= 0 && s[i] != ' '); --i, ++count) {}
        return count;
    }
};

int main(int argc, char* argv[]) {
    Solution test;
    assert(test.lengthOfLastWord("Hello World") == 5);
    std::cout << test.lengthOfLastWord("a ") << std::endl;
    return 0;
}


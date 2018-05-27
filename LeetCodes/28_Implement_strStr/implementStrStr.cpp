/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * 
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * 
 * Clarification:
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we will return 0 when needle is an
 * empty string. This is consistent to C's strstr() and Java's indexOf().
 * ##########################################################################
 * 
 * File:   implementStrStr.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 4 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>

static auto ___ = [] () {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int strStr(std::string haystack, std::string needle) {
        if (needle.empty()) return 0;
        if (haystack.empty()) return -1;
        std::hash<std::string> strHash;
        size_t needle_hash = strHash(needle);
        for (size_t i = 0, jmp = needle.size(); i + jmp <= haystack.size(); ++i)
            if (strHash(haystack.substr(i, jmp)) == needle_hash)
                return i;
        return -1;
    }
};

int main(int argc, char** argv) {
    std::string sample1 = "hello", n1 = "ll";
    std::string sample2 = "aaaaa", n2 = "bba";
    Solution test;
    assert(test.strStr(sample1, n1) == 2);
    assert(test.strStr(sample2, n2) == -1);
    return 0;
}


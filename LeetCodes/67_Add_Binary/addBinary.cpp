/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 * 
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * ##########################################################################
 * 
 * File:   addBinary.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  3 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return "";
}();

class Solution {
public:
    std::string addBinary(std::string a, std::string b) {
        if (b.size() > a.size())
            std::swap(a, b);
        std::vector<char> output(a.size());
        int sum, next = 0;
        for (int i = a.size() - 1, j = b.size() - 1; i >= 0; --i, --j) {
            sum = static_cast<int>(a[i] - '0') + next; 
            if (j >= 0) sum += static_cast<int>(b[j] - '0');
            next = sum / 2;
            output[i] = static_cast<char>('0' + sum % 2);
        }
        if (next)
            output.insert(output.begin(), static_cast<char>('0' + next));
        return std::string(output.begin(), output.end());
    }
};

int main(int argc, char* argv[]) {
    Solution test;
    assert(test.addBinary("11", "1") == "100");
    assert(test.addBinary("11", "11") == "110");
    return 0;
}


/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Compute and return the square root of x, where x is guaranteed to be a 
 * non-negative integer.
 * 
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 * 
 * Example 1:
 * Input: 4
 * Output: 2
 * 
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
 * ##########################################################################
 * 
 * File:   sqrtX.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  16 ms
 * Rank in leetCode = beats 99.69 % in that runtime range.
 */

#include <assert.h>
#include <iostream>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int mySqrt(int x) {
        long result = x;
        long tmp = x;
        while (result * result > tmp) {
            result = (result + tmp / result) / 2;
        }
        return result;
    }
};

int main(int argc, char* argv[]) {
    Solution test;
    assert(test.mySqrt(4) == 2);
    assert(test.mySqrt(8) == 2);
    return 0;
}


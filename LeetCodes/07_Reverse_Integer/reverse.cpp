/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a 32-bit signed integer, reverse digits of an integer.
 * 
 * Example 1:
 * Input: 123
 * Output:  321
 * 
 * Example 2:
 * Input: -123
 * Output: -321
 * 
 * Example 3:
 * Input: 120
 * Output: 21
 * ##########################################################################
 * 
 * File:   reverse.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  19 ms
 * Rank in leetCode = beats 99.17% in that runtime range.
 */

#include <iostream>

static int x = []() { 
    std::ios::sync_with_stdio(false); 
    std::cin.tie(NULL);  
    return 0; 
}();

static int result = []() { 
    std::ios::sync_with_stdio(false); 
    std::cin.tie(NULL);  
    return 0; 
}();

class Reverse_Integer {
public:
    int reverse(int x) {
        long result = 0;
        while (x) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return result != static_cast<int> (result)? 0: result;
    }
};

int main(int argc, char* argv[]) {
    Reverse_Integer test;
    std::cout << test.reverse(-123) << std::endl;
    std::cout << test.reverse(120) << std::endl;
    std::cout << test.reverse(4501) << std::endl;
    std::cout << test.reverse(1534236469) << std::endl;
    return 0;
}


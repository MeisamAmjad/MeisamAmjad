/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a non-empty array of digits representing a non-negative integer, 
 * plus one to the integer.
 * The digits are stored such that the most significant digit is at the head
 * of the list, and each element in the array contain a single digit.
 * 
 * You may assume the integer does not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example 1:
 * Input: [1, 2, 3]
 * Output: [1, 2, 4]
 * Explanation: The array represents the integer 123.
 * 
 * Example 2:
 * Input: [4, 3, 2, 1]
 * Output: [4, 3, 2, 2]
 * Explanation: The array represents the integer 4321.
 * ##########################################################################
 * 
 * File:   plusOne.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 4 ms
 * Rank in leetCode = beats 99.68% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    return nullptr;
}();

class Solution {
public:
    std::vector<int> plusOne(std::vector<int>& digits) {
        int addPrevDigit = 1;
        if ((addPrevDigit = addOne(digits, addPrevDigit)))
            digits.insert(digits.begin(), 1);
        return digits;
    }
    
private:
    inline
    int addOne(std::vector<int>& digits, int& addPrevDigit) {
       for (int i = digits.size() - 1, plusOne = 0; 
                                    (addPrevDigit && i >= 0); --i) {
            plusOne      = digits[i] + addPrevDigit;
            digits[i]    = plusOne % 10;
            addPrevDigit = plusOne / 10;
        }
       return addPrevDigit;
    }
};

int main(int argc, char* argv[]) {
    std::vector<int> sample1 = {1, 2, 3};
    std::vector<int> sample2 = {4, 3, 2, 1};
    std::vector<int> sample3 = {5, 6, 2, 0, 0, 4, 6, 2, 4, 9};
    std::vector<int> result1 = {1, 2, 4};
    std::vector<int> result2 = {4, 3, 2, 2};
    Solution test;
    
    // for (int digit : test.plusOne(sample3)) {std::cout << digit;}
    // std::cout << std::endl;
    // for (int digit : test.plusOne(sample2)) {std::cout << digit;}
    assert(test.plusOne(sample1) == result1);
    assert(test.plusOne(sample2) == result2);
    return 0;
}


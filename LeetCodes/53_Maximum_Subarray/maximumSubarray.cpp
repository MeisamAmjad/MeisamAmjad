/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given an integer array nums, find the contiguous subarray (containing at 
 * least one number) which has the largest sum and return its sum.
 * 
 * Example:
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4],
 * Output: 6
 * Explanation: [4, -1, 2, 1] has the largest sum = 6.
 * 
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution
 * using the divide and conquer approach, which is more subtle.
 * ##########################################################################
 * 
 * File:   maximumSubarray.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 6 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>
#include <algorithm>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int maxSubArray(std::vector<int>& nums) {
        if (nums.empty()) return 0;
        int finalMax = nums[0];
        for (int i = 1, currMax = finalMax; i < nums.size(); 
                                    getMax(currMax, finalMax, nums[i++])) {}
        return finalMax;
    }
    
private:
    inline
    void getMax(int& currMax, int&finalMax, int num) {
        currMax  = std::max(num, currMax + num);
        finalMax = std::max(finalMax, currMax);
    }
};

int main(int argc, char** argv) {
    std::vector<int> sample1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    Solution test;
    assert(test.maxSubArray(sample1) == 6);
    return 0;
}


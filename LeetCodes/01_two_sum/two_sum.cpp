/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given an array of integers, return indices of the two numbers such that they 
 * add up to a specific target. You may assume that each input would have 
 * exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * ##########################################################################
 * 
 * 
 * File:   two_sum.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 8 ms
 * Rank in leetCode = beats 98.83% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>
#include <unordered_map>

static auto nums = []() { 
    std::ios::sync_with_stdio(false); 
    std::cin.tie(NULL);  
    return 0; 
}();

static auto tempList = []() { 
    std::ios::sync_with_stdio(false); 
    std::cin.tie(NULL);  
    return 0; 
}();

using intVec = std::vector<int>;
using map    = std::unordered_map<int, size_t>;

class Two_sum {
public:
    intVec twoSum(intVec& nums, const int& target) {
        map tempList;
        auto temp = tempList.end(), end = tempList.end();
        for (size_t i = 0; i < nums.size(); tempList.insert({nums[i], i++}))
            if ((temp = tempList.find(target - nums[i])) != end)
                return {temp->second, i};
        return {};
    }
};

int main(int argc, char** argv) {
    intVec nums = {2, 7, 11, 15, 6, 0, -1, 20, 30};
    Two_sum test;
    assert(test.twoSum(nums, 1)[0] == 0 && test.twoSum(nums, 1)[1] == 6);
    return 0;
}


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
 * Author: Meisam Amjad amjadm@miamioh.edu
 *
 */

#include <iostream>
#include <vector>
#include <unordered_map>

class Two_sum {
public:
    std::vector<int> twoSum(std::vector<int>& nums, const int& target) {
        std::unordered_map<int, size_t> tempList;
        auto temp = tempList.end();
        for (size_t i = 0; i < nums.size(); tempList.insert({nums[i], i++}))
            if ((temp = tempList.find(target - nums[i])) != tempList.end())
                return {temp->second, i};
        return {};
    }
};

int main(int argc, char** argv) {
    std::vector<int> nums = {2, 7, 11, 15, 6, 0, -1, 20, 30};
    const int target = 1;
    Two_sum test;
    std::vector<int> result;
    result = test.twoSum(nums, target);
    for (auto element : result)
        std::cout << element << " ";
    return 0;
}


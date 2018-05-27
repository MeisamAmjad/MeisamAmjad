/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 * 
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 * 
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 * 
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 * ##########################################################################
 * 
 * File:   searchInsertPosition.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 4 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>

static auto ___ =[] () {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int searchInsert(std::vector<int>& nums, int target) {
        if (nums.empty() || target <= nums[0]) return 0;
        if (target > nums[nums.size() - 1]) return nums.size();
        size_t head = 0, tail = nums.size() - 1, middle;
        while (head <= tail) {
            middle = (head + tail) >> 1;
            if (nums[middle] == target)
                return middle;
            else if (nums[middle] > target)
                tail = middle - 1;
            else
                head = middle + 1;
        }
        return head;
    }
};

int main(int argc, char* argv[]) {
    std::vector<int> sample1 = {1, 3, 5, 6};
    std::vector<int> sample2 = {1, 3};
    std::vector<int> sample3 = {1, 2, 4, 6, 7};
    Solution test;
    // std::cout << test.searchInsert(sample3, 3);
    assert(test.searchInsert(sample1, 5) == 2);
    assert(test.searchInsert(sample1, 2) == 1);
    assert(test.searchInsert(sample1, 7) == 4);
    assert(test.searchInsert(sample1, 0) == 0);
    assert(test.searchInsert(sample2, 2) == 1);
    assert(test.searchInsert(sample3, 3) == 2);
    return 0;
}


/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 * 
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n 
 * respectively.
 * 
 * You may assume that nums1 has enough space (size that is greater or
 * equal to m + n) to hold additional elements from nums2.
 * 
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 * ##########################################################################
 * 
 * File:   mergeSortedArray.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  4 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    void merge(std::vector<int>& nums1, int m, std::vector<int>& nums2, int n) {
        int i = m - 1, j = n - 1, index = nums1.size() - 1;
        for (; i >= 0 && j >= 0; --index) {        
            if (nums1[i] > nums2[j])
                nums1[index] = nums1[i--];
            else
                nums1[index] = nums2[j--];
        }
        for (; i >= 0; nums1[index--] = nums1[i--]) {}
        for (; j >= 0; nums1[index--] = nums2[j--]) {}
    }
};

int main(int argc, char** argv) {
    Solution test;
    std::vector<int> a = {1, 2, 3, 0, 0, 0}; 
    std::vector<int> b = {2, 5, 6};
    std::vector<int> result = {1, 2, 2, 3, 5, 6};
    test.merge(a, 3, b, 3);
    assert(a == result);
    a = {0, 0, 3, 0, 0, 0, 0, 0, 0};
    b = {-1, 1, 1, 1, 2, 3};
    result = {-1, 0, 0, 1, 1, 1, 2, 3, 3};
    test.merge(a, 3, b, 6);
    assert(a == result);
    return 0;
}


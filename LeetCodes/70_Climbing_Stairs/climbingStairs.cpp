/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways
 * can you climb to the top?
 * 
 * Note: Given n will be a positive integer.
 * 
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * ##########################################################################
 * 
 * File:   climbingStairs.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =   ms
 * Rank in leetCode = beats  % in that runtime range.
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
    int climbStairs1(int n) {
        return recursive(0, n);
    }
    
    int climbStairs2(int n) {
        std::vector<int> memo(n + 1);
        return Helper(0, n, memo);
    }
    
    int climbStairs3(int n) {
        return dynamic(n);
    }
    
private:
    inline
    int recursive(int i, int n) {  // O(2^n)
        if (i > n) return 0;
        if (i == n) return 1;
        return recursive(i + 1, n) + recursive(i + 2, n);
    }
    
    inline
    int Helper(int i, int n, std::vector<int>& memo) {  // O(n^2)
        if (i > n) return 0;
        if (i == n) return 1;
        if (memo[i] > 0) return memo[i];
        memo[i] = Helper(i + 1, n, memo) + Helper(i + 2, n, memo);
        return memo[i];
    }
    
    inline 
    int dynamic(int n) {  // O(n)
        if (n < 2) return 1;
        if (n == 2) return n;
        std::vector<int> memo(n);
        memo[0] = 1;
        memo[1] = 2;
        for (int i = 2; i < n; memo[i] = memo[i - 1] + memo[i - 2], ++i) {}
        return memo[n - 1];
    }
};

int main(int argc, char* argv[]) {
    Solution test;
    assert(test.climbStairs1(2) == test.climbStairs2(2));
    assert(test.climbStairs2(2) == test.climbStairs3(2));
    assert(test.climbStairs1(12) == test.climbStairs2(12));
    assert(test.climbStairs2(12) == test.climbStairs3(12));
    assert(test.climbStairs1(0) == test.climbStairs2(0));
    assert(test.climbStairs2(0) == test.climbStairs3(0));
    assert(test.climbStairs1(3) == test.climbStairs2(3));
    assert(test.climbStairs2(3) == test.climbStairs3(3));
    return 0;
}


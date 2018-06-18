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
 * Time in leetCode =  2 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>
#include <algorithm>  // For sqrt(x)

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
    
    int climbStairs4(int n) {
        return fibonacci(n);
    }
    
    int climbStairs5(int n) {
        return binets(n);
    }
    
    int climbStairs6(int n) {  // O(log(n)) O(1)
        return FibonacciFormula(n);
    }
    
private:
    inline
    int recursive(int i, int n) {  // O(2^n) O(n)
        if (i > n) return 0;
        if (i == n) return 1;
        return recursive(i + 1, n) + recursive(i + 2, n);
    }
    
    inline
    int Helper(int i, int n, std::vector<int>& memo) {  // O(n^2) O(n)
        if (i > n) return 0;
        if (i == n) return 1;
        if (memo[i] > 0) return memo[i];
        memo[i] = Helper(i + 1, n, memo) + Helper(i + 2, n, memo);
        return memo[i];
    }
    
    inline 
    int dynamic(int n) {  // O(n) O(n)
        if (n < 2) return 1;
        if (n == 2) return n;
        std::vector<int> memo(n);
        memo[0] = 1;
        memo[1] = 2;
        for (int i = 2; i < n; memo[i] = memo[i - 1] + memo[i - 2], ++i) {}
        return memo[n - 1];
    }
    
    inline
    int fibonacci(const int& n) {  // O(n) O(n)
        if (n == 1) return 1;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
    
    inline
    int binets(int n) {  // O(log(n)) O(1)
        std::vector<std::vector<int>> q = {{1, 1}, {1, 0}};
        std::vector<std::vector<int>> res = pow(q, n);
        return res[0][0];
    }
    
    inline
    std::vector<std::vector<int>> pow(std::vector<std::vector<int>>& a, int n) {
        std::vector<std::vector<int>> ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1)
                ret = multiply(ret, a);
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    
    inline
    std::vector<std::vector<int>> multiply(std::vector<std::vector<int>>& a,
                                           std::vector<std::vector<int>>& b) {
        std::vector<std::vector<int>> c(2, std::vector<int>(2));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
    
    inline
    int FibonacciFormula(const int& n) {
        double sqrt5 = std::sqrt(5);
        double fibn = std::pow((1 + sqrt5) / 2, n + 1) - 
                      std::pow((1 - sqrt5) / 2, n + 1);
        return fibn / sqrt5;
    }
};

int main(int argc, char* argv[]) {
    Solution test;
    assert(test.climbStairs1(2) == test.climbStairs2(2));
    assert(test.climbStairs2(2) == test.climbStairs3(2));
    assert(test.climbStairs3(2) == test.climbStairs4(2));
    assert(test.climbStairs4(2) == test.climbStairs5(2));
    assert(test.climbStairs5(2) == test.climbStairs6(2));
    
    assert(test.climbStairs1(12) == test.climbStairs2(12));
    assert(test.climbStairs2(12) == test.climbStairs3(12));
    assert(test.climbStairs3(12) == test.climbStairs4(12));
    assert(test.climbStairs4(12) == test.climbStairs5(12));
    assert(test.climbStairs5(12) == test.climbStairs6(12));
    
    assert(test.climbStairs1(1) == test.climbStairs2(1));
    assert(test.climbStairs2(1) == test.climbStairs3(1));
    assert(test.climbStairs3(1) == test.climbStairs4(1));
    assert(test.climbStairs4(1) == test.climbStairs5(1));
    assert(test.climbStairs5(1) == test.climbStairs6(1));
    
    assert(test.climbStairs1(3) == test.climbStairs2(3));
    assert(test.climbStairs2(3) == test.climbStairs3(3));
    assert(test.climbStairs3(3) == test.climbStairs4(3));
    assert(test.climbStairs4(3) == test.climbStairs5(3));
    assert(test.climbStairs5(3) == test.climbStairs6(3));
    return 0;
}


/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a string s, find the longest palindromic substring in s. You may 
 * assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 * 
 * ##########################################################################
 * 
 * File:   longestPalindromicSubstring.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 0 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return "";
}();

class Solution {
public:
    int longestPalindrome1(std::string s) {
        return recursive(s, 0, s.size() - 1);
    }
    
    std::string longestPalindrome2(std::string s) {
        if (s.empty())
                return "";
        int n = s.size();
        // initialize P[n][n]
        bool P[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; P[i][j++] = false) {}
        for (int k = 0; k < n; k++) {
            P[k][k] = true;
            if (k == n - 1) break;
            P[k][k + 1] = (s[k] == s[k + 1]);
        }
        // dp
        for (int i = n - 3; i >= 0; --i)
            for (int j = i + 2; j < n; ++j)
                P[i][j] = (P[i + 1][j - 1] && s[i] == s[j]);
        // get maxstr result
        int start = 0, max = 0;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
            if (P[i][j] && j - i + 1 > max) {
                max = j - i + 1;
                start = i;
            }
        return s.substr(start, max);;
    }
    
    std::string longestPalindrome3(std::string s) {
        if (s.size() < 2)
            return s;
        // initializing a new string.
        const std::string& T = initStr(s);
        const int& n = T.size();
        std::vector<int> P(n, 0);
        int C = 0, R = 0, maxLen = 0, centerIndex = 0;
        for (int i = 0; i < n; i++) {
            // equals to i' = C - (i - C)
            int i_mirror = 2 * C - i;
            P[i] = (R > i)? std::min(R - i, P[i_mirror]): 0;
            // Attempt to expand palindrome centered at i
            for (; T[i + 1 + P[i]] == T[i - 1 - P[i]]; P[i]++) {}
            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
            // Find the maximum element in P.
            if (P[i] > maxLen) {
                maxLen      = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) >> 1;
        if (start < 0) start = 0;
        return s.substr(start , maxLen);
    }
    
private:
    int recursive(std::string& s, int i, int j) {
        // Base Case 1: If there is only 1 character
        if (i == j)
            return 1;
        // Base Case 2: If there are only 2 characters and both are same
        if ((s[i] == s[j]) && (i + 1 == j))
            return 2;
        // If the first and last characters match        
        if (s[i] == s[j])
            return recursive(s, i + 1, j - 1) + 2;
        // If the first and last characters do not match
        return std::max(recursive(s, i, j - 1), recursive(s, i + 1, j));
    }
    
    inline
    std::string initStr(const std::string& s) {
        int n = s.size() * 2 + 1, length = s.size();
        std::vector<char> tmp(n, '#');
        for (int i = 1, j = 0; j < length; tmp[i] = s[j++], i += 2) {}
        return std::string(tmp.begin(), tmp.end());
    }
};

int main(int argc, char* argv[]) {
    std::string sample1 = "babad";
    std::string sample2 = "abcabcbb";
    std::string sample3 = "bb";
    
    Solution test;
    std::cout << test.longestPalindrome1(sample1) << std::endl;
    std::cout << test.longestPalindrome2(sample1) << std::endl;
    std::cout << test.longestPalindrome3(sample1) << std::endl;
    
    std::cout << test.longestPalindrome1(sample2) << std::endl;
    std::cout << test.longestPalindrome2(sample2) << std::endl;
    std::cout << test.longestPalindrome3(sample2) << std::endl;
    
    std::cout << test.longestPalindrome1(sample3) << std::endl;
    std::cout << test.longestPalindrome2(sample3) << std::endl;
    std::cout << test.longestPalindrome3(sample3) << std::endl;
    return 0;
}


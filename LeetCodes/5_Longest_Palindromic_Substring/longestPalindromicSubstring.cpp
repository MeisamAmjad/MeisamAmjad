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
 * Time in leetCode =  ms
 * Rank in leetCode = beats  % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <algorithm>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
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
            for (int j = 0; j < n; P[i][j++] = false) {}
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
};

int main(int argc, char* argv[]) {
    std::string sample1 = "babad";
    std::string sample2 = "abcabcbb";
    std::string sample3 = "cbbd";
    
    Solution test;
    std::cout << test.longestPalindrome1(sample1) << std::endl;
    std::cout << test.longestPalindrome2(sample1) << std::endl;
    
    std::cout << test.longestPalindrome1(sample2) << std::endl;
    std::cout << test.longestPalindrome2(sample2) << std::endl;
    
    std::cout << test.longestPalindrome1(sample3) << std::endl;
    std::cout << test.longestPalindrome2(sample3) << std::endl;
    return 0;
}


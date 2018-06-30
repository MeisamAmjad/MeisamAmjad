/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Valid Parenthesis String My SubmissionsBack to Contest
 * Virtual User Accepted: 0
 * Virtual User Tried: 0
 * Virtual Total Accepted: 0
 * Virtual Total Submissions: 0
 * 
 * Difficulty: Medium
 * Given a string containing only three types of characters: '(', ')' and
 * '*', write a function to check whether this string is valid. We define
 * the validity of a string by these rules:
 * 
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left 
 * parenthesis '(' or an empty string.
 * 
 * An empty string is also valid.
 * 
 * Example 1:
 * Input: "()"
 * Output: True
 * 
 * Example 2:
 * Input: "(*)"
 * Output: True
 * 
 * Example 3:
 * Input: "(*))"
 * Output: True
 * ##########################################################################
 * 
 * File:   valid_Parenthesis_String.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  ms
 * Rank in leetCode = beats % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>
#include <stack>
#include <string>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    bool checkValidString(std::string s) {
        if (s.empty()) return true;
        int stk = 0;
        int asterisk = 0;
        for (size_t i = 0; i < s.size(); ++i) {
            switch (s[i]) {
                case '(':
                    stk++;
                    asterisk++;
                    break;
                case '*':
                    if (stk)
                        stk--;
                    asterisk++; 
                    break;
                case ')':
                    if (stk)
                        stk--; 
                    asterisk--;
                    if (!stk && asterisk < 0)
                        return false;
            }
        }
        return !stk;
    }
};

int main(int argc, char* argv[]) {
    std::string sample1 = "(())(())(((()*()()()))()((()()(*()())))(((*)()";
    Solution test;
    std::cout << test.checkValidString(sample1) << std::endl;
    return 0;
}


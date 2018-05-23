/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 *  ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * 
 * Example 1:
 * Input: "()"
 * Output: true
 * 
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 * 
 * Example 3:
 * Input: "(]"
 * Output: false
 * 
 * Example 4:
 * Input: "([)]"
 * Output: false
 * 
 * Example 5:
 * Input: "{[]}"
 * Output: true
 * ##########################################################################
 * 
 * File:   validParentheses.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 4 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <stack>

static std::string s = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return "";
}();

static auto tempStack = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return "";
}();

static int ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(0);
    return 0;
}();

class Solution {
public:
    bool isValid(std::string s) {
        if (s.empty() || s.size() == 1) return false;
        std::stack<char> tempStack;
        for (int i = 0; i < s.size(); ++i)
            switch(s[i]) {
                case '(':
                case '{':
                case '[':
                    tempStack.push(s[i]);
                    break;
                case ')':
                    if (tempStack.top() == '(') tempStack.pop();
                    else return false;
                    break;
                case '}':
                    if (tempStack.top() == '{') tempStack.pop();
                    else return false;
                case ']':
                    if (tempStack.top() == ']') tempStack.pop();
                    else return false;
                default:
                    return false;
            }
    }
};

int main(int argc, char* argv[]) {
    std::string sample1 = "()";
    std::string sample2 = "()[]{}";
    std::string sample3 = "(]";
    std::string sample4 = "([)]";
    std::string sample5 = "{[]}";
    Solution s;
    assert(s.isValid(sample1));
    assert(s.isValid(sample2));
    assert(s.isValid(sample3));
    assert(s.isValid(sample4));
    assert(s.isValid(sample5));
    return 0;
}


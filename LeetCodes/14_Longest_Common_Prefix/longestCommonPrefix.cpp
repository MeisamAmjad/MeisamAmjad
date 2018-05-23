/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Write a function to find the longest common prefix string amongst an array 
 * of strings.
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * 
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * 
 * Note:
 * All given inputs are in lowercase letters a-z.
 * ##########################################################################
 * 
 * File:   longestCommonPrefix.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 4 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <vector>

static auto __ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return "";
}();

class Solution {
public:
    std::string longestCommonPrefix(std::vector<std::string>& strs) {
        if (strs.size() == 0) return "";
        if (strs.size() == 1) return strs[0];     
        size_t index = 0;
        std::string temp = strs[0];
        while (index < temp.size()) {
            for (size_t item = 1; item < strs.size(); ++item)
                if (strs[item].size() > index) {
                    if (temp[index] != strs[item][index])
                        return temp.substr(0, index);
                } else {
                    return temp.substr(0, index);
                }
            ++index;
        }
        return temp.substr(0, index);
    }
};

int main(int argc, char* argv[]) {
    std::vector<std::string> sample1 = {"flower", "flow", "flight"};
    std::vector<std::string> sample2 = {"dog", "racecar", "car"};
    std::vector<std::string> sample3 = {"aa", "a"};
    Solution lCP;
    // std::cout << lCP.longestCommonPrefix(sample1) << std::endl;
    // std::cout << lCP.longestCommonPrefix(sample2) << std::endl;
    // std::cout << lCP.longestCommonPrefix(sample3) << std::endl;
    assert(lCP.longestCommonPrefix(sample1) == "fl");
    assert(lCP.longestCommonPrefix(sample2) == "");
    assert(lCP.longestCommonPrefix(sample3) == "a");
    return 0;
}


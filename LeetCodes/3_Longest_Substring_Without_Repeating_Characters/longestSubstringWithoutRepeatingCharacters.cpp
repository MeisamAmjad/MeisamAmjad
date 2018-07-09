/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * ##########################################################################
 * 
 * File:   longestSubstringWithoutRepeatingCharacters.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 8 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <unordered_set>
#include <algorithm>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int lengthOfLongestSubstring1(std::string s) {
        if (s.size() < 2)
            return s.size();
        int n = s.size(), start = 0, end = 0, max = 0;
        std::unordered_set<char> set;
        while (start < n && end < n)
            if (set.insert(s[end]).second)
                max = std::max(max, (++end) - start);
            else
                set.erase(s[start++]);
        return max;
    }
    
    int lengthOfLongestSubstring2(std::string s) {
        if (s.size() < 2)
            return s.size();
        int n = s.size(), max = 0;
        int index[128];
        initiArray(index);
        for (int i = 0, j = 0; j < n; j++) {
            i = std::max(index[s[j]], i);
            std::cout << "i: " << i << "\t" 
                    << "s[j]: " << s[j] << "\t"
                    << "int(s[j]): " << static_cast<int>(s[j]) << "\n"
                    << "index[s(j)]: " << index[static_cast<int>(s[j])] << "\t"
                    << "max: " << max << "\t" 
                    << "[j - i + 1]: " << j - i + 1 << "\n";
            max = std::max(max, j - i + 1);
            std::cout << "max: " << max <<  "\n--------\n";
            index[s[j]] = j + 1;
        }
        return max;
    }
    
private: 
    inline
    void initiArray(int (&A)[128]) {
        for (int i = 0; (i < 128); A[i++] = 0) {}  
    }
};

int main(int argc, char* argv[]) {
    std::string sample1 = "abcabcbb";
    std::string sample2 = "pwwkew";
    std::string sample3 = "bbbbb";
    std::string sample4 = "ababaf";
    
    Solution test;
    assert(test.lengthOfLongestSubstring1(sample1) == 3);
    assert(test.lengthOfLongestSubstring1(sample2) == 3);
    assert(test.lengthOfLongestSubstring1(sample3) == 1);
    assert(test.lengthOfLongestSubstring1(sample4) == 3);
    
    assert(test.lengthOfLongestSubstring2(sample1) == 3);
    assert(test.lengthOfLongestSubstring2(sample2) == 3);
    assert(test.lengthOfLongestSubstring2(sample3) == 1);
    assert(test.lengthOfLongestSubstring2(sample4) == 3);
    return 0;
}


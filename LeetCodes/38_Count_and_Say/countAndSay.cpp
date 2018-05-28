/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * The count-and-say sequence is the sequence of integers with the first 
 * five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 6.     312211
 * 7.     13112221
 * 8.     1113213211
 * 9.     31131211131221
 * 10.     13211311123113112211
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth term of the count-and-say sequence.
 * Note: Each term of the sequence of integers will be represented as a string.
 * 
 * Example 1:
 * Input: 1
 * Output: "1"
 * 
 * Example 2:
 * Input: 4
 * Output: "1211"
 * ##########################################################################
 * 
 * File:   countAndSay.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 2 ms
 * Rank in leetCode = beats 100% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    std::string countAndSay(int n) {
        /* if (!n) return "";
        std::string map[11] = {"", "1", "11", "21", "1211", 
                                "111221", "312211", "13112221", 
                                "1113213211", "31131211131221", 
                                "13211311123113112211"};
        std::string result = "";
        while (n > 10) {
            int index = n % 10;
            result = map[index] + result;
            n /= 10;
            std::cout << "-->" << index << "," << n << "<--";
        }
        result = map[n] + result;
        return result; */
        
        /* if (n == 0) return "";
        std::string res = "1";
        while (--n) {
            std::string cur = "";
            for (size_t i = 0; i < res.size(); i++) {
                int count = 1;
                while ((i + 1 < res.size()) && (res[i] == res[i + 1])) {
                    count++;    
                    i++;
                }
                cur += std::to_string(count) + res[i];
            }
            res = cur;
        }
        return res; */
        std::vector<char> buf1, buf2;
        std::vector<char> *pre = &buf1, *cur = &buf2;
        pre->push_back('1');

        while (--n) {
            cur->clear();
            char c = (*pre)[0];
            int cnt = 1;
            for (size_t i = 1; i < pre->size(); ++i) {
                if ((*pre)[i] == c) {
                    cnt += 1;
                } else {
                    append_int(*cur, cnt);
                    cur->push_back(c);
                    c = (*pre)[i];
                    cnt = 1;
                }
            }
            append_int(*cur, cnt);
            cur->push_back(c);
            std::swap(pre, cur);
        }
        return std::string(pre->begin(), pre->end());
    }
    
    std::string countAndSay2(int n) {
        if (n <= 0) return "";
        std::vector<char> result = {'1'};
        while (--n) {
            std::vector<char> temp;
            for (size_t index = 0; index < result.size(); ) {
                size_t count = countDigit(result, index);
                temp.push_back(static_cast<char>('0' + count));
                temp.push_back(result[index]);
                index += count;
            }
            result = temp;
        }
        return std::string(result.begin(), result.end());
    }
    
private:
    inline
    size_t countDigit(const std::vector<char>& buff, const size_t& index) {
        size_t count = 1;
        for (size_t i = index; 
                (i < buff.size() - 1) && (buff[i] == buff[i + 1]); 
                    ++i, ++count) {}
        return count;
    }
    
    void append_int(std::vector<char> &buf, int n) {
        while (n != 0) {
        buf.push_back('0' + n % 10);
        n /= 10;
        }
    }
};

int main(int argc, char* argv[]) {
    Solution test;
    std::cout << test.countAndSay(11) << "\n";
    std::cout << test.countAndSay2(11) << "\n---\n";
    std::cout << test.countAndSay(20) << "\n";
    std::cout << test.countAndSay2(20) << "\n---\n";
    std::cout << test.countAndSay(1) << "\n";
    std::cout << test.countAndSay2(1) << "\n---\n";
    std::cout << test.countAndSay(10) << "\n";
    std::cout << test.countAndSay2(10) << "\n---\n";
    std::cout << test.countAndSay(13) << "\n";
    std::cout << test.countAndSay2(13) << "\n";
    return 0;
}


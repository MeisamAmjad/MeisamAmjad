/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * If you are thinking of converting the integer to string, note the 
 * restriction of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might
 * overflow. How would you handle such case?
 * ##########################################################################
 * 
 * 
 * File:   PalindromeNumber.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 140 ms
 * Rank in leetCode = beats 98.61% in that runtime range.
 */
#include <assert.h>
#include <iostream>

static auto ___ = []() { 
    std::ios::sync_with_stdio(false); 
    std::cin.tie(NULL);  
    return 0; 
}();

class Palindrome {
public:
    bool isPalindrome(int x) {
        if (x < 0) return 0;
        int tmp = x;
        int reverseX = 0;
        getReverse(reverseX, tmp);
        return x == reverseX;
    }
private:
    inline
    void getReverse(int& reverseX, int& x) {
        while (x) {
            reverseX = reverseX * 10 + x % 10;
            x /= 10;
        }
    }
};

int main(int argc, char** argv) {
    Palindrome test;
    assert(test.isPalindrome(1221));
    assert(test.isPalindrome(120021));
    assert(!test.isPalindrome(-1));
    assert(!test.isPalindrome(10));
    assert(!test.isPalindrome(100));
    assert(test.isPalindrome(1));
    assert(test.isPalindrome(0));
    return 0;
}


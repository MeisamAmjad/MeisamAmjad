/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except
 * the number 0 itself.
 * 
 * Example
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * ##########################################################################
 * 
 * File:   addTwoNumbers.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 20 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *currL1 = l1;
        ListNode *currL2 = l2;
        ListNode *result = new ListNode(0), *head = result;
        int extraDigit = 0;
        while (currL1 && currL2) {
            int currDigit = extraDigit + currL1->val + currL2->val;
            extraDigit = 0;
            if (currDigit > 9) {
                extraDigit = 1;
                currDigit -= 10;
            }
            result->next = new ListNode(currDigit);
            result = result->next;
            currL1 = currL1->next;
            currL2 = currL2->next;
        }
        if (currL1) 
            fillTheRest(currL1, result, extraDigit);
        else
            fillTheRest(currL2, result, extraDigit);
        return head->next;
    }
    
    ListNode* addTwoNumbers2(ListNode* l1, ListNode* l2) {  
        ListNode* head = new ListNode(0);
        ListNode* curr = head;
        int cbit = 0, a = 0, b = 0;
        while (l1||l2) {
            int sum = ((l1)? (l1->val): (0)) + ((l2)? (l2->val): (0)) + cbit;
            curr->next = new ListNode(sum%10);
            cbit = sum / 10;
            if (l1) l1 = l1->next;
            if (l2) l2 = l2->next;
            curr = curr->next;
        }
        if (cbit) {
            curr->next = new ListNode(cbit);    
        }
        return head->next;
    }
    
private:
    inline
    void fillTheRest(ListNode* node, ListNode* result, int& extraDigit) {
        while (node) {
            int currDigit = node->val + extraDigit;
            extraDigit = 0;
            if (currDigit > 9) {
                extraDigit = 1;
                currDigit -= 10;
            }
            result->next = new ListNode(currDigit);
            result = result->next;
            node   = node->next;
        }
        if (extraDigit)
            result->next = new ListNode(extraDigit);
    }
};

int main(int argc, char* argv[]) {
    ListNode* sample1 = new ListNode(2);
    sample1->next = new ListNode(4);
    sample1->next->next = new ListNode(9);
    
    ListNode* sample2 = new ListNode(5);
    sample2->next = new ListNode(6);
    sample2->next->next = new ListNode(9);
    
    Solution test;
    ListNode* result = test.addTwoNumbers(sample1, sample2);
    while (result) {
        std::cout << result->val << ", ";
        result = result->next;
    }
    
    ListNode* result2 = test.addTwoNumbers(sample1, sample2);
    while (result2) {
        std::cout << result2->val << ", ";
        result2 = result2->next;
    }
    return 0;
}


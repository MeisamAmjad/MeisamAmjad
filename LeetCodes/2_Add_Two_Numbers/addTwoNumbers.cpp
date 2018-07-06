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
 * Time in leetCode =  ms
 * Rank in leetCode = beats  % in that runtime range.
 */

#include <assert.h>
#include <iostream>

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* currL1 = l1;
        ListNode* currL2 = l2;
        ListNode* result;
        ListNode* head;
        int extraDigit = 0;
        bool first = 1;
        while (currL1 && currL2) {
            int currDigit = extraDigit + currL1->val + currL2->val;
            if (currDigit > 9) {
                extraDigit = currDigit / 10;
                currDigit %= 10;
            }
            result = new ListNode(currDigit);
            if (first) {
                head = result;
                first = 0;
            }
            result = result->next;
            currL1 = currL1->next;
            currL2 = currL2->next;
        }
        if (currL1) 
            fillTheRest(currL1, result);
        else
            fillTheRest(currL2, result);
        return head;
    }
    
private:
    inline
    void fillTheRest(ListNode* node, ListNode* result) {
        while (node) {
            result = new ListNode(node->val);
            result = result->next;
            node   = node->next;
        }
    }
};

int main(int argc, char* argv[]) {
    ListNode* sample1 = new ListNode(2);
    sample1->next = new ListNode(4);
    sample1->next->next = new ListNode(3);
    
    ListNode* sample2 = new ListNode(5);
    sample2->next = new ListNode(6);
    sample2->next->next = new ListNode(4);
    
    Solution test;
    ListNode* result = test.addTwoNumbers(sample1, sample2);
    while (result) {
        std::cout << result->val << ", ";
        result = result->next;
    }
    return 0;
}


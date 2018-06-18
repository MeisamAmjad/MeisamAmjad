/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 * 
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 * ##########################################################################
 * 
 * File:   removeDuplicatesfromSortedList.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =   ms
 * Rank in leetCode = beats % in that runtime range.
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
    ListNode* deleteDuplicates(ListNode* head) {
        return NULL;
    }
};

int main(int argc, char** argv) {
    return 0;
}


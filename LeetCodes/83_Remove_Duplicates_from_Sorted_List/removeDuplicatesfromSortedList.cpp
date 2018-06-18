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
 * Time in leetCode =  10 ms
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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode *now = head, *tmp;
        while (head && now->next) {
            if (now->val == now->next->val) {
                tmp = now -> next;
                now->next = now->next->next;
                delete tmp;
            } else {
                now = now->next;
            }
        }
        return head;
    }
};

int main(int argc, char** argv) {
    return 0;
}


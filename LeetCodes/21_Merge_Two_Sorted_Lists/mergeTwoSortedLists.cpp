/* Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Merge two sorted linked lists and return it as a new list. The new list 
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * ##########################################################################
 * 
 * File:   mergeTwoSortedLists.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode = 10 ms
 * Rank in leetCode = beats 99.90% in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>

static int ___ = []() {
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
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        struct ListNode *w, *head;
        w = (struct ListNode *)malloc(sizeof(struct ListNode));
        head = w;
        if (!l1) return l2;
        if (!l2) return l1;
    
        while (l1 && l2) { 
            if (l1->val < l2->val) { 
                w->next = l1;
                w = l1;
                l1 = l1->next;   
            } else { 
                w->next = l2;
                w = l2;
                l2 = l2->next;   
            }
        }
        if (l1) w->next = l1;
        if (l2) w->next = l2;
        return head->next;  
    }
};

int main(int argc, char* argv[]) {
    return 0;
}


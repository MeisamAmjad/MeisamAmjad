/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given two binary trees, write a function to check if they are the same 
 * or not.
 * 
 * Two binary trees are considered the same if they are structurally identical
 * and the nodes have the same value.
 * 
 * Example 1:
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 * 
 *         [1,2,3],   [1,2,3]
 * Output: true
 * 
 * Example 2:
 * Input:     1         1
 *           /           \
 *          2             2
 * 
 *         [1,2],     [1,null,2]
 * Output: false
 * 
 * Example 3:
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 * 
 *         [1,2,1],   [1,1,2]
 * Output: false
 * ##########################################################################
 * 
 * File:   sameTree.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  3 ms
 * Rank in leetCode = beats 98.87 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <queue>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if (!p && !q)
            return true;
        else if (!p || !q)
            return false;
        std::queue<TreeNode*> Q1; 
        std::queue<TreeNode*> Q2;
        Q1.push(p);
        Q2.push(q);
        while (!Q1.empty() && !Q2.empty()) {
            TreeNode *tmp1 = Q1.front();
            TreeNode *tmp2 = Q2.front();
            Q1.pop();
            Q2.pop();
            if (tmp1 && tmp2) {
                if (tmp1->val != tmp2->val)
                    return false;    
                Q1.push(tmp1->left);              
                Q1.push(tmp1->right);
                Q2.push(tmp2->left);
                Q2.push(tmp2->right);
            } else if (tmp1 != tmp2) {
                return false;
            }
        }
        return (Q1.empty() && Q2.empty());
    }
};

int main(int argc, char** argv) {
    TreeNode *T1 = new TreeNode(1);
    // T1->left = new TreeNode(2); 
    T1->right = new TreeNode(2); 
    
    TreeNode *T2 = new TreeNode(1);
    // T2->left = new TreeNode(NULL);
    T2->right = new TreeNode(2);
    
    Solution test;
    std::cout << test.isSameTree(T1, T2);
    return 0;
}


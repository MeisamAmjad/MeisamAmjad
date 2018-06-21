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
 * Time in leetCode =   ms
 * Rank in leetCode = beats  % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>

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
        std::vector<TreeNode*> Q1(1); 
        std::vector<TreeNode*> Q2(1);
        Q1[0] = p;
        Q2[0] = q;
        while (!Q1.empty() && !Q2.empty()) {
            if (Q1[0] && Q2[0]) {
                if (Q1[0]->val != Q2[0]->val)
                    return false;
                if (Q1[0]->left) Q1.insert(Q1.end(), Q1[0]->left); 
                if (Q1[0]->right) Q1.insert(Q1.end(), Q1[0]->right);
                if (Q2[0]->left) Q2.insert(Q2.end(), Q2[0]->left);
                if (Q2[0]->right) Q2.insert(Q2.end(), Q2[0]->right);
                Q1.erase(Q1.begin());
                Q2.erase(Q2.begin());
            } else {
                return false;
            }
        }
        return (Q1.empty() && Q2.empty());
    }
};

int main(int argc, char** argv) {
    TreeNode *T1 = new TreeNode(1);
    T1->left = new TreeNode(2); 
    T1->right = new TreeNode(3); 
    
    TreeNode *T2 = new TreeNode(1);
    T2->left = new TreeNode(2);
    T2->right = new TreeNode(3);
    
    Solution test;
    std::cout << test.isSameTree(T1, T2);
    return 0;
}


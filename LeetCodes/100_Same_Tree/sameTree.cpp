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


struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    bool isSameTree(TreeNode* p, TreeNode* q) {
        return 0;
    }
};

int main(int argc, char** argv) {
    return 0;
}


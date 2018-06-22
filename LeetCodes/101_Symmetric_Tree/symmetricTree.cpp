/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a binary tree, check whether it is a mirror of itself 
 * (ie, symmetric around its center).
 * 
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * ##########################################################################
 * 
 * File:   symmetricTree.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  4 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <queue>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return NULL;
}();

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    bool isSymmetric(TreeNode* root) {
        if (!root) return true;
        return helper(root->left, root->right);
    }
    bool helper(TreeNode* n1, TreeNode* n2) {
        if (!n1 || !n2) return n1 == n2;
        if (n1->val != n2->val) return false;
        return helper(n1->left, n2->right) && helper(n1->right, n2->left);
    }
};

int main(int argc, char* argv[]) {
    return 0;
}


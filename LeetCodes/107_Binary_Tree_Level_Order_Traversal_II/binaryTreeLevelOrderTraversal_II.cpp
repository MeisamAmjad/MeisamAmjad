/* Copyright (C) 2018 amjadm@miamiOH.edu
 * 
 * ##########################################################################
 * Given a binary tree, return the bottom-up level order traversal of its
 * nodes' values. (ie, from left to right, level by level from leaf to root).
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * ##########################################################################
 * 
 * File:   binaryTreeLevelOrderTraversal_II.cpp
 * 
 * Author: Meisam Amjad amjadm@miamioh.edu
 * 
 * Time in leetCode =  4 ms
 * Rank in leetCode = beats 100 % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <vector>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    std::vector<std::vector<int>> levelOrderBottom(TreeNode* root) {
        if (!root) return {};
        // Recording all Nodes in a vector O(n)
        int level = 0;
        std::vector<std::vector<TreeNode*>> S;
        S.push_back(std::vector<TreeNode*> {root});
        while (true) {
            std::vector<TreeNode*> tmp;
            for (TreeNode* node : S[level]) {
                if (node->left) tmp.push_back(node->left);
                if (node->right) tmp.push_back(node->right);
            }
            if (tmp.empty()) break;
            S.push_back(tmp);
            ++level;
        }
        // Producing result vector O(n)
        std::vector<std::vector<int>> result;
        for (int i = S.size() - 1; i >= 0; --i) {
            std::vector<int> tmp;
            for (TreeNode* node : S[i])
                tmp.push_back(node->val);
            result.push_back(tmp);
        }
        return result;
    }
};

int main(int argc, char* argv[]) {
    TreeNode* root = new TreeNode(0);
    root->left = new TreeNode(2);
    root->left->left = new TreeNode(1);
    root->left->left->left = new TreeNode(5);
    root->left->left->right = new TreeNode(1);
    
    root->right = new TreeNode(4);
    root->right->left = new TreeNode(3);
    root->right->right = new TreeNode(-1);
    root->right->left->right = new TreeNode(6);
    root->right->right->right = new TreeNode(8);
    
    Solution test;
    assert(test.levelOrderBottom(root) == 
      (std::vector<std::vector<int>> {{5, 1, 6, 8}, {1, 3, -1}, {2, 4}, {0}}));
    return 0;
}


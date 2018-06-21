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
 * Time in leetCode =   ms
 * Rank in leetCode = beats  % in that runtime range.
 */

#include <assert.h>
#include <iostream>
#include <queue>

static auto ___ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    return NULL;
}();

int main(int argc, char* argv[]) {
    return 0;
}


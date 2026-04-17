package com.codewithlfn;

public class AVLNode {
    int key;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int key) {
        this.key = key;
        this.height = 1; // New nodes are initially added at leaf position
    }
}

package com.codewithlfn;

public class AVLNode {
    int courseCode;
    int nodeHeight;
    AVLNode leftChild, rightChild;

    public AVLNode(int courseCode) {
        this.courseCode = courseCode;
        this.nodeHeight = 1; // New nodes start at height 1
    }
}

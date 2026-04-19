package com.codewithlfn;

public class AVLTree {
    AVLNode treeRoot;

    // how tall is this node
    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.nodeHeight;
    }

    // is the tree leaning more to the left or right
    private int getBalance(AVLNode node) {
        return node == null ? 0 : getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    // update the height after we make changes
    private void updateHeight(AVLNode node) {
        node.nodeHeight = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
    }

    // the tree is too heavy on the left so we tip it to the right
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.leftChild;
        AVLNode temp = x.rightChild;
        x.rightChild = y;
        y.leftChild = temp;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // the tree is too heavy on the right so we tip it to the left
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.rightChild;
        AVLNode temp = y.leftChild;
        y.leftChild = x;
        x.rightChild = temp;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // after adding or removing a course we check if anything is off balance
    private AVLNode rebalance(AVLNode node, int courseCode) {
        updateHeight(node);
        int balance = getBalance(node);

        // too many nodes stacking on the left side
        if (balance > 1 && courseCode < node.leftChild.courseCode) {
            return rotateRight(node);
        }

        // too many nodes stacking on the right side
        if (balance < -1 && courseCode > node.rightChild.courseCode) {
            return rotateLeft(node);
        }

        // left side has a node that belongs more to the right
        if (balance > 1 && courseCode > node.leftChild.courseCode) {
            node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }

        // right side has a node that belongs more to the left
        if (balance < -1 && courseCode < node.rightChild.courseCode) {
            node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }

        // tree looks fine, nothing to do
        return node;
    }

    // registers a new course into the system
    public void insert(int courseCode) {
        treeRoot = insertNode(treeRoot, courseCode);
    }

    private AVLNode insertNode(AVLNode node, int courseCode) {
        if (node == null) {
            return new AVLNode(courseCode);
        }

        if (courseCode < node.courseCode) {
            node.leftChild = insertNode(node.leftChild, courseCode);
        } else if (courseCode > node.courseCode) {
            node.rightChild = insertNode(node.rightChild, courseCode);
        } else {
            return node; // this course is already in the system
        }

        return rebalance(node, courseCode);
    }

    // drops a course from the system
    public void delete(int courseCode) {
        treeRoot = deleteNode(treeRoot, courseCode);
    }

    private AVLNode deleteNode(AVLNode node, int courseCode) {
        if (node == null) {
            return null;
        }

        if (courseCode < node.courseCode) {
            node.leftChild = deleteNode(node.leftChild, courseCode);
        } else if (courseCode > node.courseCode) {
            node.rightChild = deleteNode(node.rightChild, courseCode);
        } else {
            // we found the course, now remove it
            if (node.leftChild == null) {
                return node.rightChild;
            }
            if (node.rightChild == null) {
                return node.leftChild;
            }

            // node has two children so we swap it with the next one in line
            AVLNode smallest = getSmallestNode(node.rightChild);
            node.courseCode = smallest.courseCode;
            node.rightChild = deleteNode(node.rightChild, smallest.courseCode);
        }

        return rebalance(node, courseCode);
    }

    // keep going left until we hit the smallest value
    private AVLNode getSmallestNode(AVLNode node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    // prints all courses from smallest to largest
    public void printInOrder() {
        printInOrder(treeRoot);
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.leftChild);
            System.out.print("Course " + node.courseCode + "  ");
            printInOrder(node.rightChild);
        }
    }
}
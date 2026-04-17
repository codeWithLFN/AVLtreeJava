package com.codewithlfn;

public class AVLTree {
    AVLNode root;

    // Helper function to get the height of a node
    int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    int getBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Right rotation for a left-left case
    AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotation for a right-right case
    AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert method
    AVLNode insert(AVLNode root, int key) {
        if (root == null) {
            return new AVLNode(key);
        }

        if (key < root.key) {
            root.left = insert(root.left, key);
        }else if (key > root.key) {
            root.right = insert(root.right, key);
        } else {
            return root; // Duplicate keys are not allowed
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalanceFactor(root);
        if (balance > 1 && key < root.left.key) {
            return rotateRight(root);
        } else if (balance < -1 && key > root.right.key) {
            return rotateLeft(root);
        } else if (balance > 1 && key > root.left.key) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        } else if (balance < -1 && key < root.right.key) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }

    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    // Delete method
    AVLNode delete(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // Node with only one child or no child
            if ((node.left == null) || (node.right == null)) {
                AVLNode temp;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                node = null;
                return temp;
            } else {
                // Node with two children
                AVLNode temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalanceFactor(node);

        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        } else if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        } else if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } else if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Method to visually print the tree using standard ASCII characters
    void printTree(AVLNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "+-- " : "\\-- ") + node.key);

            // Recursively print left and right children
            printTree(node.left, prefix + (isLeft ? "|   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "|   " : "    "), false);
        }
    }
}

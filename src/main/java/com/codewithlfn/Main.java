package com.codewithlfn;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Showing the insertion of elements
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 25);

        System.out.println("AVL Tree structure after insertion:");
        tree.printTree(tree.root, "", false);

        // Showing the deletion of 20
        tree.root = tree.delete(tree.root, 20);

        System.out.println("\nAVL Tree structure after deleting 20:");
        tree.printTree(tree.root, "", false);

    }
}
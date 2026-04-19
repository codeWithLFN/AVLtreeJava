package com.codewithlfn;

public class Main {
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        // lets add some courses to the system
        System.out.println("--- Registering Courses ---");
        tree.insert(1012);
        tree.insert(2045);
        tree.insert(3078);
        tree.insert(1055);
        tree.insert(4090);
        System.out.println("done, all courses have been added.\n");

        // show what we have so far in order
        System.out.print("courses currently in the system: ");
        tree.printInOrder();
        System.out.println("\n");

        // now lets remove one and see what happens
        System.out.println("--- removing course 2045 from the system ---");
        tree.delete(2045);

        // print again to confirm it was removed
        System.out.print("courses still registered: ");
        tree.printInOrder();
        System.out.println();
    }
}
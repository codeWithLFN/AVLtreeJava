package com.codewithlfn;

public class Main {
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        // lets add some courses to the system
        System.out.println("--- Registering Courses ---");
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(25);
        System.out.println("done, all courses have been added.\n");

        // show sorted list
        System.out.print("courses currently in the system: ");
        tree.printInOrder();
        System.out.println("\n");

        // now lets remove one and see what happens
        System.out.println("--- removing course 10 from the system ---");
        tree.delete(10);

        // confirm what is still registered
        System.out.print("courses still registered: ");
        tree.printInOrder();
        System.out.println();
    }
}
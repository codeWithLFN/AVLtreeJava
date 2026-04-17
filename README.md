# AVL Tree Implementation in Java 🌳

Welcome to my **AVL Tree** project! This repository contains a clean, fully commented Java implementation of a self-balancing Binary Search Tree (BST) known as an AVL Tree. 

I built this to deeply understand how self-balancing trees work, specifically focusing on **insertion, deletion, and rotations**. 

---

## 🧠 What is an AVL Tree?

A standard Binary Search Tree (BST) is great, but if you insert sorted data (like `10, 20, 30, 40`), it turns into a straight line (a linked list). When that happens, searching for a number becomes very slow — **O(n)** time instead of the fast **O(log n)** we want.

An **AVL Tree** fixes this problem. Every time you insert or delete a node, the tree checks itself. If one side gets too heavy, the tree automatically "rotates" to stay perfectly balanced!

---

## ⚖️ How Does It Balance Itself?

Every node in the tree keeps track of two things:
1. **Height:** How far it is from the bottom leaf.
2. **Balance Factor:** The difference in height between its Left child and Right child.  
   - `Balance Factor = Height(Left) - Height(Right)`

If the Balance Factor ever becomes **greater than 1** or **less than -1**, the tree is unbalanced!

---

## 🔄 The 4 Rotations (With Diagrams!)

To fix imbalance, AVL trees use four rotations:

---

### 1. Right Rotation (Left-Left Case)

- **Problem:** Left-heavy (insertion in left-left path)
- **Fix:** Rotate right

```text
Before (Left-Heavy)            After Right Rotation

        30                              20
       /  \                            /  \
     20    40        --->            10    30
    /  \                                  /  \
  10   25                               25   40
```

---

### 2. Left Rotation (Right-Right Case)

- **Problem:** Right-heavy (insertion in right-right path)
- **Fix:** Rotate left

```text
Before (Right-Heavy)           After Left Rotation

        10                              20
       /  \                            /  \
      5    20        --->            10    30
          /  \                      /  \
        15   30                   5   15
```

---

### 3. Left-Right Rotation (LR Case)

- **Problem:** Left subtree is right-heavy
- **Fix:** Left rotate child, then right rotate root

```text
Before                 Step 1 (Left Rotate on 10)     Step 2 (Right Rotate on 30)

        30                        30                              20
       /                         /                               /  \
     10            --->        20               --->           10    30
       \                      /
       20                   10
```

---

### 4. Right-Left Rotation (RL Case)

- **Problem:** Right subtree is left-heavy
- **Fix:** Right rotate child, then left rotate root

```text
Before                 Step 1 (Right Rotate on 30)    Step 2 (Left Rotate on 10)

        10                        10                              20
          \                         \                            /  \
           30         --->          20           --->          10    30
          /                           \
         20                            30
```

---

## 🛠️ Project Structure

This project uses two separate classes for clean, object-oriented design:

### `AVLNode.java`
Represents a single node:
- `key`: Data value
- `height`: Node height
- `left`, `right`: Child pointers

### `AVLTree.java`
Core logic:
- `insert(key)`
- `delete(key)`
- `rotateLeft()` / `rotateRight()`
- `printTree()`

### `Main.java`
Test driver:
- Inserts nodes
- Prints tree
- Deletes nodes
- Verifies balancing

---

## 🚀 How to Run It

1. Clone the repository  
2. Open in IntelliJ / Eclipse / VS Code  
3. Run `Main.java`  
4. Observe balancing in console output  

---

## 💡 What I Learned

- The hardest part is handling **orphaned subtrees** during rotation (e.g., `25`, `15`)
- Rotations are just pointer reassignments — but must be precise
- Recursion naturally supports bottom-up balancing

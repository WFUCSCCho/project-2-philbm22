/********************************************************************************
 ∗ @file: BST.java
 ∗ @description: This file implements and maintains the Binary Search Tree structure
 ∗ @author: Benton Phillips
 ∗ @date: October 24 , 2024
 *********∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

// Implement the constructor
class BST<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> root;
    private int nodecount;
    private String endString = "";

    //Constructor for BST that sets an empty tree
    public BST() {
        root = null;
        nodecount = 0;
    }

    // Implement the clear method
    //Clears the whole tree by setting the root to null and nodecount to 0
    public void clear() {
        root = null;
        nodecount = 0;
    }

    // Implement the size method
    //Returns the amount of nodes in the BST
    public int size() {
        return nodecount;
    }

    // Implement the insert method
    //Adds a node to the BST
    public void insert(E key) {
        root = insertHelp(root, key);
        nodecount++;
    }

    // Implement the remove method
    //Removes a node from the BST
    public E remove(E key) {
        E temp = searchHelp(root, key);
        if (temp != null) {
            root = removeHelp(root, key);
            nodecount--;
        }
        return temp;
    }

    // Implement the search method
    //Finds a node that matches the value of the key value
    public E search(E key) {
        return searchHelp(root, key);
    }

    //Builds the string to print the BST in order
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : this) {
            sb.append(e).append(" ");
        }
        return sb.toString().trim();
    }


    // Implement the iterator method
    //Returns a BST iterator
    @Override
    public Iterator<E> iterator() {
        return new BSTIterator(root);
    }

    //Returns a node that matches the value of a key, searches for a particular node
    private E searchHelp(Node<E> root, E key) {
        if (root == null) {
            return null;
        }

        if (root.getElement().compareTo(key) > 0) {
            return searchHelp(root.getLeft(), key);
        } else if (root.getElement().compareTo(key) == 0) {
            return root.getElement();
        } else {
            return searchHelp(root.getRight(), key);
        }
    }

    //Inserts a node into the BST based on the rules of a BST
    private Node<E> insertHelp(Node<E> root, E key) {
        if (root == null) {
            return new Node<E>(key);
        }
        if (root.getElement().compareTo(key) > 0) {
            root.setLeft(insertHelp(root.getLeft(), key));
        } else {
            root.setRight(insertHelp(root.getRight(), key));
        }
        return root;
    }

    //Removes a node based on the rules of BST
    private Node<E> removeHelp(Node<E> root, E key) {
        if (root == null) {
            return null;
        }

        if (root.getElement().compareTo(key) > 0) {
            root.setLeft(removeHelp(root.getLeft(), key));
        } else if (root.getElement().compareTo(key) < 0) {
            root.setRight(removeHelp(root.getRight(), key));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            } else {
                Node<E> temp = getMax(root.getLeft());
                root.setElement(temp.getElement());

                root.setLeft(deleteMax(root.getLeft()));
            }
        }

        return root;
    }

    //Gets the maximum node in the BST
    private Node<E> getMax(Node<E> root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    //Deletes the maximum node in the BST
    private Node<E> deleteMax(Node<E> root) {
        if (root.getRight() == null) {
            return root.getLeft();
        }


        root.setRight(deleteMax(root.getRight()));
        return root;
    }

    // Implement the BSTIterator class
    //Iterator for the BST to allow for easy traversal of BST nodes
    private class BSTIterator implements Iterator<E> {
        private Stack<Node<E>> stack;

        //Constructor for the BSTIterator
        public BSTIterator(Node<E> root) {
            stack = new Stack<>();
            pushLeft(root);
        }

        //Push all left nodes onto the stack
        private void pushLeft(Node<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        //Checks to see if stack is empty
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        //Returs the next element in the stack
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<E> node = stack.pop();
            E result = node.getElement();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return result;
        }
    }
}

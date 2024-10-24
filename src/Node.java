/********************************************************************************
 ∗ @file: Node.java
 ∗ @description: File that creates a node used in the BST
 ∗ @author: Benton Phillips
 ∗ @date: October  24, 2024
 *********∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/

// Implement the constructor
public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    //Constructor for the Node class
    Node(T value) {
        this.value = value;
        left = right = null;
    }

    // Implement the setElement method
    // sets the value of the node
    public void setElement(T value) {
        this.value = value;
    }

    // Implement the setLeft method
    //Sets the pointer of the node's left child
    public void setLeft(Node<T> left) {this.left=left;}

    // Implement the setRight method
    //Sets the pointer of the node's right child
    public void setRight(Node<T> right) {this.right=right;}

    // Implement the getLeft method
    //Gets the node's left child
    public Node<T> getLeft(){
        return left;
    }

    // Implement the getRight method
    //Gets the node's right child
    public Node<T> getRight(){
        return right;
    }

    // Implement the getElement method
    //Returns the value of node
    public T getElement() {
        return value;
    }

    // Implement the isLeaf method
    //Checks to see if the node is a leaf (has no children)
    public boolean isLeaf(){
        return left == null && right == null;
    }
}

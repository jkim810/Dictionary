/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Junbum
 */
public class Node {
    private Node left;
    private Node right;
    private Vocabulary value = null;

    public Node(){
        
    }
    
    public Node(Vocabulary Obj) {
        value = Obj;
        left = null;
        right = null;
    }

    public boolean remove(String value, Node parent) {
        //Searching leftChild since the value is lower
        if (value.compareTo(this.value.word) < 0) {
            if (left != null) {
                return left.remove(value, this);
            } else {
                return false;
            }
            //Searching rightChild since the value is greater
        } else if (value.compareTo(this.value.word) > 0) {
            if (right != null) {
                return right.remove(value, this);
            } else {
                return false;
            }
            //found the word to delete
        } else {
            if (left != null && right != null) {
                //Overwrite the value with the smallest value in the rightChild.
                this.value = right.minValue();
                //Disconnect the smallest value in the rightChild.
                right.remove(this.value.word, this);
            } //The leftChild of parent is the one we are finding. There is no rightChild of parent since we checked in the previous case.
            //If left of the one we have to delete is empty, connect the right to the parent and vice versa.
            else if (parent.left == this) {
                parent.left = (left != null) ? left : right;
            } //The rightChild of parent is the one we are finding. There is no leftChild of parent since we checked in the previous case.
            //If left of the one we have to delete is empty, connect the right to the parent and vice versa.
            else if (parent.right == this) {
                parent.right = (left != null) ? left : right;
            }
            return true;
        }
    }

    //finds the left most branch of the tree
    public Vocabulary minValue() {
        if (left == null) {
            return value;
        } else {
            return left.minValue();
        }
    }

    public Vocabulary getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setValue(Vocabulary obj) {
        this.value = obj;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

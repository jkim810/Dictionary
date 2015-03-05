/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Junbum
 */
public class BinaryTree {
    private Node root = null;
    //starting node of the tree
    public int numberOfWords = 0;
    //Keeps track of how many words there are. This is neccesary for setting up the table.

    public Node getRoot() {
        return root;
    }

    //Initiates the Tree
    public BinaryTree() {
        
    }
    
    public boolean isEmpty(){
        if(root == null){
            return true;
        } else {
            return false;
        }
    }

    //Add Tree
    public void addToTree(Vocabulary obj) {
        numberOfWords++;
        //case1. root is null
        if(isEmpty()){
            root = new Node(obj);
            //case2. root is taken
        } else {
            Node nodeToAdd, travellerNode, travellerBackup;
            travellerNode = travellerBackup = root;
            nodeToAdd = new Node(obj);
            while (travellerNode != null) {
                travellerBackup = travellerNode;
                //recursively track down the node we need to add the child to
                if (nodeToAdd.getValue().getName().compareTo(travellerNode.getValue().getName()) > 0) {
                    travellerNode = travellerNode.getRight();
                } else {
                    travellerNode = travellerNode.getLeft();
                }
            }
            //add the node either to the left or to the right.
            if (nodeToAdd.getValue().getName().compareTo(travellerBackup.getValue().getName()) > 0) {
                travellerBackup.setRight(nodeToAdd);
            } else {
                travellerBackup.setLeft(nodeToAdd);
            }
        }
    }
    //Printing off the Data in Tree. This part will be saved off into a file and be reloaded when the program is called.
    private String inOrderString;
    
    
    public String traverseInOrder() {
        inOrderString = "";
        return traverseInOrderHelper(root, "");
    }

    //Traverse helper, the actual part that traverses the methods
    private String traverseInOrderHelper(Node p, String s) {
        if (p != null) {
            traverseInOrderHelper(p.getLeft(), inOrderString);
            inOrderString += p.getValue().word + ":" + p.getValue().meaning+ "\n";
            traverseInOrderHelper(p.getRight(), inOrderString);
        }
        return inOrderString;
    }
    
    //Searching the Tree
    private Vocabulary foundVocabulary;

    //Initiating the Search - similar to the traverse but instead of adding to the String it just checks the parity of the key to data recorded.
    public Vocabulary search(String s) {
        foundVocabulary = new Vocabulary();
        if (!isEmpty()) {
            searchHelper(root, s);
        }
        return foundVocabulary;
    }
  

    //Search helper
    private void searchHelper(Node p, String name) {
        if (p != null) {
            searchHelper(p.getLeft(), name);
            searchHelper(p.getRight(), name);
            if (p.getValue() == null) {
                //when the root is empty
            } else if (p.getValue().getName().equals(name)) {
                foundVocabulary = p.getValue();
            }
        }
    }
     

    //Removing
    //A initiator for remove button
    public boolean remove(String value) {
        numberOfWords--;
        if (isEmpty()) {
            return false;
            //When the deleting Node is the root.
        } else if (root.getValue().word.equals(value)) {
            Node auxRoot = new Node();
            auxRoot.setLeft(root);
            boolean result = root.remove(value, auxRoot);
            root = auxRoot.getLeft();
            return result;
        } else {
            return root.remove(value, null);
        }
    }
}

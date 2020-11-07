/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreetutorial;

/**
 *
 * @author fifiteklemedhin
 */
public class BinaryTree {

    Node root;
    
    public static void main(String[] args)
    {
        BinaryTree theTree = new BinaryTree();
        
        theTree.addNode(50, "Boss");
        theTree.addNode(25, "VP");
        theTree.addNode(15, "Office man");
        theTree.addNode(2, "Int");
        theTree.addNode(18, "Data Entry");
        theTree.addNode(30, "Secret");
        theTree.addNode(75, "SalesManag");
        theTree.addNode(70, "Sakesman2");
        theTree.addNode(85, "Sakesman1");
        
        //theTree.inOrderTraverseTree(theTree.root);
        //theTree.preOrderTraverseTree(theTree.root);
        //theTree.postOrderTraverseTree(theTree.root);
        //System.out.println("Search for 30");
        //System.out.println(theTree.findNode(30));
        
        System.out.println("REMOVE KEY 25");
        theTree.remove(25);
        theTree.preOrderTraverseTree(theTree.root);
    }
    public boolean remove(int key)
    {
        Node focusNode = root;
        Node parent = root;
        
        boolean isItALeftChild = true;
        
        while(focusNode.key != key)
        {
            parent = focusNode;
            if(key < focusNode.key)
            {
                isItALeftChild = true;
                focusNode = focusNode.leftChild;
            }
            else
            {
                isItALeftChild = true;
                focusNode = focusNode.leftChild;
            }
            if(focusNode == null)
                return false;

        }
         //if leafnode or they don't have children
            if(focusNode.leftChild == null && focusNode.rightChild == null)
            {
                if(focusNode == root)
                {
                    root = null;
                }
                else if(isItALeftChild)
                {
                    parent.leftChild = null;
                }
                else
                    parent.rightChild = null;
            
            }
            else if(focusNode.rightChild == null)
            {
                if(focusNode == root)
                    root = focusNode.leftChild;
                else if(isItALeftChild)
                {
                    parent.leftChild = null;
                }
                else
                    parent.rightChild = null;
            }
            else if(focusNode.rightChild == null)
            {
                if(focusNode == root)
                    root = focusNode.leftChild;
                else if(isItALeftChild)
                    parent.leftChild = focusNode.leftChild;
                else parent.rightChild = focusNode.leftChild;
            }
            
            else if(focusNode.leftChild == null)
                if(focusNode == root)
                    root = focusNode.rightChild;
                else if(isItALeftChild)
                    parent.leftChild = focusNode.rightChild;
                else
                    parent.rightChild = focusNode.leftChild;
            else 
            {
                Node replacement = getReplacementNode(focusNode);
                
                if(focusNode == root)
                    root = replacement;
                else if(isItALeftChild)
                    parent.leftChild = replacement;
                else
                    parent.rightChild = replacement;
                replacement.leftChild = focusNode.leftChild;
            }
     return true;
        
    }
    public Node getReplacementNode(Node replacedNode)
    {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        
        Node focusNode = replacedNode.rightChild; //starts off on right
        
        while(focusNode != null)
        {
            replacementParent = replacement; //goes to parent of leftmost
            replacement = focusNode; //goes to leftmost, one step behind being null
            focusNode = focusNode.leftChild; 
        }
        
        if(replacement != replacedNode.rightChild) // If the replacement isn't the right child move the replacement into the parents leftChild slot and move the replaced nodes right child into the replacements rightChild 
        {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }
        
        return replacement;
    }
    public void addNode(int key, String name)
    {
        Node newNode = new Node(key, name);
        
        if(root == null)
        {
            root = newNode;
        }
        else
        {
            Node focusNode = root;
            Node parent;
            
            while(true)
            {
                parent = focusNode;
                
                if(key < focusNode.key)
                {
                    focusNode = focusNode.leftChild;
                    
                    if(focusNode == null)
                    {
                        parent.leftChild = newNode;
                        return;
                    }
                }
                
                else 
                {
                    focusNode = focusNode.rightChild;
                    
                    if(focusNode == null)
                    {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }
    public Node findNode(int key)
    {
        Node focusNode = root;
        
        while(focusNode.key != key)
        {
            if(key < focusNode.key)
            {
                focusNode = focusNode.leftChild;
            }
            else
            {
                focusNode = focusNode.rightChild;
            }
            if(focusNode == null)
                return null;
        }
        return focusNode;
    }
    public void inOrderTraverseTree(Node focusNode)
    {
        if(focusNode != null)
        {
            inOrderTraverseTree(focusNode.leftChild);
            
            System.out.println(focusNode);
            
            inOrderTraverseTree(focusNode.rightChild);
        }
    }
    
    public void preOrderTraverseTree(Node focusNode)
    {
        if(focusNode != null)
        {
            System.out.println(focusNode);
            preOrderTraverseTree(focusNode.leftChild);
            preOrderTraverseTree(focusNode.rightChild);
        }
    }
    
    public void postOrderTraverseTree(Node focusNode)
    {
        if(focusNode != null)
        {
            postOrderTraverseTree(focusNode.leftChild);
            postOrderTraverseTree(focusNode.rightChild);
            System.out.println(focusNode);
        }
    }
    
}

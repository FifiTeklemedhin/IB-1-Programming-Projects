/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avlbinarytree;

import APClasses.APConsole;


/**
 *
 * @author fifiteklemedhin
 */
public class AVLBinaryTree<E extends Comparable> extends APBinaryTree{

    public AVLBinaryTree()
    {
        super();
    }
   
   
    public void rightRotation(Node rotatedNode)
    {
         rightRotation(rotatedNode, rotatedNode.parent);
    }
    
    public void rightRotation(Node rotatedNode, Node parent)
    {
        if(parent == null)
            return;
        
        if(parent == this.getRoot())
            this.root = rotatedNode;
        
        // if they have one, assigns the rotating node's right child to become the parent's left child 
        if(rotatedNode.right != null)
        {
            parent.left = rotatedNode.right;
        }
        else
            parent.left = null;
        
        // assigns the rotating node's parent to become its right child 
        rotatedNode.right = parent;
        
         // if they have one, assigns rotated node to have the parent's parent
        if(parent.parent != null)
        {
            rotatedNode.parent = parent.parent;
        }
        else
            parent = null;
        
        //sets the old parent's parent to be the rotated node
        rotatedNode.right.parent = rotatedNode;
    }
    
    public void leftRotation(Node rotatedNode)
    {
        leftRotation(rotatedNode, rotatedNode.parent);
    }
    
    public void leftRotation(Node rotatedNode, Node parent)
    {
        if(parent == null)
            return;
        
        if(parent == this.getRoot())
            this.root = rotatedNode;
        
        // if they have one, assigns the rotating node's left child to become the parent's right child 
        if(rotatedNode.left != null)
        {
            parent.right = rotatedNode.left;
        }
        else
            parent.right = null;
        
        // assigns the rotating node's parent to become its left child 
        rotatedNode.left = parent;
        
        // if they have one, assigns rotated node to have the parent's parent
        if(parent.parent != null)
        {
            rotatedNode.parent = parent.parent;
        }
        else
            parent = null;
        
        //sets the old parent's parent to be the rotated node
        rotatedNode.left.parent = rotatedNode;
    }
    
    
    public boolean rightRotationWorks(Node newRoot, Node oldRoot)
    {
        // all of these conditions have to be false in order for the right rotation to work
        return !(Math.abs(newRoot.rightHeight() - oldRoot.rightHeight()) > 1) // When rotating, these two will have the old root as a parent, so if they are not balanced, the rotation will not be effective
               && !(Math.abs(newRoot.leftHeight() - newRoot.rightHeight()) > 1) // The new root is the one that is unbalanced, so its left and right height need to be unbalanced 
               && !(oldRoot.rightHeight() + 1 > newRoot.leftHeight()); //When rotating, the left children of the new and old roots become siblings, so they must be balanced. 
                                                                      // The old root's right child will have a height increase of 1 since it will become it's parent's root
    }
    private boolean leftRotationWorks(Node newRoot, Node oldRoot)
    {
        // all of these conditions have to be false in order for the right rotation to work
        return !(Math.abs(newRoot.leftHeight() - oldRoot.leftHeight()) > 1) // When rotating, these two will have the old root as a parent, so if they are not balanced, the rotation will not be effective
               && !(Math.abs(newRoot.leftHeight() - newRoot.rightHeight()) > 1) // The new root is the one that is unbalanced, so its left and right height need to be unbalanced 
               && !(oldRoot.leftHeight() + 1 > newRoot.rightHeight()); //When rotating, the left children of the new and old roots become siblings, so they must be balanced. 
                                                                      // The old root's right child will have a height increase of 1 since it will become it's parent's root
    }
    
}

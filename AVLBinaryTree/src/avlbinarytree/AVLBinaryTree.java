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
 * @param <E>
 */
public class AVLBinaryTree<E extends Comparable> extends APBinaryTree {

    public AVLBinaryTree()
    {
        super();
    }
   
    public void rightRotation(Node rotatedNode)
    {
         rightRotation(rotatedNode, rotatedNode.parent);
    }
    public void leftRotation(Node rotatedNode)
    {
        leftRotation(rotatedNode, rotatedNode.parent);
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
    
    public void doubleRightRotation(Node imbalancedNode) //rotates grandchild to the right
    {
        //what do you do if no grandchildren are unbalanced?
        //what if the child node is not imbalanced? Does grandchildren refer to any node below the child of the imbalanced node?
        
        System.out.println(imbalancedNode.left != null);
        System.out.println(!imbalancedNode.left.isBalanced());
        System.out.println(imbalancedNode.left.right.height() + ", " +  imbalancedNode.left.left.height());
         
        Node rotatingGrandchild;
        if(imbalancedNode.left != null) //if child isn't null
        {
            if(!imbalancedNode.left.isBalanced()) //if there is a grandchild that is unbalanced
            {
                if(imbalancedNode.left.right.height() > imbalancedNode.left.left.height()) //finds the imbalanced grandchild
                    rotatingGrandchild = imbalancedNode.left.right; 
                else
                    rotatingGrandchild = imbalancedNode.left.left; 
                
                rightRotation(rotatingGrandchild); //rotates to the right
            }
        }  
        
    }
    
    public void doubleLeftRotation(Node imbalancedNode) //rotates grandchild to the left
    {
        //what do you do if no grandchildren are unbalanced?
        //what if the child node is not imbalanced? Does grandchildren refer to any node below the child of the imbalanced node?
        
        Node rotatingGrandchild;
        if(imbalancedNode.right != null) //if child isn't null
        {
            if(!imbalancedNode.right.isBalanced()) //if there is a grandchild that is unbalanced
            {
                if(imbalancedNode.right.right.height() > imbalancedNode.right.left.height()) //finds the imbalanced grandchild
                    rotatingGrandchild = imbalancedNode.right.right; 
                else
                    rotatingGrandchild = imbalancedNode.right.left; 
                
                leftRotation(rotatingGrandchild); //rotates to the left
            }
        }        
    }
    

    public void balance(Node newRoot) //balances a node
    {
        if(newRoot == this.root || this.root.isBalanced())
            return;
        
        if(newRoot.parent.left == newRoot) //if left child, do right rotation
        {
            if(rightRotationWorks(newRoot, this.root)) //do single rotation if possible, else do double
                rightRotation(newRoot);
            else
                doubleRightRotation(newRoot);
        }
        
        else //else is right child, do left rotation
        {
            if(leftRotationWorks(newRoot, this.root)) //do single rotation if possible, else do double
                leftRotation(newRoot);
            else
                doubleLeftRotation(newRoot);
        }
        
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
    
      public E add(E data)
    {
        return (E) addFrom(this.root, data, this.root);
    }
    
    private E addFrom(Node<E> currentNode, E data, Node parent)
    {
        
        if (currentNode.data == null) 
        {
            root = new Node(data);
            size += 1;
            balance(currentNode);
            balance(currentNode);
        }
        
        if(currentNode == null)
        {
            currentNode = new Node(data, parent);
            balance(currentNode); 
            return data;
        }

        int compare = data.compareTo(currentNode.data);
        if(compare == 1)
        {
            if(currentNode.right == null)
            {
                if(parent != null)
                {
                    currentNode.right = new Node(data, parent);
                }
                else
                    currentNode.right = new Node(data);
                size += 1;
                balance(currentNode); 
                return data;
            }
            addFrom(currentNode.right, data, currentNode);
        }
        else if(compare == -1)
        {
            if(currentNode.left == null)
            {
                if(parent != null)
                {
                    currentNode.left = new Node(data, parent);
                }
                else
                    currentNode.left = new Node(data);
                
                size += 1;
                balance(currentNode); 
                return data;
            }
            addFrom(currentNode.left, data, currentNode);
        }
        // if it exists already, then add it to the subtree of the root that has the smallest length
        else
        {
            if(subtreeLength(currentNode.left) > subtreeLength(currentNode.right))
            {
                if(currentNode.right == null)
                {
                    if(parent != null)
                    {
                        currentNode.right = new Node(data, parent);
                    }
                    
                    else
                        currentNode.right = new Node(data);
                    balance(currentNode); 
                    return data;
                }
                else
                    addFrom(currentNode.right, data, currentNode);
            }
            else
            {
                if(currentNode.left == null)
                {
                    if(parent != null)
                    {
                        currentNode.left = new Node(data, parent);
                    }
                    else
                        currentNode.left = new Node(data);
                    balance(currentNode); 
                    return data;
                }

                else
                    addFrom(currentNode.left, data, currentNode);
            }
        }
       
        return null;
    
        
    }
    
   
}

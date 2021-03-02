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
public class AVLBinaryTree extends APBinaryTree{

      /*
        TODO:
            * print tree
            * get height of a given node
            * 
    */
    
  
    
    public AVLBinaryTree()
    {
        super();
    }
    
    public static void main(String[] args)
    {
        AVLBinaryTree tree = new AVLBinaryTree();
        APConsole console = new APConsole("AVL Tree Tester");
        
        tree.add(3);
        tree.add(2);
        tree.add(1);
        
        
        tree.printTree(tree.root, 3, 3);
        console.println("root height: " + tree.root.height());
        console.println(rightRotationWorks(tree.root.left, tree.root)); // should work for adding 3,2,1
    }
    
    public static boolean rightRotationWorks(Node newRoot, Node oldRoot)
    {
        // all of these conditions have to be false in order for the right rotation to work
        return !(Math.abs(newRoot.rightHeight() - oldRoot.rightHeight()) > 1) // When rotating, these two will have the old root as a parent, so if they are not balanced, the rotation will not be effective
               && !(Math.abs(newRoot.leftHeight() - newRoot.rightHeight()) > 1) // The new root is the one that is unbalanced, so its left and right height need to be unbalanced 
               && !(oldRoot.rightHeight() + 1 > newRoot.leftHeight()); //When rotating, the left children of the new and old roots become siblings, so they must be balanced. 
                                                                      // The old root's right child will have a height increase of 1 since it will become it's parent's root
    }
    private static boolean leftRotationWorks(Node newRoot, Node oldRoot)
    {
        // all of these conditions have to be false in order for the right rotation to work
        return !(Math.abs(newRoot.leftHeight() - oldRoot.leftHeight()) > 1) // When rotating, these two will have the old root as a parent, so if they are not balanced, the rotation will not be effective
               && !(Math.abs(newRoot.leftHeight() - newRoot.rightHeight()) > 1) // The new root is the one that is unbalanced, so its left and right height need to be unbalanced 
               && !(oldRoot.leftHeight() + 1 > newRoot.rightHeight()); //When rotating, the left children of the new and old roots become siblings, so they must be balanced. 
                                                                      // The old root's right child will have a height increase of 1 since it will become it's parent's root
    }
    
}

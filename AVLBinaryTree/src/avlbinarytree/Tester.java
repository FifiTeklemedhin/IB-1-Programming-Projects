/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avlbinarytree;

import APClasses.APConsole;
import avlbinarytree.AVLBinaryTree;/**
 *
 * @author fifiteklemedhin
 */
public class Tester 
{
    
    public static void main(String[] args)
    {
        AVLBinaryTree tree = new AVLBinaryTree();
        APConsole console = new APConsole("AVL Tree Tester");
        
        
        
        // printing + getting height: works
            tree.add(3);
            tree.add(2);
            tree.add(1);    
            tree.printTree(tree.root, 3, 3, console);
            
            console.println("root height: " + tree.root.height());
        
        //checking whether rotations would help: works
            console.println(tree.rightRotationWorks(tree.root.left, tree.root)); // should work for adding 3,2,1
        
        // right rotations: work
            tree = new AVLBinaryTree();
            tree.add(7);
            tree.add(8);
            tree.add(4);
            tree.add(5);
            tree.add(3);

            tree.rightRotation(tree.root.left);
            tree.printTree(tree.root, 10, 10);
            
        // left rotations: work
            tree = new AVLBinaryTree();
            tree.add(7);
            tree.add(9);
            tree.add(8);
            tree.add(11);
            tree.add(4);

            tree.leftRotation(tree.root.right);
            tree.printTree(tree.root, 10, 10);
            
        //right double rotations
            tree = new AVLBinaryTree();
            tree.add(47);
            tree.add(51);
            tree.add(60);
            tree.add(50);
            tree.add(30);
            tree.add(40);
            tree.add(42);
            tree.add(29);
            tree.add(45);

            tree.doubleRightRotation(tree.root);
            tree.printTree(tree.root, 10, 10);
            
            System.out.println(tree.root.left);
    }
    
}

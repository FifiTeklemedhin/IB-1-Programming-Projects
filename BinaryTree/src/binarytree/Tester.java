/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import APClasses.APConsole;
import java.util.Iterator;

/**
 *
 * @author fifiteklemedhin
 */
public class Tester 
{
    public static void main(String[] args)
    {
        APBinaryTree tree = new APBinaryTree();
        APConsole console = new APConsole("BINARY TREE FINAL TESTER");
        
        /* testing add, adding non-unique values
            tree.add(5);
            tree.add(1);
            tree.add(2);
            tree.add(3);
            tree.add(0);
            tree.add(3);
            tree.add(2);
            tree.add(5);
            tree.add(30);
        */
        
        //add, remove, iterator()
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        
        Iterator<APBinaryTree> iterator = tree.iterator();
        for(Object node: tree)
           console.println(node);
        
        console.println(tree+"\n");
        //console.println(tree);
        
        tree.add(0);
        console.println(tree+"\n");
        
        tree.remove(0);
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        tree.remove(4);
        tree.remove(5);
        tree.remove(6);
        tree.remove(7);
        tree.remove(8);
        tree.remove(9);
        console.println(tree+"\n");

        tree.remove(0);
        console.println(tree+"\n");
        
        //*****************************Iterators*******************************
        console.println("************* inorder iterator********** ");
        tree.add(20);
        tree.add(15);
        tree.add(16);
        tree.add(25);
        tree.add(10);
        console.println(tree+"\n");
        
        iterator = tree.inOrderIterator();
        for(Object node: tree)
           iterator.remove();
        
        console.println(tree+"\n");
        
        
        tree.add(20);
        tree.add(15);
        tree.add(16);
        tree.add(25);
        tree.add(10);
        console.println(tree);
        
        console.println("************* preorder iterator********** ");
        Iterator<APBinaryTree> preOrderIterator = tree.preOrderIterator();
        for(Object node: tree)
        {
            console.println(node);
        }
        
        console.println("\n"+tree);
        
        tree.add(20);
        tree.add(15);
        tree.add(16);
        tree.add(25);
        tree.add(10);
        console.println(tree);
        
        console.println("************* postorder iterator********** ");
        Iterator<APBinaryTree> postOrderIterator = tree.postOrderIterator();
        for(Object node: tree)
        {
            console.println(node);
        }
        
        console.println("\n"+tree);
        
        tree.add(20);
        tree.add(15);
        tree.add(16);
        tree.add(25);
        tree.add(10);
        console.println(tree);
        
        console.println("************* breadthfirst iterator********** ");
        Iterator<APBinaryTree> breadthIterator = tree.breadthFirstIterator();
        for(Object node: tree)
        {
            console.println(node);
            iterator.next();
        }
        
        console.println(tree+"\n");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this nodelate file, choose Tools | Templates
 * and open the nodelate in the editor.
 */
package avlbinarytree;

import java.util.Iterator;

import APClasses.*;
/**
 *
 * @author fifiteklemedhin
 */
public class APBinaryTree<E extends Comparable> implements Iterable<E>
{
    protected Node<E> root = new Node(null, null, null);
    private int size = 0;
    APConsole console = new APConsole("");
    String toStr = "";
    

    //REMOVE WORKS FOR ONLY FIRST AND SECOND VALUES
    /*
        TODO:
            * E add(E data)
            * int length()
            * int subtreeLength(Node<E>) --> private
            * E remove(E data)
            * Iterator iterator() --> in order
            * Iterator getPreOrderIterator()
            * Iterator getPostOrderIterator()
    */
    
    /*
        * which thing do you by
    */
    
    /*
        
    */
    public static void main(String[] args)
    {
        APBinaryTree tree = new APBinaryTree();
        APConsole console = new APConsole("");
        
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        
        //console.println("root: " + tree.root.right);
        tree.printTree(tree.root, 10, 10);
    }
    E add(E data)
    {
        return addFrom(this.root, data);
    }
    E addFrom(Node<E> currentNode, E data)
    {
        
        if (currentNode.data == null) 
        {
            root = new Node(data);
            size += 1;
            return data;
        }
        
        if(currentNode == null)
        {
            currentNode = new Node(data);
            return data;
        }

        int compare = data.compareTo(currentNode.data);
        if(compare == 1)
        {
            if(currentNode.right == null)
            {
                currentNode.right = new Node(data);
                size += 1;
                return data;
            }
            addFrom(currentNode.right, data);
        }
        else if(compare == -1)
        {
            if(currentNode.left == null)
            {
                currentNode.left = new Node(data);
                size += 1;
                return data;
            }
            addFrom(currentNode.left, data);
        }
        // if it exists already, then add it to the subtree of the root that has the smallest length
        else
        {
            if(subtreeLength(currentNode.left) > subtreeLength(currentNode.right))
            {
                if(currentNode.right == null)
                {
                    currentNode.right = new Node(data);
                    return data;
                }
                else
                    addFrom(currentNode.right, data);
            }
            else
            {
                if(currentNode.left == null)
                {
                    currentNode.left = new Node(data);
                    return data;
                }

                else
                    addFrom(currentNode.left, data);
            }
        }
       
        return null;
    
        
    }public E add(Node currentNode, Node nodeToAdd, E data)
    {
        if(nodeToAdd == null)
            return data;
        if(this.root == null)
        {
           root = nodeToAdd;
           return data; 
        }
        
        int compare = data.compareTo(currentNode.data);
        
        if(compare < 0)
        {
            if(currentNode.left == null)
            {
                currentNode.left = nodeToAdd;
                return data;
            }
            return add(currentNode.left, nodeToAdd, data);
        }
        
        else if(compare > 0)
        {
            if(currentNode.right == null)
            {
                currentNode.right = nodeToAdd;
                return data;
            }
            return add(currentNode.right, nodeToAdd, data);
        }
        
        else
        {
            if(subtreeLength(currentNode.right) < subtreeLength(currentNode.left))
            {
                if(currentNode.right == null)
                {
                    currentNode.right = nodeToAdd;
                    return data;
                }
                return add(currentNode.right, nodeToAdd, data);
            }
            
            else
            {
                if(currentNode.left == null)
                {
                    currentNode.left = nodeToAdd;
                    return data;
                }
                return add(currentNode.left, nodeToAdd, data);
            }
            
        }
        
        
    }
    
    public E remove(E data)
    {
        return this.remove(this.root, data);
    }
    public E remove(Node currentNode, E data)
    {
        
        if(this.root == null || currentNode == null)
        {
           return data; 
        }
        
        int compare = data.compareTo(currentNode.data);
        if(compare != 0)
        {
             if(compare < 0)
                return remove(currentNode.left, data);

            else if(compare > 0) 
                return remove(currentNode.right, data);
        }

        else
        {
            //take the bigger side of the current node and keep it there, then take the other side and insert it to that tree
          
            if(currentNode.isLeaf())
            {
                System.out.println("1");
                
                currentNode.right = null;
                currentNode.left = null;
                currentNode = null;
                
                return data;
            }
            
            else if(currentNode.right == null && currentNode.left != null)
            {
                currentNode = currentNode.left;
                currentNode.left = null;
                return data;
            }
            else if(currentNode.left == null && currentNode.right != null)
            {
                currentNode = currentNode.right;
                currentNode.right = null;
                return data;
            }
            
            if(subtreeLength(currentNode.right) < subtreeLength(currentNode.left))
            {
               Node<E> replacementTree = currentNode.left;
               Node right = currentNode.right;
               
               currentNode.left = null;
               currentNode.right = null;
               
               this.add(replacementTree, right, (E) right.data);
               currentNode = replacementTree;
    
               return data;
 
            }
            
            else
            {
               Node replacementTree = currentNode.right;
               Node left = currentNode.left;
     
               currentNode.right = null;
               currentNode.left = null;
               
               this.add(replacementTree, left, (E) left.data);
               currentNode = replacementTree;
               
               return data;
               
            }
            
        }
        
        
       return data;
        
    }
    
    public boolean contains(E data)
    {
        Iterator<E> iterator = this.iterator();
        System.out.print("QUEUE: ");
        for(Object node: this)
        {
            System.out.print(node + ",");
            if(data.compareTo(node) == 0)
            {
                System.out.println("\n");
                return true;
            }
        }

        System.out.println("\n");
        return false;
    }
    private int subtreeLength(Node<E> node)
    {
        return inOrder(node, new APQueue<E>()).getLength();
    }
    public int length()
    {
        return subtreeLength(this.root);
    }
    public APQueue inOrder(Node<E> node, APQueue queue)
    {
        // add left add me add right
        if(node == null || node.data == null) //means that the root has no data yet
            return queue;
        if(node.left != null)
            inOrder(node.left, queue);

        queue.enqueue(node.data);

        if(node.right != null)
            inOrder(node.right, queue);

        return queue;
    }
    public APQueue preOrder(Node<E> node, APQueue queue)
    {
        // add left add me add right
        if(node == null || node.data == null) //means that the root has no data yet
            return queue;
        queue.enqueue(node.data);
        if(node.left != null)
            inOrder(node.left, queue);
        if(node.right != null)
            inOrder(node.right, queue);

        return queue;
    }
    
    public String toString()
    {
        return "";
    }
    public void printTree(Node root, int spaces, int increment)
    {
        if(root == null)
           return;
        
        spaces += increment;
        if(root.right != null)
            printTree(root.right, spaces, increment);
        
        console.println("");
        for(int j = increment; j < spaces; j++)
            console.print(" ");
        
        console.println(root.data);
        
        if(root.left != null)
            printTree(root.left, spaces, increment);
        
        
    }
     @Override
    public Iterator<E> iterator() 
    {
        return new APBinaryTreeIterator(this);
    }
    
    private class APBinaryTreeIterator implements Iterator<E>
    {
        private APBinaryTree tree; 
        private APQueue queue = new APQueue();
        public APBinaryTreeIterator(APBinaryTree list)
        {
            this.tree = list;
            //this.tree.root = new Node(null, null, null);
            this.queue = inOrder(this.tree.root, this.queue);
        }
        
        @Override
        public boolean hasNext() 
        {
           return this.queue.peek() != null;
        }

        @Override
        public E next() 
        {
            if(hasNext())
            {
                E currentData = (E) this.queue.dequeue();
                return currentData;
                
            }
            return null;
        }
        
        @Override
        public void remove()
        {
           if(hasNext())
            queue.dequeue();
        
        }
        
    }
}

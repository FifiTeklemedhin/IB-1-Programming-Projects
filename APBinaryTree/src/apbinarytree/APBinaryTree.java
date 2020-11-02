/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this nodelate file, choose Tools | Templates
 * and open the nodelate in the editor.
 */
package apbinarytree;

import java.util.Iterator;

/**
 *
 * @author fifiteklemedhin
 */
public class APBinaryTree<E extends Comparable> implements Iterable<E>
{
    protected Node<E> root = new Node(null, null, null);
    private int size = 0;
    //ADD DOEST WORK IF YOU TRY TO ADD THE SAME NODE VALUE THREE TIMES
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
    public static void main(String[] args)
    {
        APBinaryTree tree = new APBinaryTree();
        
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);
        tree.add(3);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        System.out.println("LENGTH: " + tree.length() + "\n");
        
        Iterator<APBinaryTree> iterator = tree.iterator();
        
        for(Object node: tree)
            System.out.println(node);
        
        for(Object node: tree)
        {
            iterator.remove();
            System.out.println(tree);  
        }
            
        
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
    
        
    }
    
    private int subtreeLength(Node<E> node)
    {
        return traverseFrom(node, new APQueue<E>()).getLength();
    }
    public int length()
    {
        return subtreeLength(this.root);
    }
    private APQueue traverseFrom(Node<E> node, APQueue queue)
    {
        // add left add me add right
        if(node == null || node.data == null) //means that the root has no data yet
        {
            //System.out.println("NULL NODE: " + node.data);
            return queue;
        }
        if(node.left != null)
        {
            //System.out.println("GOING LEFT OF: " + node.data);
            traverseFrom(node.left, queue);
        }
        
        queue.enqueue(node);
        //System.out.println("PASSING: " + node.data);
        
        if(node.right != null)
        {
            //System.out.println("GOING RIGHT OF: " + node.data);
            traverseFrom(node.right, queue);
        }
        
        return queue;
    }
    
    public String toString()
    {
        APQueue queue = traverseFrom(this.root, new APQueue<E>());
        String tree = "[";

        while(queue.getLength() != 0)
            tree += queue.dequeue() + ", ";
        
        
        return tree;
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
            this.queue = traverseFrom(this.tree.root, this.queue);
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
                Node<E> current = (Node<E>) this.queue.dequeue();
                E currentData = (E) current.data;
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
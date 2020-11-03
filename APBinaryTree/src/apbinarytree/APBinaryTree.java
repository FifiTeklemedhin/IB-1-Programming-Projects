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
        
        tree.remove(tree.root, 3);
        System.out.println("\n");
        
        iterator = tree.iterator();
        for(Object node: tree)
            System.out.println(node);
            
        
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
    public E remove(Node<E> currentNode, E data)
    {
        
        if(!this.contains(data) || currentNode == null)
           return null;
        
        int compare = data.compareTo(currentNode.data);
        
        if(compare == 1)
            return remove(currentNode.right, data);
        
        if(compare == -1)
            return remove(currentNode.left, data);
        
        else
            //if equal, then find whichever subtree is shorter and then child as the opposite-most node (ie if right is shorter, then add left child to leftmost right subtree node)
        {
            if(this.subtreeLength(currentNode.right) > this.subtreeLength(currentNode.left))
            {
                Node<E> leftmost = currentNode.right;
                while(!leftmost.isLeaf())
                    leftmost = leftmost.left;
                
                leftmost.left = currentNode.left;
                currentNode.left = null;
                root.right = currentNode.right;
            }
            
            else
            {
                Node<E> rightmost = currentNode.left;
                while(!rightmost.isLeaf())
                    rightmost = rightmost.right;
                
                rightmost.right = currentNode.right;
                currentNode.right = null;
                root.left = currentNode.left;
            }
        }
        
        return data;
    }
    public boolean contains(E data)
    {
        APQueue queue = inOrder(root, new APQueue<E>());
        for(int i = 0; i < queue.getLength(); i++)
            if(data.compareTo(queue.dequeue()) == 0)
                return true;
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
    private APQueue inOrder(Node<E> node, APQueue queue)
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
    
    public String toString()
    {
        APQueue queue = inOrder(this.root, new APQueue<E>());
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

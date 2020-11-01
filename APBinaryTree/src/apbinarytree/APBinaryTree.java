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
        
        tree.add(tree.root, 1);
        tree.add(tree.root, 2);
        tree.add(tree.root, 3);
        tree.add(tree.root, 4);
        System.out.println("LENGTH: " + tree.length() + "\n");
        
        Iterator<APBinaryTree> iterator = tree.iterator();
        
        for(Object node: tree)
            System.out.println(node);
        
    }
    E add(Node<E> node, E data)
    {
        if (node.data == null) {
            root = new Node(data);
            size += 1;
            return data;
        }
        APQueue<Node> queue = new APQueue<Node>();
        queue.enqueue(node);
 
        // Do level order traversal until we find
        // an empty place.
        boolean inserted = false;
        Node<E> currentNode = node;
        while(true)
        {
            if(data.compareTo(currentNode.data) == 1)
            {
                if(currentNode.right == null)
                {
                    currentNode.right = new Node(data);
                    return data;
                }
                currentNode = currentNode.right;
            }
            else if(data.compareTo(currentNode.data) == -1)
            {
                if(currentNode.left == null)
                {
                    currentNode.left = new Node(data);
                    return data;
                }
                currentNode = currentNode.left;
            }
            
        }
        
    }
    
    private int subtreeLength(Node<E> node)
    {
        Node<E> currentNode = this.root;
        while(currentNode != node)
        {
            if(currentNode.isLeaf())
                return 0;
            
            //sees whether to go left or right depending on value
            if(node.data.compareTo(currentNode.data) == 1)
                currentNode = currentNode.left;
            if(node.data.compareTo(currentNode.data) == -1)
                currentNode = currentNode.right;
        }
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
        
        queue.enqueue(node.data);
        //System.out.println("PASSING: " + node.data);
        
        if(node.right != null)
        {
            //System.out.println("GOING RIGHT OF: " + node.data);
            traverseFrom(node.right, queue);
        }
        
        return queue;
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

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
    public Node<E> root = new Node(null, null, null);
    protected int size = 0;
    APConsole console = new APConsole("1");
    String toStr = "";
    
    
    public Node getRoot()
    {
        return this.root;
    }
    

    public E add(E data)
    {
        return  addFrom(this.root, data, this.root);
    }
    
    private E addFrom(Node<E> currentNode, E data, Node parent)
    {
        
        if (currentNode.data == null) 
        {
            root = new Node(data);
            size += 1;
            return data;
        }
        
        if(currentNode == null)
        {
            currentNode = new Node(data, parent);
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
                    return data;
                }

                else
                    addFrom(currentNode.left, data, currentNode);
            }
        }
       
        return null;
    
        
    }
    
    public E add(Node currentNode, Node nodeToAdd, E data)
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
    protected int subtreeLength(Node<E> node)
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
        if(root == null) //if null, go to next recursive call
           return;
        
        spaces += increment; //spaces increment depending on level of node within tree
        if(root.right != null) 
            printTree(root.right, spaces, increment); //print right child first
        
        console.println(""); //create a new line and then indent 
        for(int j = increment; j < spaces; j++)
            console.print(" ");
        
        console.println(root.data); //print current node
        
        if(root.left != null) //print left child
            printTree(root.left, spaces, increment);
    }
    
    public void printTree(Node root, int spaces, int increment, APConsole console)
    {
        if(root == null) //if null, go to next recursive call
           return;
        
        spaces += increment; //spaces increment depending on level of node within tree
        if(root.right != null) 
            printTree(root.right, spaces, increment); //print right child first
        
        console.println(""); //create a new line and then indent 
        for(int j = increment; j < spaces; j++)
            console.print(" ");
        
        console.println(root.data); //print current node
        
        if(root.left != null) //print left child
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

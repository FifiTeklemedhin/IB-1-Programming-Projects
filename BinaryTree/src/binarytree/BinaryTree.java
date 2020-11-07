/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import java.util.Iterator;

/**
 *
 * @author fifiteklemedhin
 */
public class BinaryTree<E extends Comparable> implements Iterable<E>
{
    public Node<E> root;
    
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(0);
        System.out.println("roOt: " + tree.root.data);
        System.out.println(tree);
    }
    
    public E add(E data)
    {
        return add(this.root, data);
    }
    public E add(Node currentNode, E data)
    {
        if(this.root == null)
        {
           root = new Node(data);
           System.out.println("1 Added: " + data);
           return data; 
        }
        else if(currentNode == null)
        {
            currentNode = new Node<E>(data);
            System.out.println("2 Added: " + data);
            return data;
        }
        
        int compare = data.compareTo(currentNode.data);
        
        if(compare < 0)
        {
            if(currentNode.left == null)
            {
                currentNode.left = new Node(data);
                return data;
            }
            add(currentNode.left, data);
        }
        
        else if(compare > 0)
        {
            if(currentNode.right == null)
            {
                currentNode.right = new Node(data);
                return data;
            }
            add(currentNode.right, data);
        }
        
        else
            System.out.println("equal");
        
        
       return data;
        
    }
    
    public APQueue inOrder(Node<E> currentNode, APQueue queue)
    {
        if(currentNode == null)
        {
            System.out.println("null");
            return queue;
        }

        
        if(currentNode.left != null)
            inOrder(currentNode.left, queue);
        
        System.out.println(currentNode.data);
        queue.enqueue(currentNode.data);
        
        if(currentNode.right != null)
            inOrder(currentNode.right, queue);
        
        return queue;
    }
    
    public APQueue preOrder(Node<E> currentNode, APQueue queue)
    {
        if(currentNode == null || currentNode.isLeaf())
            return queue;
        
        queue.enqueue(currentNode.data);
        
        if(currentNode.left != null)
            preOrder(currentNode.left, queue);

        if(currentNode.right != null)
            preOrder(currentNode.right, queue);
        
        return queue;
    }
    
    public APQueue postOrder(Node<E> currentNode, APQueue queue)
    {
        if(currentNode == null || currentNode.isLeaf())
            return queue;
        
        if(currentNode.left != null)
            preOrder(currentNode.left, queue);

        if(currentNode.right != null)
            preOrder(currentNode.right, queue);
        
        queue.enqueue(currentNode.data);
        
        return queue;
    }

    public String toString()
    {
        APQueue queue = inOrder(this.root, new APQueue());
        String result = "TREE: ";
        int len = queue.getLength();
        for(int i = 0; i < len; i++)
            result += queue.dequeue() + ", ";
        
        return result;
    }
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

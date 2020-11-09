/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 *
 * @author fifiteklemedhin
 */
public class Node<E> {
    
    public E data;
    public Node<E> left;
    public Node<E> right;
    public Node<E> next;

     public Node(E data, Node<E> next)
    {
        this(data);
        this.next = next;
    }
    
    public Node(E data)
    {
        this.data = data;
    }
    
    public Node(E data, Node<E> left, Node<E> right)
    {
        this(data);
        this.left = left;
        this.right = right;
    }
    
    
    public boolean isLeaf()
    {
        return left == null && right == null;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avlbinarytree;

/**
 *
 * @author fifiteklemedhin
 */
public class Node<E> 
{
    //needs parent to assign new children after a rotation
    
    public Node<E> left;
    public Node<E> right;
    public Node<E> parent;
    
    public E data;
    
    public Node(E data)
    {
        this.data = data;
    }
    
    public Node(E data, Node<E> parent)
    {
        this.data = data;
        this.parent = parent;
    }
    
    public Node(E data, Node<E> left, Node<E> right)
    {
        this(data);
        this.left = left;
        this.right = right;
    }
    
    public Node(E data, Node<E> left, Node<E> right, Node<E> parent)
    {
        this(data, parent);
        this.left = left;
        this.right = right;
    }
    
    
    public boolean isLeaf()
    {
        return left == null && right == null;
    }
    
    public boolean isBalanced()
    {
        return Math.abs(this.rightHeight() - this.leftHeight()) <= 1;
    }
    
    public int height()
    {
        return Math.max(this.leftHeight(), this.rightHeight()) + 1;
    }
    public int leftHeight()
    {
        return leftHeight(this, 0);
    }
    
    public int rightHeight()
    {
        return rightHeight(this, 0);
    }
    
    public int leftHeight(Node<E> current, int height)
    {
        if(current == null)
            return height;
        
        if(current.left == null)
            return height;
        
        return leftHeight(current.left, height + 1);
    }
    
     public int rightHeight(Node<E> current, int height)
    {
        if(current == null)
            return height;
        
        if(current.right == null)
            return height;
        
        return rightHeight(current.right, height + 1);
    }
     
    
    public String toString()
    {
        return this.data + "";
    }
}

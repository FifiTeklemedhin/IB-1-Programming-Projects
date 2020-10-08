/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplinkedlist;

/**
 *
 * @author tvawdrey
 */
public class APLinkedList<E> {
    
    private Node<E> first = null;
    private int size = 0;
    
    public E add(E data)
    {
        if(first == null)
        {
            first = new Node(data, null);
            this.size += 1;
            return (E) data;
        }
        
        return insert(data, this.size - 1);

    }
    
    public E insert( E data, int position )
    {
        Node<E> currentNode = first;
        
        if(position > this.size - 1)
            return null;
        
        if(position == this.size - 1)
            for(int i = 0; i < position; i++)
            currentNode = currentNode.next;
        
        
        else
        {
            for(int i = 0; i < position-1; i++)
            currentNode = currentNode.next;
        }
        
        Node<E> nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode);
        
        this.size += 1;
        System.out.println("size: " + size);
        return (E) data;
    }
    
    public E remove( int position )
    {
        if(position > this.size - 1)
            return null;
        
        Node<E> currentNode = first;
        Node<E> previousNode = null;
        
        //goes until the node before
        for(int i = 0; i < position - 1; i++)
            currentNode = currentNode.next;
        
        previousNode = currentNode;
        currentNode = currentNode.next;
        previousNode.next = currentNode.next;
        
        this.size -= 1;
        System.out.println("removal size: " + size);
        return null;
    }
    
    public E remove( E data )
    {
        return null;
    }
    
    public int firstIndexOf( E data )
    {
        return -1;
    }
    
   /*
    public boolean contains(E data)
    {
        
    }
    public E get( int position )
    {
        
    }
    public int size()
    {
        return this.size;
    }
  */
    public String toString()
    {
        String list = "[";
        Node<E> currentNode = first;
       while(currentNode != null)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.next;
       }
       
       return list.substring(0, list.length()-2) + "]";
    }
  
    
}


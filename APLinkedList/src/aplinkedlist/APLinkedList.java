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
            return data;
        }
        
        return insert(data, this.size - 1);

    }
    
    public E insert( E data, int position )
    {
        if(position > this.size - 1)
            return null;
        
        Node<E> currentNode = first;
        for(int i = 0; i < position; i++)
            currentNode = currentNode.next;
        
        Node<E> nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode);
        
        this.size += 1;
        
        return data;
    }
    
    public E remove( int position )
    {
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


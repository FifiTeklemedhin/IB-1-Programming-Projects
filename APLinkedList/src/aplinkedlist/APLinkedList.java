/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplinkedlist;

import java.util.NoSuchElementException;

/**
 *
 * @author tvawdrey
 */
public class APLinkedList<E> {
    
    Node<E> first = null;
    private int size = 0;
    
    public E add(E data)
    {
        if(first == null)
        {
            first = new Node(data, null);
            this.size += 1;
            return (E) data;
        }
        if(first.next == null)
        {
            first.next = new Node(data, null);
            this.size += 1;
            return (E) data;
        }
        return insert(data, this.size - 1);

    }
    
    public E insert( E data, int position )
    {
        Node<E> currentNode = first;
        Node<E> nextNode = null;
         
        if(position > this.size-1)
            throw new IndexOutOfBoundsException("Unable to insert: position " + position + "is not within bounds");
        
        
        //if the position is at the end of the list, find the tail node and set it's next value to a node with the data
        if(position == this.size - 1)
        {
            while(currentNode.next != null)
               currentNode = currentNode.next;
            
            currentNode.next = new Node(data, null);
            this.size += 1;
            return (E) data;
        }
        //if inserting between two nodes, go one before the desired position to maintain order
        else
            for(int i = 0; i < position-1 && i >= 0; i++)
                currentNode = currentNode.next;
        
        //stores the nodes that are being shifted over then inserts the desired node with the variable as its next property
        nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode);
        
        this.size += 1;
        return (E) data;
    }
    
    public E remove( int position )
    {
        if(position > this.size - 1)
            throw new IndexOutOfBoundsException("Unable to remove: position " + position + " is not within bounds");
        if(position == 0)
        {
            this.first = first.next;
            return (E)("Successfully removed at position " + position);
        }
        
        Node<E> currentNode = first;
        Node<E> previousNode = null;
        
        //goes until the node before
        for(int i = 0; i < position - 1; i++)
            currentNode = currentNode.next;
        
        previousNode = currentNode;
        currentNode = currentNode.next;
        previousNode.next = currentNode.next;
        
        this.size -= 1;
        return (E)("Successfully removed at position " + position);
    }
    
    public E removeFirst( E data )
    {
        if(!this.contains(data))
            throw new NoSuchElementException("Unable to remove: no element with data " + data);
        remove(this.firstIndexOf(data));
        return data;
    }
    public E removeAll(E data)
    {
        if(!this.contains(data))
            throw new NoSuchElementException("Unable to remove: no element with data " + data);
        while(this.contains(data))
            removeFirst(data);
        return data;
    }
   
    public boolean contains(E data)
    {
       Node<E> currentNode = first;
       while(currentNode != null)
       {
           boolean isString = (currentNode.data.getClass().toString()).equalsIgnoreCase("java.lang.String");
           if(isString && currentNode.equals(data))
            return true;
           
           if(currentNode.data == data)
            return true;
           currentNode = currentNode.next;
       } 
       return false;
    }
    
    //get the value at a position
    public E get( int position )
    {
        if(position < 0)
            throw new IndexOutOfBoundsException("Unable to remove: position " + position + "is not within bounds");
        
        if(position > this.size - 1)
            throw new IndexOutOfBoundsException("Unable to remove: position " + position + "is not within bounds");

        
        Node<E> currentNode = this.first;
        for(int i = 0; i < position && currentNode.next != null; i++)
            currentNode = currentNode.next;
        
        return currentNode.data;
    }
    
    //get the position of a value
    public int firstIndexOf(E data)
    {
        if(!this.contains(data))
            throw new NoSuchElementException("Unable to find: no element with data " + data);
        
        Node<E> currentNode = this.first;
        for(Integer i = 0; i < this.size; i++)
        {
           boolean isString = (currentNode.data.getClass().toString()).equalsIgnoreCase("java.lang.String");
           if(isString && currentNode.equals(data))
            return i;
            
           if(currentNode.data == data)
            return i;
           
           currentNode = currentNode.next;
        } 
        throw new NoSuchElementException("Unable to find: no element with data " + data);
    }
    
    public int size()
    {
        return this.size;
    }
    public String toString()
    {
       if(first == null)
        return "[]";
        
       String list = "[";
       Node<E> currentNode = first;
       while(currentNode != null)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.next;
       }

       if(list.length() == 2)
           return list.replace(",", "") + "]";
       return list.substring(0, list.length()-2) + "]";
    }
  
    
}


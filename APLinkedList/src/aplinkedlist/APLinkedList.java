/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplinkedlist;

import java.util.Iterator;


/**
 *
 * @author tvawdrey
 */
public class APLinkedList<E> implements Iterable<E> {
    
    Node<E> first = null;
    protected int size = 0;
    
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
            return null;
        
        
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
        /*
        if(position == this.size - 1 && !(this.size -1 >= 0)) //if the position is lower
            return null;
        */
        if(position < 0)
            return null;
        
        if(position > this.size - 1)
            return null;
        if(position == 0)
        {
            this.first = first.next;
            this.size -= 1;
            return null;
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
        return null;
    }
    
    public E removeFirst( E data )
    {
        if(!this.contains(data))
            return null;
        
        remove(this.firstIndexOf(data));
        
        return data;
    }
    public E remove(E data)
    {
        if(!this.contains(data))
            return null;
        
        while(this.contains(data))
            removeFirst(data);
        
        return data;
    }
   
    public boolean contains(E data)
    {
       Node<E> currentNode = first;
       while(currentNode != null)
       {
           // if the data is a string, uses .equals() to compare
           boolean isString = (currentNode.data.getClass().toString()).equalsIgnoreCase("java.lang.String");
           if(isString && currentNode.equals(data))
            return true;
           
           // else compares the data as a numeric value
           if(currentNode.data == data)
            return true;
           
           // if the data does not match with the current node, moves on
           currentNode = currentNode.next;
       } 
       return false;
    }
    
    //get the value at a position
    public E get( int position )
    {
        if(position < 0)
           return null;
            
        if(position > this.size - 1)
            return null;
        
        
        Node<E> currentNode = this.first;
        for(int i = 0; i < position; i++)
            currentNode = currentNode.next;
        
        return currentNode.data;
    }
    
    //get the position of a value
    public int firstIndexOf(E data)
    {
        if(!this.contains(data))
            return -1;
        
        Node<E> currentNode = this.first;
        for(int i = 0; i < this.size; i++)
        {
            // if the data is a string, uses .equals() to compare
           boolean isString = (currentNode.data.getClass().toString()).equalsIgnoreCase("java.lang.String");
           if(isString && currentNode.equals(data))
            return i;
           
           // else compares the data as a numeric value
           if(currentNode.data == data)
            return i;
           
           // if the data does not match with the current node, moves on
           currentNode = currentNode.next;
        } 
   
        return -1;
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

    @Override
    public Iterator<E> iterator() 
    {
        return new LinkedListIterator(this);
    }
    
    private class LinkedListIterator implements Iterator<E>
    {
        private Node<E> current;
        private APLinkedList list; 
        private int index;
        public LinkedListIterator(APLinkedList list)
        {
            this.list = list;
            this.current = this.list.first;
            this.index = 0;
        }
        
        @Override
        public boolean hasNext() 
        {
           return this.current != null;
        }

        @Override
        public E next() 
        {
            if(hasNext())
            {
                E currentData = this.current.data;
                this.current = this.current.next;
                this.index += 1;
                
                return currentData;
                
            }
            return null;
        }
        
        @Override
        public void remove()
        {
           if(hasNext())
           {
                this.list.remove(index);
                current = current.next;
           }
        
        }
        
    }

}
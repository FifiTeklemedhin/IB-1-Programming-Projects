/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import APClasses.APConsole;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author fifiteklemedhin
 */
public class APCyclicallyLinkedList<E> extends APDoublyLinkedList<E> 
{  
    //add 
    public E add(E data)
    {
        if(head == null)
        {
            head = new Node(data, null);
            size += 1;
            return (E) data;
        }
        if(head.next == null)
        {
            head.next = new Node(data, null, head);//previous is the head of the list
            
            tail = head.next; //since the next node is null, it is the last
            head.previous = tail;
            tail.next = head;
            
            size += 1;
            return (E) data;
        }
        return insert(data, size - 1);

    }
    
    public E insert( E data, int position )
    {
        Node<E> currentNode = head;
        Node<E> nextNode = null;
        Node<E> nodeBefore = null;
         
        if(position > size-1)
            return null;
        
        
        //if the position is at the end of the list, find the tail node and set it's next value to a node with the data
        if(position == size - 1)
        {
            for(int i = 0; i < size && currentNode.next != null; i++)
               currentNode = currentNode.next;
            
            //the new tail of the list
            currentNode.next = new Node(data, null,currentNode); //sets the new tail to have the old tail be its previous
            tail = currentNode.next;
            
            
            head.previous = tail;
            tail.next = head;
            size += 1;
            return (E) data;
        }
        //if inserting between two nodes, go one before the desired position to maintain order
        else
            for(int i = 0; i < position-1 && i >= 0; i++)
                currentNode = currentNode.next;
        
        //stores the nodes that are being shifted over then inserts the desired node with the variable as its next property
        nodeBefore = currentNode;
        nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode, nodeBefore); //adds previous attribute
        nextNode.previous = currentNode.next;// updating the previous attribute for the index after
        
        if(position == size - 1)
        {
            tail = nextNode;
            tail.next = head;
            head.previous = tail;
        }
        size += 1;
        return (E) data;
    }
    
    public E remove( int position )
    {
        if(position < 0)
            return null;
        
        if(position > size - 1)
            return null;
        if(position == 0)
        {
            head = head.next;
            
            if(head == null) //null does not have a previous attribute to set to null
                tail = null;
            else
                head.previous = null; //cannot reference a removed node
            
            size -= 1;
            return null;
        }
        
        Node<E> currentNode = head;
        Node<E> previousNode = null;
        
        //goes until the node before
        for(int i = 0; i < position - 1; i++)
            currentNode = currentNode.next;
        
        previousNode = currentNode;
        currentNode = currentNode.next;
        previousNode.next = currentNode.next;
        
        if(previousNode.next != null) //if not removing the not tail
            previousNode.next.previous = previousNode; //makes it so that the node after the removed node does not point back to the removed node
        else
            tail = previousNode;
        size -= 1;
        return null;
    }
    
    
    public void updateEnds()
    {
        if(head != null && tail != null)
        {
            head.previous = tail;
            tail.next = head;
        }
        
    }
    
    public String toString()
    {
       if(head == null)
        return "[]";
        
       String list = "[";
       Node<E> currentNode = head;
       for(int i =0; i < size && currentNode.next != null; i++)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.next;
       }

       if(list.length() == 2)
           return list.replace(",", "") + "]";
       return list.substring(0, list.length()-2) + "]";
    }
    
    public boolean contains(E data)
    {
       Node<E> currentNode = head;
       for(int i = 0; i < size; i++)
       {
           // if the data is a string, uses .equals() to compare
           if(currentNode.data.equals(data))
            return true;
           
           // else compares the data as a numeric value
           if(currentNode.data == data)
            return true;
           
           // if the data does not match with the current node, moves on
           currentNode = currentNode.next;
       } 
       return false;
    }
    
    @Override
    public Iterator<E> iterator() 
    {
        return new CyclicallyLinkedListIterator(this);
    }
    
    private class CyclicallyLinkedListIterator implements Iterator<E>
    {
        private Node<E> current;
        private APLinkedList list; 
        private int index;
        private int head_count = 0;
        public CyclicallyLinkedListIterator(APCyclicallyLinkedList list)
        {
            this.list = list;
            this.current = this.list.head;
            this.index = 0;
        }
        
        @Override
        public boolean hasNext() 
        {
            if(this.current == head)
                head_count += 1;
           return head_count <= 1;
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

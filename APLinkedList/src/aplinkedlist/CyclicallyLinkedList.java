/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplinkedlist;

import APClasses.APConsole;

/**
 *
 * @author fifiteklemedhin
 */
public class CyclicallyLinkedList<E> extends DoublyLinkedList<E>
{
    public static void main (String[] args)
    {  //adding
        APConsole console = new APConsole("Cyclically Linked List");
        CyclicallyLinkedList list = new CyclicallyLinkedList();
        new Tester(list, console);
        console.println("TAIL: " + list.head.previous.data);
        console.println("HEAD: " + list.tail.next.data);
    }
    public E add(E data) //changed to be o(1)
    {
        if(head == null)
        {
            head = new Node(data, null);
            tail = head;
            size += 1;
            
            updateEnds();
            return (E) data;
        }
        if(head.next == null)
        {
            head.next = new Node(data, null, head);
            tail = head.next;
            size += 1;
            
            updateEnds();
            return (E) data;
        }
        //if the position is at the end of the list, find the tail node and set it's next value to a node with the data
     
        if(tail == null)
        {
            tail = new Node(data, head);
            size += 1;
            
            updateEnds();
            return (E) data;
        }
        if(tail == head)
        {
            tail.next = new Node(data, null, head);
            tail = tail.next;
            
            updateEnds();
            return (E) data;
        }
            
        tail.next = new Node(data, null, tail);
        tail = tail.next;
        size += 1;
        
        updateEnds();
        return (E) data;
        
    }
    public E insert( E data, int position )
    {
        Node<E> currentNode = this.head;
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
            updateEnds();
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
        updateEnds();
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
            this.head= this.head.next;
            this.size -= 1;
            //updateEnds();
            return null;
        }
        
        Node<E> currentNode = this.head;
        Node<E> previousNode = null;
        
        //goes until the node before
        for(int i = 0; i < position - 1; i++)
            currentNode = currentNode.next;
        
        previousNode = currentNode;
        currentNode = currentNode.next;
        previousNode.next = currentNode.next;
        
        this.size -= 1;
        //updateEnds();
        return null;
    }
    public void updateEnds()
    {
        head.previous = tail;
        tail.next = head;
    }
    
    public String toString()
    {
       if(this.head == null)
        return "[]";
        
       String list = "[";
       Node<E> currentNode = this.head;
       for(int i = 0; i <= size; i++)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.next;
       }

       if(list.length() == 2)
           return list.replace(",", "") + "]";
       return list.substring(0, list.length()-2) + "]";
    }
}

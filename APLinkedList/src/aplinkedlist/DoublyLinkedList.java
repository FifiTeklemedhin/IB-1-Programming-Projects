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
public class DoublyLinkedList<E> extends APLinkedList<E>
{   
    Node<E> tail;
    public static void main(String[] args)
    {
        APConsole console = new APConsole("Doubly LinkedList Tester");
        DoublyLinkedList list = new DoublyLinkedList();
        
        console.println("***********************************ADDING************************************");
        console.println("letters");
        list.add("43802sdlja3048");
        list.add("hello world");
        console.println("\t" + list);
             
        console.println("\nadd numbers");
        for(int  i = 0; i <= 10; i++)
        {
            list.add(i);
           console.println("\t" + list);
        }
        
        console.println("\nmore letters");
        list.add("i");
        list.add("j");
        list.add("l");
        console.println("\t" + list);
    }
    
    public E add(E data) //changed to be o(1)
    {
        if(first == null)
        {
            first = new Node(data, null);
            tail = first;
            this.size += 1;
            return (E) data;
        }
        if(first.next == null)
        {
            first.next = new Node(data, null, first);
            tail = first.next;
            this.size += 1;
            return (E) data;
        }
        //if the position is at the end of the list, find the tail node and set it's next value to a node with the data
     
        if(tail == null)
        {
            tail = new Node(data, null, null);
            return (E) data;
        }
        if(tail == first)
        {
            tail.next = new Node(data, null, null);
            this.tail = tail.next;
        }
            
        tail.next = new Node(data, null, tail);
        this.tail = tail.next;
        this.size += 1;
        return (E) data;
        
    }
    
    public E insert( E data, int position )
    {
        Node<E> currentNode = first;
        Node<E> nextNode = null;
         
        if(position > this.size-1)
            return null;

        //if inserting between two nodes, go one before the desired position to maintain order
        for(int i = 0; i < position-1 && i >= 0; i++)
            currentNode = currentNode.next;
        
        //stores the nodes that are being shifted over then inserts the desired node with the variable as its next property
        Node<E> previousNode = currentNode;
        nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode, previousNode);
        
        this.size += 1;
        return (E) data;
    }
    
    public String toString()
    {
       if(first == null)
        return "[]";
        
       String list = "[";
       Node<E> currentNode = tail;
       for(int i = 0; i < this.size(); i++)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.previous;
           
       }

       
       return list;
    }
}


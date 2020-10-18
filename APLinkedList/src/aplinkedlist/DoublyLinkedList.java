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
    Node<E> tail;
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
            first.next = new Node(data, null, first);
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
            
            currentNode.next = new Node(data, null, currentNode);
            this.tail = currentNode.next;
            this.size += 1;
            return (E) data;
        }
        //if inserting between two nodes, go one before the desired position to maintain order
        else
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
       while(currentNode != null)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.previous;
       }

       if(list.length() == 2)
           return list.replace(",", "") + "]";
       return list;
    }
}


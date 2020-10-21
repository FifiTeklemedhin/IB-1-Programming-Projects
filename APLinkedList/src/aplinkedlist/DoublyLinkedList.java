/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplinkedlist;

import APClasses.APConsole;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author fifiteklemedhin
 */
public class DoublyLinkedList<E> extends APLinkedList<E>
{   
    Node<E> tail;
    //ITERATOR DOES NOT WORK
    public static void main(String[] args)
    {
         //adding
        APConsole console = new APConsole("Doubly Linked List");
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
        
        
        //inserting
        console.println("**********************************INSERTING**********************************");
        console.println("inserting numbers");
        
        list.insert(11, 11);
        console.println("\t" + list);
        list.insert(23, 5);
        
        console.println("\t" + list + "\n");
        console.println("inserting letters");
       
        list.insert("wassup", 11);
        console.println("\t" + list);
        list.insert("testing", 5);
        console.println("\t" + list + "\n");
        
        //removing
        console.println("**********************************REMOVING**********************************");
        console.println("removing");
        
        console.println("\t" + list);
        while(list.size() >= 1)
        {   
            list.remove(list.size() - 1);  
            console.println("\t" + list);
        }
        System.out.println(list.size);
        console.println("*******************************REMOVING FIRST*******************************");
        //filling list
        console.println("adding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
            list.add(i);
 
        }
        list.insert("wassup", 11);
        list.insert("testing", 5);
        console.println("\t" + list);
        System.out.println("\t" + list.size);
        //removing each element one by one
        console.println("\nremoving first: wassup");
        
        list.removeFirst("wassup");
        for(int i = 0; i <= 10; i++)
            list.removeFirst(i);
        console.println("\t" + list);
        System.out.println("\t" + list.size);
        
        console.println("\n********************************REMOVING ALL*********************************");
        //filling list
        console.println("adding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
 
        }
        list.insert("wassup", 11);
        list.insert("testing", 5);
        
        console.println("\t" + list);
        System.out.println("\t" + list.size);
        
        console.println("\nremoving all of each data value: wassup, testing");
        list.remove("wassup");
        list.remove("testing");
        console.println("\t" + list);
        System.out.println("\t" + list.size);
        //removing all of each data value
        
        
        console.println("\nadding new list");
        Random rand = new Random();
        for(int i = 0; i <= 10; i++)
        {
            list.add(rand.nextInt((100 - 11) + 1) + 11);
            list.add("hello"); 
        }
        console.println("\t" + list);
        System.out.println("\t" + list.size);
         
        console.println("\nremoving all of each data value: 47, hello");
        
        list.remove(47);
        list.remove("hello");
        console.println("\t" + list);
        System.out.println("\t" + list.size);
        
        console.println("\n********************************CONTAINS*********************************");
        if(list.contains("1"))
            list.add(20);
        
        if(!list.contains("hello"))
            list.add(40);
        console.println(list);
        System.out.println("contains: " + list.size);
        
        
        console.println("\n********************************ITERABLE*********************************");
        System.out.println("before removing: " + list.size);
        Iterator<APLinkedList> iterator = list.iterator();
        console.println("For-each: removing all/n");
        console.println("\t" + list);
        for(Object node : list)
        {
            list.removeFirst(node);
           console.println("\t" + list);
        }
        System.out.println(list.size);
        console.println("\nadding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(47);
            list.add("hello"); 
        }
        System.out.println("\t" + list.size);
        
        console.println("removing all");
        console.println("\t" + list);
        System.out.println();
        
        for(Object node: list)
        {
           
            iterator.remove();
            console.println("\t" + list);
            
        }
        System.out.println(list.size);
        console.println("\nadding new list");
        
        for(int j = 0; j <= 10; j++)
        {
            list.add(47);
            list.add("hello"); 
        }
        System.out.println("\t" + list.size);
        console.println("removing one\n");
        console.println("\t" + list);
        
        iterator.remove();
        
        console.println("\t" + list);
        console.println("\tnew head: " + list.head.data);
        System.out.println("\t" + list.size);
        
        
        console.println("\nremoving half\n");
        //console.println("\t" + list);
        
        int index = 0;
        for(Object node: list)
        {
            
            if(index % 2 == 0)
            {
                iterator.remove();
                console.println("\t" + list);
            }
            index += 1;
        }
        console.println(list.get(0));
        console.println("\t" + list);
        System.out.println("\t" + list.size);
        
        console.println("\ninserting and removing\n");
        list.insert(1235, 5);
        console.println(list);
        list.remove(5);
        console.println(list);
        
        console.println(list.size());
        System.out.println("\t" + list.size);
        
        
        boolean mostMethodsPassed =  "[47, hello, 47, hello, 47, hello, 47, hello, 47, hello]".equals(list.toString());
        boolean containsWorks = !list.contains(3) && list.contains(47);
        boolean functionsCorrectly =  mostMethodsPassed && containsWorks && list.size() == 10;
        
        if(mostMethodsPassed)
            console.println("\nMost Methods Passed");
        
        if(containsWorks)
            console.println("Contains Works");
        
        console.println(list.size());
        if(list.size() == 10)
            console.println("Right Size");
        
        
        console.println(list);
        if(functionsCorrectly)
            console.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ALL TESTS PASSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
         else
            console.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<TESTS FAILED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        //TODO: test size() and contains()
    }
    
    public E add(E data) //changed to be o(1)
    {
        if(head == null)
        {
            head = new Node(data, null);
            tail = head;
            size += 1;
            return (E) data;
        }
        if(head.next == null)
        {
            head.next = new Node(data, null, head);
            tail = head.next;
            size += 1;
            return (E) data;
        }
        //if the position is at the end of the list, find the tail node and set it's next value to a node with the data
     
        if(tail == null)
        {
            tail = new Node(data, null);
            size += 1;
            return (E) data;
        }
        if(tail == head)
        {
            tail.next = new Node(data, null, null);
            tail = tail.next;
            return (E) data;
        }
            
        tail.next = new Node(data, null, tail);
        tail = tail.next;
        size += 1;
        return (E) data;
        
    }
    
    public E insert( E data, int position )
    {
        Node<E> currentNode = head;
        Node<E> nextNode = null;
         
        if(position > size-1)
            return null;

        //if inserting between two nodes, go one before the desired position to maintain order
        for(int i = 0; i < position-1 && i >= 0; i++)
            currentNode = currentNode.next;
        
        //stores the nodes that are being shifted over then inserts the desired node with the variable as its next property
        Node<E> previousNode = currentNode;
        nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode, previousNode);
        
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
            head= head.next;
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
        
        currentNode.previous = previousNode;
        
        size -= 1;
        return null;
    }
     
    public String reversed()
    {
       if(head == null)
        return "[]";
        
       String list = "[";
       Node<E> currentNode = tail;
       for(int i = 0; i < size() && currentNode != null; i++)
       {
           list += currentNode.data + ", ";
           currentNode = currentNode.previous;
           
       }

       if(list.length() == 2)
           return list.replace(",", "") + "]";
       return list.substring(0, list.length()-2) + "]";
    }
    public String toString()
    {
       if(this.head == null)
        return "[]";
        
       String list = "[";
       Node<E> currentNode = this.head;
       while(currentNode != null)
       {
           /*if(currentNode.next != null)
            list += currentNode.data + ": " + currentNode.next.data + ", ";
           else*/
               list += currentNode.data + ", ";
           currentNode = currentNode.next;
       }

       if(list.length() == 2)
           return list.replace(",", "") + "]";
       return list.substring(0, list.length()-2) + "]";
    }
}


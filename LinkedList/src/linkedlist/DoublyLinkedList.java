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
public class DoublyLinkedList<E> extends LinkedList<E> 
{
    Node<E> tail;
    
     public static void main(String[] args)
    {
        APConsole console = new APConsole("Doubly LinkedList2 Tester");
        DoublyLinkedList list = new DoublyLinkedList();
        
         console.println("***********************************ADDING************************************");
        console.println("letters");
        list.add("43802sdlja3048");
        list.add("hello world");
        console.println("\t" + list.reversed());
        
        console.println("\nadd numbers");
        for(int  i = 0; i <= 10; i++)
        {
            list.add(i);
           console.println("\t" + list.reversed());
        }
        
        console.println("\nmore letters");
        list.add("i");
        list.add("j");
        list.add("l");
        console.println("\t" + list.reversed());
        
        
        //inserting
        console.println("**********************************INSERTING**********************************");
        console.println("inserting numbers");
        
        list.insert(11, 11);
        console.println("\t" + list.reversed());
        list.insert(23, 5);
        
        console.println("\t" + list.reversed() + "\n");
        console.println("inserting letters");
       
        list.insert("wassup", 11);
        console.println("\t" + list.reversed());
        list.insert("testing", 5);
        console.println("\t" + list + "\n");
        
        //removing
        console.println("**********************************REMOVING**********************************");
        console.println("removing");
        
        console.println("\t" + list.reversed());
        while(list.size() >= 1)
        {   
            list.remove(list.size() - 1);  
            console.println("\t" + list.reversed());
        }
       
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
        console.println("\t" + list.reversed());
        
        //removing each element one by one
        console.println("\nremoving head: wassup");
        
        list.removeFirst("wassup");
        for(int i = 0; i <= 10; i++)
            list.removeFirst(i);
        console.println("\t" + list.reversed());
        
        
        console.println("\n********************************REMOVING ALL*********************************");
        //filling list
        console.println("adding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
 
        }
        list.insert("wassup", 11);
        list.insert("testing", 5);
        
        console.println("\t" + list.reversed());
        
        console.println("\nremoving all of each data value: wassup, testing");
        list.remove("wassup");
        list.remove("testing");
        console.println("\t" + list.reversed());
        
        //removing all of each data value
        
        
        console.println("\nadding new list");
        Random rand = new Random();
        for(int i = 0; i <= 10; i++)
        {
            list.add(rand.nextInt((100 - 11) + 1) + 11);
            list.add("hello"); 
        }
        console.println("\t" + list.reversed());
        
         
        console.println("\nremoving all of each data value: 47, hello");
        
        list.remove(47);
        list.remove("hello");
        console.println("\t" + list.reversed());
        
        console.println("\n********************************CONTAINS*********************************");
        if(list.contains("1"))
            list.add(20);
        
        if(!list.contains("hello"))
            list.add(40);
        
        console.println("\n********************************CONTAINS*********************************");
        if(list.contains("1"))
            list.add(20);
        
        if(!list.contains("hello"))
            list.add(40);
        
        console.println("\n********************************ITERABLE*********************************");
        Iterator<LinkedList> iterator = list.iterator();
        console.println("For-each: removing all/n");
        console.println("\t" + list.reversed());
        for(Object node : list)
        {
            list.removeFirst(node);
           console.println("\t" + list.reversed());
        }
        
        console.println("\nadding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(47);
            list.add("hello"); 
        }
        
        console.println("removing all");
        console.println("\t" + list.reversed());
        
        for(Object node: list)
        {
           
            iterator.remove();
            console.println("\t" + list.reversed());
            
        }
        
        console.println("\nadding new list");
        for(int j = 0; j <= 10; j++)
        {
            list.add(47);
            list.add("hello"); 
        }
        
        console.println("removing one\n");
        console.println("\t" + list.reversed());
        
        iterator.remove();
        console.println("\t" + list.reversed());
        console.println("\tnew head: " + list.head.data);
        
        console.println("\nremoving half\n");
        console.println("\t" + list.reversed());
        int index = 0;
        for(Object node: list)
        {
            
            if(index % 2 == 0)
                iterator.remove();
            index += 1;
        }
        console.println("\t" + list.reversed());
        
        console.println("\ninserting and removing\n");
        list.insert(1235, 5);
        console.println(list.reversed());
        list.remove(5);
        console.println(list.reversed());
        
        
        
        boolean mostMethodsPassed =  "[hello, 47, hello, 47, hello, 47, hello, 47, hello, 47]".equals(list.reversed());
        boolean containsWorks = !list.contains(3) && list.contains(47);
        boolean functionsCorrectly =  mostMethodsPassed && containsWorks && list.size() == 10;
        
        console.println(containsWorks);
        if(functionsCorrectly)
            console.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ALL TESTS PASSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
         else
            console.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<TESTS FAILED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
       
        
    }

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
            while(currentNode.next != null)
               currentNode = currentNode.next;
            
            //the new tail of the list
            currentNode.next = new Node(data, null,currentNode); //sets the new tail to have the old tail be its previous
            tail = currentNode.next;
            
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
        
        if(previousNode.next != null) //if removing the not tail
            previousNode.next.previous = previousNode; //makes it so that the node after the removed node does not point back to the removed node
        else
            tail = previousNode;
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
}

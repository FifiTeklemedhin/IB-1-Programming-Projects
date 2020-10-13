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
public class Tester 
{
    APLinkedList list;
    APConsole console;
 
    public Tester(APLinkedList list)
    {
        this.list = list;
        this.console = new APConsole("Linked List");
        this.functionsCorrectly(list);
    }
    public static void main(String[] args)
    {
        new Tester(new APLinkedList());
    }
    public boolean functionsCorrectly(APLinkedList list)
    {
        
        //adding
        this.console.println("***********************************ADDING************************************");
        this.console.println("letters");
        list.add("43802sdlja3048");
        list.add("hello world");
        this.console.println("\t" + list);
        
        this.console.println("\nadd numbers");
        for(int  i = 0; i <= 10; i++)
        {
            list.add(i);
           this.console.println("\t" + list);
        }
        
        this.console.println("\nmore letters");
        list.add("i");
        list.add("j");
        list.add("l");
        this.console.println("\t" + list);
        
        
        //inserting
        this.console.println("**********************************INSERTING**********************************");
        this.console.println("inserting numbers");
        
        list.insert(11, 11);
        this.console.println("\t" + list);
        list.insert(23, 5);
        
        this.console.println("\t" + list + "\n");
        this.console.println("inserting letters");
       
        list.insert("wassup", 11);
        this.console.println("\t" + list);
        list.insert("testing", 5);
        this.console.println("\t" + list + "\n");
        
        //removing
        this.console.println("**********************************REMOVING**********************************");
        this.console.println("removing");
        
        this.console.println("\t" + list);
        while(list.size() >= 1)
        {   
            list.remove(list.size() - 1);  
            this.console.println("\t" + list);
        }
       
        this.console.println("*******************************REMOVING FIRST*******************************");
        //filling list
        this.console.println("adding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
            list.add(i);
 
        }
        list.insert("wassup", 11);
        list.insert("testing", 5);
        this.console.println("\t" + list);
        
        //removing each element one by one
        this.console.println("\nremoving first: wassup");
        
        list.removeFirst("wassup");
        for(int i = 0; i <= 10; i++)
            list.removeFirst(i);
        this.console.println("\t" + list);
        
        
        this.console.println("\n********************************REMOVING ALL*********************************");
        //filling list
        this.console.println("adding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
 
        }
        list.insert("wassup", 11);
        list.insert("testing", 5);
        
        this.console.println("\t" + list);
        
        this.console.println("\nremoving all of each data value: wassup, testing");
        list.remove("wassup");
        list.remove("testing");
        this.console.println("\t" + list);
        
        //removing all of each data value
        
        
        this.console.println("\nadding new list");
        Random rand = new Random();
        for(int i = 0; i <= 10; i++)
        {
            list.add(rand.nextInt((100 - 11) + 1) + 11);
            list.add("hello"); 
        }
        this.console.println("\t" + list);
        
         
        this.console.println("\nremoving all of each data value: 47, hello");
        
        list.remove(47);
        list.remove("hello");
        this.console.println("\t" + list);
        
        this.console.println("\n********************************CONTAINS*********************************");
        if(list.contains("1"))
            list.add(20);
        
        if(!list.contains("hello"))
            list.add(40);
        
        this.console.println("\n********************************CONTAINS*********************************");
        if(list.contains("1"))
            list.add(20);
        
        if(!list.contains("hello"))
            list.add(40);
        
        this.console.println("\n********************************ITERABLE*********************************");
        Iterator<APLinkedList> iterator = list.iterator();
        this.console.println("For-each: removing all/n");
        this.console.println("\t" + list);
        for(Object node : list)
        {
            list.removeFirst(node);
           this.console.println("\t" + list);
        }
        
        this.console.println("\nadding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(47);
            list.add("hello"); 
        }
        
        this.console.println("removing all");
        this.console.println("\t" + list);
        
        for(Object node: list)
        {
           
            iterator.remove();
            this.console.println("\t" + list);
            
        }
        
        this.console.println("\nadding new list");
        for(int j = 0; j <= 10; j++)
        {
            list.add(47);
            list.add("hello"); 
        }
        
        this.console.println("removing one\n");
        this.console.println("\t" + list);
        
        iterator.remove();
        this.console.println("\t" + list);
        this.console.println("\tnew head: " + list.first.data);
        
        this.console.println("\nremoving half\n");
        this.console.println("\t" + list);
        int index = 0;
        for(Object node: list)
        {
            
            if(index % 2 == 0)
                iterator.remove();
            index += 1;
        }
        this.console.println("\t" + list);
        
        this.console.println("\ninserting and removing\n");
        list.insert(1235, 5);
        console.println(list);
        list.remove(5);
        console.println(list);
        
        
        
        boolean functionsCorrectly =  "[hello, 47, hello, 47, hello, 47, hello, 47, hello, 47, hello]".equals(list.toString());
        if(functionsCorrectly)
            console.println("\n********************************ITERABLE*********************************");
        return functionsCorrectly;
    }
}

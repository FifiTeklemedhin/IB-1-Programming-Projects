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
public class APDoublyLinkedListTester extends APLinkedListTester
{
    private APDoublyLinkedList list;
    private APConsole console;
    public APDoublyLinkedListTester(APDoublyLinkedList list, APConsole console)
    {
        super(list, console);
    }
    
    
    public boolean functionsCorrectly()
    {
        return generalFunctionsWork() && list.reversed().equals("[hello, 47, hello, 47, hello, 47, hello, 47, hello, 47]");
    }
    public boolean generalFunctionsWork()
    {
        
        //adding
        this.console.println("***********************************ADDING************************************");
        this.console.println("letters");
        this.list.add("43802sdlja3048");
        this.list.add("hello world");
        this.console.println("\t" + this.list);
        
        this.console.println("\nadd numbers");
        for(int  i = 0; i <= 10; i++)
        {
            this.list.add(i);
           this.console.println("\t" + this.list);
        }
        
        this.console.println("\nmore letters");
        this.list.add("i");
        this.list.add("j");
        this.list.add("l");
        this.console.println("\t" + this.list);
        
        
        //inserting
        this.console.println("**********************************INSERTING**********************************");
        this.console.println("inserting numbers");
        
        this.list.insert(11, 11);
        this.console.println("\t" + this.list);
        this.list.insert(23, 5);
        
        this.console.println("\t" + this.list + "\n");
        this.console.println("inserting letters");
       
        this.list.insert("wassup", 11);
        this.console.println("\t" + this.list);
        this.list.insert("testing", 5);
        this.console.println("\t" + this.list + "\n");
        
        //removing
        this.console.println("**********************************REMOVING**********************************");
        this.console.println("removing");
        
        this.console.println("\t" + this.list);
        while(this.list.size() >= 1)
        {   
            this.list.remove(this.list.size() - 1);  
            this.console.println("\t" + this.list);
        }
       
        this.console.println("*******************************REMOVING FIRST*******************************");
        //filling this.list
        this.console.println("adding new this.list");
        for(int i = 0; i <= 10; i++)
        {
            this.list.add(i);
            this.list.add(i);
 
        }
        this.list.insert("wassup", 11);
        this.list.insert("testing", 5);
        this.console.println("\t" + this.list);
        
        //removing each element one by one
        this.console.println("\nremoving head: wassup");
        
        this.list.removeFirst("wassup");
        for(int i = 0; i <= 10; i++)
            this.list.removeFirst(i);
        this.console.println("\t" + this.list);
        
        
        this.console.println("\n********************************REMOVING ALL*********************************");
        //filling this.list
        this.console.println("adding new this.list");
        for(int i = 0; i <= 10; i++)
        {
            this.list.add(i);
 
        }
        this.list.insert("wassup", 11);
        this.list.insert("testing", 5);
        
        this.console.println("\t" + this.list);
        
        this.console.println("\nremoving all of each data value: wassup, testing");
        this.list.remove("wassup");
        this.list.remove("testing");
        this.console.println("\t" + this.list);
        
        //removing all of each data value
        
        
        this.console.println("\nadding new this.list");
        Random rand = new Random();
        for(int i = 0; i <= 10; i++)
        {
            this.list.add(rand.nextInt((100 - 11) + 1) + 11);
            this.list.add("hello"); 
        }
        this.console.println("\t" + this.list);
        
         
        this.console.println("\nremoving all of each data value: 47, hello");
        
        this.list.remove(47);
        this.list.remove("hello");
        this.console.println("\t" + this.list);
        
        this.console.println("\n********************************CONTAINS*********************************");
        if(this.list.contains("1"))
            this.list.add(20);
        
        if(!this.list.contains("hello"))
            this.list.add(40);
        
        this.console.println("\n********************************CONTAINS*********************************");
        if(this.list.contains("1"))
            this.list.add(20);
        
        if(!this.list.contains("hello"))
            this.list.add(40);
        
        this.console.println("\n********************************ITERABLE*********************************");
        Iterator<APLinkedList> iterator = this.list.iterator();
        this.console.println("For-each: removing all/n");
        this.console.println("\t" + this.list);
        for(Object node : this.list)
        {
            this.list.removeFirst(node);
           this.console.println("\t" + this.list);
        }
        
        this.console.println("\nadding new this.list");
        for(int i = 0; i <= 10; i++)
        {
            this.list.add(47);
            this.list.add("hello"); 
        }
        
        this.console.println("removing all");
        this.console.println("\t" + this.list);
        
        for(Object node: this.list)
        {
           
            iterator.remove();
            this.console.println("\t" + this.list);
            
        }
        
        this.console.println("\nadding new this.list");
        for(int j = 0; j <= 10; j++)
        {
            this.list.add(47);
            this.list.add("hello"); 
        }
        
        this.console.println("removing one\n");
        this.console.println("\t" + this.list);
        
        iterator.remove();
        this.console.println("\t" + this.list);
        this.console.println("\tnew head: " + this.list.head.data);
        
        this.console.println("\nremoving half\n");
        this.console.println("\t" + this.list);
        int index = 0;
        for(Object node: this.list)
        {
            
            if(index % 2 == 0)
                iterator.remove();
            index += 1;
        }
        this.console.println("\t" + this.list);
        
        this.console.println("\ninserting and removing\n");
        this.list.insert(1235, 5);
        console.println(this.list);
        this.list.remove(5);
        console.println(this.list);
        
        
        
        boolean mostMethodsPassed =  "[47, hello, 47, hello, 47, hello, 47, hello, 47, hello]".equals(this.list.toString());
        boolean containsWorks = !this.list.contains(3) && this.list.contains(47);
        boolean functionsCorrectly =  mostMethodsPassed && containsWorks && this.list.size() == 10;
        
        console.println(containsWorks);
        if(functionsCorrectly)
            console.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ALL TESTS PASSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
         else
            console.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<TESTS FAILED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return functionsCorrectly;
        
        //TODO: test size() and contains()
        
    }
}

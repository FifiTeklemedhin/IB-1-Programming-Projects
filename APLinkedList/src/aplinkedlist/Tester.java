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
public class Tester 
{
 
    public static void main(String[] args)
    {
        APLinkedList list = new APLinkedList();
        APConsole console = new APConsole("Linked List");
        
        //adding
        console.println("***********************************ADDING************************************");
        console.println("letters");
        list.add("43802sdlja3048");
        list.add("hello world");
        console.println(list);
        
        console.println("add numbers");
        for(int  i = 0; i <= 10; i++)
        {
            list.add(i);
            console.println(list);
        }
        
        console.println("more letters");
        list.add("i");
        list.add("j");
        list.add("l");
        console.println(list);
        
        
        //inserting
        console.println("**********************************INSERTING**********************************");
        console.println("inserting numbers");
        list.insert(11, 11);
        console.println("\n" + list);
        list.insert(23, 5);
        console.println(list + "\n");
        
        
        console.println("inserting letters");
        list.insert("wassup", 11);
        console.println(list);
        list.insert("testing", 5);
        console.println(list + "\n");
        
        //removing
        console.println("**********************************REMOVING**********************************");
        console.println("removing");
        while(list.size() > 0)
        {
            list.remove(list.size() - 1);
            console.println(list);
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
        console.println(list);
        
        //removing each element one by one
        console.println("removing first: wassup");
        console.println("\n");
        
        list.removeFirst("wassup");
        for(int i = 0; i <= 10; i++)
            list.removeFirst(i);
        console.println(list);
        
        
        console.println("\n********************************REMOVING ALL*********************************");
        //filling list
        console.println("adding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
 
        }
        list.insert("wassup", 11);
        list.insert("testing", 5);
        
        console.println(list);
        
        console.println("removing all of each data value: wassup, testing");
        list.removeAll("wassup");
        list.removeAll("testing");
        console.println(list);
        
        //removing all of each data value
        
        
        console.println("\nadding new list");
        for(int i = 0; i <= 10; i++)
        {
            list.add(47);
            list.add("hello"); 
        }
        console.println(list);
        
        
        console.println("removing all of each data value: 47, hello");
        
        list.removeAll(47);
        list.removeAll("hello");
        console.println(list);
    }
}

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
        for(int  i = 0; i <= 10; i++)
        {
            list.add(i);
            console.println(list);
        }
        
        //inserting
        console.println("**********************************INSERTING**********************************");
        list.insert(11, 11);
        console.println("\n" + list);
        list.insert(23, 5);
        console.println(list + "\n");
        
        
        //removing
        console.println("**********************************REMOVING**********************************");
        for(int i = list.size(); i >= 0; i--)
        {
            list.remove(i);
            console.println("removing at index " + i + ": " + list);
        }

        //removing first
        console.println("*******************************REMOVING FIRST*******************************");
        //filling list
        console.println("\n\n");
        //removing frome the first
        for(int i = 0; i <= 10; i++)
        {
            list.add(i);
            console.println(list);
        }
        
        console.println("\n");
        for(int i = 0; i <= 10; i++)
        {
            list.removeFirst(i);
            console.println(list);
        }
    }
}

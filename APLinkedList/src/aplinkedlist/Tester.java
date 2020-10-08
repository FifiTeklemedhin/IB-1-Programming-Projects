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
        
        //list.add(1);
        for(int  i = 0; i <= 10; i++)
        {
            list.add(i);
            console.println(list);
        }
        
        console.println("\n" + list);
    }
}

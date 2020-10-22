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
public class APCyclicallyLinkedListTester extends APDoublyLinkedListTester
{
    private APCyclicallyLinkedList list;
    private APConsole console;

    public APCyclicallyLinkedListTester(APCyclicallyLinkedList list, APConsole console) 
    {
        super(list, console);
        this.list = list;
        this.console = console;
    }
  
    public boolean functionsCorrectly()
    {
        boolean accurateEnds = this.list.tail.next.data.equals("43802sdlja3048") && this.list.head.previous.data.equals("hello world");
        return superFunctionsWork() && accurateEnds;
    }
    public boolean superFunctionsWork()
    {
        
       return generalFunctionsWork() && list.reversed().equals("[hello, 47, hello, 47, hello, 47, hello, 47, hello, 47]");
    }
}


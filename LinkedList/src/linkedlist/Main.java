/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import APClasses.APConsole;

/**
 *
 * @author fifiteklemedhin
 */
public class Main
{
    public static void main(String[] args)
    {
        APConsole console = new APConsole("Main");
        console.println("Linked List functions correctly: " + new APLinkedListTester(new APLinkedList(), new APConsole("LinkedList2")).functionsCorrectly());
        console.println("DoublyLinked List functions correctly: " + new APDoublyLinkedListTester(new APDoublyLinkedList(), new APConsole("Doubly Linked List Tester")).functionsCorrectly());
        console.println("Linked List functions correctly: " + new APCyclicallyLinkedListTester(new APCyclicallyLinkedList(), new APConsole("Cyclically Linked List Tester")).functionsCorrectly());
    }
}

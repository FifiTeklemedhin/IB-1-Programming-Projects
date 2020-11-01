/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import APClasses.APConsole;

/**
 *
 * @author fifiteklemedhin
 */
public class Main 
{
    //still need to test for underflow
    public static APConsole console = new APConsole("Stack, Deque, Queue Tester");
    public static void main(String[] args)
    {
        new StackTester();
        new QueueTester();
        new DequeTester();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csthreads;

import static java.lang.Thread.sleep;
import java.util.Collections;

/**
 *
 * @author fifiteklemedhin
 */
public class Consumer extends Thread{
    private SharedCell cell;
    private int accessCount;
    public static int numConsumers = 0;
    public int value = -1;
    public Consumer(int accessCount, SharedCell cell, String name)
    {
        super(name);
        this.accessCount = accessCount;
        this.cell = cell;
        numConsumers += 1;
    }
    public void run()
    {
        CSThreads.console.println(getName() + " starting up\n"); // Identify myself

        try
        {
            sleep((int) (Math.random() * CSThreads.SLEEP_INTERVAL));
        }
        catch(InterruptedException e)
        {
            CSThreads.console.println(e.toString());
        }
        value = cell.getData(); // Consume by accessing shared cell
        cell.occupied = false;

        CSThreads.console.println(getName() + " is done enrolling");

            
    }
}

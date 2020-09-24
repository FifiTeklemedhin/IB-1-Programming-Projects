/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerandconsumer;

import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author fifiteklemedhin
 */
///////////////////////////////////////////////////
// File: Consumer.java
public class Consumer extends Thread{
    private SharedCell cell;
    private int accessCount;
    public static int numConsumers = 0;
    public static Set<String> currentConsumers = new HashSet<String>();
    public Consumer(int accessCount, SharedCell cell, String name)
    {
        super(name);
        this.accessCount = accessCount;
        this.cell = cell;
        numConsumers += 1;
       
    }
    public void run()
    {
        System.out.println(getName() + " starting up\n"); // Identify myself

        int value = -1;
        while(value != accessCount)
        {
            try
            {
                sleep((int) (Math.random() * ProducerAndConsumer.SLEEP_INTERVAL));
            }
            catch(InterruptedException e)
            {
                System.out.println(e.toString());
            }

            if(currentConsumers.contains(this.getName()))
                break;
            else
            {
                currentConsumers.add(this.getName()); //this before so that add method tests for the last consumer inclusive
                value = cell.getData();

            } // Consume by accessing shared cell
            //System.out.println(currentConsumers);
        }
        
        System.out.println(getName() + " is done consuming");
    }
}
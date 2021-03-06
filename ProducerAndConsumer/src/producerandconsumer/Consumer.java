/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerandconsumer;



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
    public Consumer(int accessCount, SharedCell cell, String name)
    {
        super(name);
        this.accessCount = accessCount;
        this.cell = cell;
        numConsumers += 1;
    }
    public void run()
    {
        ProducerAndConsumer.console.println(getName() + " starting up\n"); // Identify myself

        int value;
        do
        {
        try
        {
            sleep((int) (Math.random() * ProducerAndConsumer.SLEEP_INTERVAL));
        }
        catch(InterruptedException e)
        {
            ProducerAndConsumer.console.println(e.toString());
        }
        value = cell.getData(); // Consume by accessing shared cell
        cell.occupied = false;
    }
        while (value != accessCount);
        ProducerAndConsumer.console.println(getName() + " is done consuming");
    }
}
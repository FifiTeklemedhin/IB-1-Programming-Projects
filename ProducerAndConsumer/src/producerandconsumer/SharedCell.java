/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerandconsumer;

import java.util.HashSet;

/**
 *
 * @author fifiteklemedhin
 */

public class SharedCell{
    private int data;
    private boolean writable;
    public static int timesConsumed = Consumer.numConsumers;
    public static boolean first = true;
    
    public SharedCell()
    {
        data = -1;
        writable = true;
    }
    public synchronized void setData(int data)
    {
        while (! writable && !first)
        { // Producer must wait until
            try
            { // consumer invokes notify()
                if(Consumer.currentConsumers.size() == 0 || !first)
                {
                    System.out.println("Waiting because not all consumers");
                }
                else
                    System.out.println("Waiting because not writeable");
                wait();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.toString());
            }
        }
        System.out.println("\n" + Thread.currentThread().getName() + " setting data to " + data);

        this.data = data;
        writable = false;
        notify(); // Tell consumer to become ready
    }
    public synchronized int getData()
    {
        while (writable)
        { // Consumer must wait until
            try
            { // producer invokes notify()
            wait();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.toString());
            }
        }
            System.out.println(Thread.currentThread().getName() +
            " accessing data " + data);
        
        //notify();
            
        if(Consumer.currentConsumers.size() == Consumer.numConsumers) // if the number of consumers in the set match the amount of consumers
        {
            System.out.print("CONSUMERS REACHED," + Consumer.currentConsumers);
            
            //System.out.println(" SIZE: " + Consumer.currentConsumers.size() + " consumers: " + Consumer.numConsumers);
            Consumer.currentConsumers.clear(); //empty the set
            
            System.out.println("***********************");
            timesConsumed = 0;    
            writable = true;
        }
            
        else
        {
            writable = false;
            //System.out.println(" SIZE: " + Consumer.currentConsumers.size() + " consumers: " + Consumer.numConsumers);
        }
          
        

        notify(); // Tell producer to become ready
        return data;
    }
}
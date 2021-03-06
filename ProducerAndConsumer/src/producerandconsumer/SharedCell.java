
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

public class SharedCell{
    private int data;
    private boolean writable;
    public boolean occupied = false;
    public SharedCell()
    {
        data = -1;
        writable = true;
    }
    public synchronized void setData(int data)
    {
        while (! writable || occupied)
        { // Producer must wait until
            try
            { // consumer invokes notify()
                wait();
            }
            catch(InterruptedException e)
            {
                ProducerAndConsumer.console.println(e.toString());
            }
        }
        ProducerAndConsumer.console.println("\n" + Thread.currentThread().getName() + " setting data to " + data);

        this.data = data;
        writable = false;
        notify(); // Tell consumer to become ready
    }
    public synchronized int getData()
    {
        while (writable || occupied)
        { // Consumer must wait until
            try
            { // producer invokes notify()
            wait();
            }
            catch(InterruptedException e)
            {
                ProducerAndConsumer.console.println(e.toString());
            }
        }
            ProducerAndConsumer.console.println(Thread.currentThread().getName() +
            " accessing data " + data);
        occupied = true;
        writable = true;
        notify(); // Tell producer to become ready
        return data;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csthreads;

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
                CSThreads.console.println(e.toString());
            }
        }
        CSThreads.console.println("\n" + Thread.currentThread().getName() + " is opening spot " + data);

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
                CSThreads.console.println(e.toString());
            }
        }
            CSThreads.console.println("---> " + Thread.currentThread().getName() +
            " is enrolling in spot " + data);
        occupied = true;
        writable = true;
        notify(); // Tell producer to become ready
        return data;
    }
}

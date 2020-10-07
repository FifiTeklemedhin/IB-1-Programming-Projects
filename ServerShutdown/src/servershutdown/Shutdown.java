/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servershutdown;

/**
 *
 * @author fifiteklemedhin
 */
public class Shutdown 
{
    //thread knows to shut down because linked to phonebook server's console, which client prints out to
    //while threads are being interrupted, cannot shut server down
        // have a list of client handlers in daemon, shut each down 
        // then shut daemon down
    // after threads are shut down, shut server down
    private static Boolean threadsAreShutDown;
    
    
    public  boolean ShutDown() throws InterruptedException
    {
        if(threadsAreShutDown == null || threadsAreShutDown == false) 
            wait();
        return threadsAreShutDown;
    }
    public synchronized void canShutDown()
    {
        this.threadsAreShutDown = true;
        notify();
    }
}

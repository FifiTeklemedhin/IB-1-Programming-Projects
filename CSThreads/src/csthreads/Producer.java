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
import static java.lang.Thread.sleep;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fifiteklemedhin
 */
    public class Producer extends Thread{
    private SharedCell cell;
    private int accessCount;
    
    
    public Producer(int accessCount, SharedCell cell)
    {
        super ("Producer");
        this.accessCount = accessCount;
        this.cell = cell;
    }
    public void run()
    {
        CSThreads.console.println(getName() + " starting up\n"); // Identify myself
        for (int i = 1; i <= accessCount; i++)
        {
            try
            {
                sleep((int) (Math.random() * CSThreads.SLEEP_INTERVAL));
            }
            catch(InterruptedException e)
            {
                CSThreads.console.println(e.toString());
            }
        cell.setData(i); // Produce by setting shared cell
        }
    //CSThreads.console.println(getName() + " has opened all spots");
    }
    public SharedCell getCell()
    {
        return this.cell;
    }
    
}
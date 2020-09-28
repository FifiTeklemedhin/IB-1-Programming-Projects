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
import APClasses.APConsole;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
public class CSThreads 
{
    public static APConsole console = new APConsole("CSThreads");
    public static final int SLEEP_INTERVAL = 2000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        SharedCell cell1 = new SharedCell();
        SharedCell cell2 = new SharedCell();
        SharedCell cell3 = new SharedCell();
        SharedCell cell4 = new SharedCell();
        SharedCell cell5 = new SharedCell();

        Producer producer1 = new Producer(20,  cell1);
        Producer producer2 = new Producer(20,  cell2);
        Producer producer3 = new Producer(20,  cell3);
        Producer producer4 = new Producer(20,  cell4);
        Producer producer5 = new Producer(20,  cell5);
        
        ConsumerGroup group1 = makeGroup(1, cell1, producer1);
        ConsumerGroup group2 = makeGroup(2, cell2, producer2);
        ConsumerGroup group3 = makeGroup(3, cell3, producer3);
        ConsumerGroup group4 = makeGroup(4, cell4, producer4);
        ConsumerGroup group5 = makeGroup(5, cell5, producer5);
        
        fill(group1);
        fill(group2);
        fill(group3);
        fill(group4);
        fill(group5);

        /* TODO code application logic here
        five sections means 5 producers
        100 students means 20 threads per producer
        match each student to a class
        
        for each producer
            fill linearly
                produce spots 1-20
                fill spots 1-20
                but students can only fill once
            move on to next node
        */
    }

    public static ConsumerGroup makeGroup(int start, SharedCell cell, Producer producer)
    {
        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        
        for(int i = start; i < start + 20; i++)
            consumers.add(new Consumer(20, cell, "Student" + i));
        return new ConsumerGroup(producer, (start - 1) / 5, consumers);  
    }
    public static void fill(ConsumerGroup group)
    {
         group.fillSection();
        
        try
        {
            sleep(45000);
        }
        catch(InterruptedException e)
        {
            CSThreads.console.println(e.toString());
        }
        CSThreads.console.println("***********************SECTION " + group.section + " IS FULL***********************");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csthreads;

import static csthreads.CSThreads.console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author fifiteklemedhin
 */
public class ConsumerGroup 
{
    public Set<Consumer> scheduledConsumers = new HashSet<Consumer>();
 
    public ArrayList<Consumer> allConsumers;
    Producer producer;
    SharedCell cell;
    int section;
    public ConsumerGroup(Producer producer, int section, ArrayList<Consumer> consumers)
    {
        this.producer = producer;
        this.cell = producer.getCell();
        this.section = section;
        this.allConsumers = consumers;
    }
    
    public  void fillSection()
    {
        this.producer.start();

        for(Consumer consumer : allConsumers)
            consumer.start();

    }
 
    public void add(Consumer consumer)
    {
        allConsumers.add(consumer);
    }
    
    public void add(Consumer[] group)
    {
        for(Consumer consumer: group)
            this.add(consumer);
    }
    
}

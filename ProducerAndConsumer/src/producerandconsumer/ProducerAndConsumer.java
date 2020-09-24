package producerandconsumer;
import java.util.Scanner;
import producerandconsumer.*;


//@author: FifiTeklemedhin
public class ProducerAndConsumer
{
    public static final int SLEEP_INTERVAL = 2000;
    
    public static void main (String args[])
    {
        /* Get the number of accesses from the user,
        create a shared cell, and create and start up
        a producer and a consumer. */
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the number of accesses: ");
        int accessCount = reader.nextInt();
        SharedCell cell = new SharedCell();
        
        Producer producer = new Producer(accessCount, cell);
        
        Consumer consumer1 = new Consumer(accessCount, cell, "Consumer1");
        
        Consumer consumer2 = new Consumer(accessCount, cell, "Consumer2");
        Consumer consumer3 = new Consumer(accessCount, cell, "Consumer3");
        Consumer consumer4 = new Consumer(accessCount, cell, "Consumer4");
        
        System.out.println("Starting threads");
        //System.out.println("number of consumers " + Consumer.numConsumers);
        producer.start();
        
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        
 
    }
    
  }

    
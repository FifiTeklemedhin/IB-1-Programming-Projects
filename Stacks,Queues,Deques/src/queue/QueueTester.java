/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import APClasses.APConsole;
import static queue.Main.console;

/**
 *
 * @author fifiteklemedhin
 */
public class QueueTester 
{
    
    public QueueTester()
    {
        
    if(functionsCorrectly())
            console.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> QUEUE: ALL TESTS PASSED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
        else
           console.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> QUEUE: TESTS FAILED  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
    }
   
    
    public boolean  functionsCorrectly()
    {
       APQueue queue = new APQueue();
       
       console.println(">>>>>>>>>>>>>>>>>>>>> QUEUE <<<<<<<<<<<<<<<<<<<<<<<<<");
       console.println("******* PEEK, ENQUEUE, AND DEQUEUE ********");
       
       if(queue.peek() == null)   
            console.println("no peeking when empty");
       if(queue.dequeue() == null)   
            console.println("no dequeueing when empty");
       
       for(int i = 0; i < 10; i++)
       {
           queue.enqueue(i);
           console.println(queue.dequeue());
       }
       
      
        queue.enqueue("\ndssasa");
        console.println(queue.peek());
        console.println(queue.dequeue() + "\n");
        
        queue.enqueue("fdfsd");
        console.println(queue.peek());
        console.println(queue.dequeue() + "\n");
        
        queue.enqueue(";;;;;'`/.43241sA");
        console.println(queue.peek());
        console.println(queue.dequeue() + "\n");
       
        
        console.println("******* PRINTING AND SIZE ********");
        for(int i = 0; i < 10; i++)
            queue.enqueue(i);
        
        queue.enqueue("dssasa");
        queue.enqueue("fdfsd");
        queue.enqueue(";;;;;'`/.43241sA");
        
        console.println(queue);
        
        return queue.getLength() == 13 && queue.toString().contains("Integer");
    }
}

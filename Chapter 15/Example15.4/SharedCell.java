// Example 15.4

///////////////////////////////////////////////////// File: SharedCell.java

public class SharedCell{
    
   private int data;
   private boolean writable;
   
   public SharedCell(){
      data = -1;
      writable = true;
   }
   
   public synchronized void setData(int data){
      while (! writable){                          // Producer must wait
         try{                                      // until consumer
            wait();                                // invokes notify()
         } 
         catch(InterruptedException e){
            System.out.println(e.toString());
         }
      }
      
      System.out.println(Thread.currentThread().getName() +
                   " setting data to " + data);
      this.data = data;
      writable = false;
      notify();                         // Tell consumer to become ready
   }
   
   public synchronized int getData(){
      while (writable){                            // Consumer must wait
         try{                                      // until producer 
            wait();                                // invokes notify()
         } 
         catch(InterruptedException e){
            System.out.println(e.toString());
         }
      }
      System.out.println(Thread.currentThread().getName() +
                         " accessing data " + data);
      writable = true;
      notify();                         // Tell producer to become ready
      return data;
   }
   
}
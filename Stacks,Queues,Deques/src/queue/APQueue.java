/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author fifiteklemedhin
 */
public class APQueue<E>  
{
    Node<E> first;
    Node<E> last;
    int size = 0;
    //needs to store previous to update first
    /*
        enqueue()
        E dequeue()
        E peek()
        int getLength()
    */
    
    public void enqueue(E data)
    {
        if(size == 0)
        {
            first = new Node(data, null);
            last = first;
            
            size += 1;
            return;
        }
        
        last.next = new Node(data, null, last);
        last = last.next;
        
        size += 1;
        
    }
    
    
    public E dequeue()
    {
        if(size == 0)
            return null;
        
        E front = first.data;
        first = first.next;
        
        size -= 1;
        return front;
    }
    
    
    public E peek()
    {
        if(size == 0)
            return null;
        
        return first.data;
    }
    
    public int getLength()
    {
        return size;
    }
    
}

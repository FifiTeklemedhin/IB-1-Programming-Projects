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
public class APDeque<E> 
{
    /*
        addFront(E)
        addBack(E)
        E getFront()
        E getBack()
        E seeFront()
        E seeBack()
        int getLength()
        String toString() does not print everything in there, just like stack: size that of type this
    */
    
    Node<E> front;
    Node<E> back;
    private int size = 0;
    
    public void addFront(E data)
    {

        if(size == 0)
        {
            front = new Node(data, null);
            back = front;
            
            size += 1;
            return;
        }
        
        back.next = new Node(data, null, back);
        back = back.next;
        
        size += 1;
    }
    
    public void addBack(E data)
    {
        if(size == 0)
        {
            back = new Node(data, null);
            front = back;
            
            size += 1;
            return;
        }
        
        front.next = new Node(data, null, front);
        front = front.next;
        
        size += 1;
    }
    
    
    public E seeFront()
    {
        if(size == 0)
            return null;
        
        return front.data;
    }
    public E seeBack()
    {
        if(size == 0)
            return null;
        
        return front.data;
    }
    
    
    public E getBack()
    {
        if(size == 0)
            return null;
        
        E previousTop = front.data;
        
        size -= 1;
        front = front.previous;
        return previousTop;
    }
    
    public E getFront()
    {
        if(size == 0)
            return null;
        
        Node<E> oldFront = front;
        front = front.next;
        
        size -= 1;
        return oldFront.data;
    }
    
    public int getLength()
    {
        return size;
    }
    
    public String toString()
    {
        if(front == null)
            return "Deque of size " + getLength() + ", type unknown";
        return "Deque of size " + getLength() + ", type " + front.data.getClass();
    }
}

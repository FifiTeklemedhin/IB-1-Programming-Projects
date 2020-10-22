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
        front.previous = new Node(data, front);
        front = front.previous;
        size += 1;
    }
    
    public void addBack(E data)
    {
        back.next = new Node(data, null, back);
        back = back.next;
        size += 1;
    }
    
    
    public E seeFront()
    {
        return front.data;
    }
    public E seeBack()
    {
        return back.data;
    }
    
    
    public E getBack()
    {
        Node<E> oldBack = back;
        back = back.previous;
        
        size -= 1;
        return oldBack.data;
    }
    
    public E getFront()
    {
        Node<E> oldFront = front;
        front = front.next;
        
        size -= 1;
        return oldFront.data;
    }
    
    public int getLength()
    {
        return size;
    }
}

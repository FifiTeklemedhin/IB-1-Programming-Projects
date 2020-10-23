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
public class APStack<E> 
{
   /*
    push(E data)
    E pop()
    E top()
    int length()
   */
    Node<E> top;
    Node<E> bottom;
    int size = 0;

    
    public void push(E data)
    {
        if(size == 0)
        {
            bottom = new Node(data, null);
            top = bottom;
            
            size += 1;
            return;
        }
        
        top.next = new Node(data, null, top);
        top = top.next;
        
        size += 1;
    }
    
    
    public E pop()
    {
        if(size == 0)
            return null;
        
        E previousTop = top.data;
        
        size -= 1;
        top = top.previous;
        return previousTop;
    }
    
    public E top()
    {
        if(size == 0)
            return null;
        
        return top.data;
    }
    
    public int getLength()
    {
        return size;
    }
    
    
    
    
    
    
}

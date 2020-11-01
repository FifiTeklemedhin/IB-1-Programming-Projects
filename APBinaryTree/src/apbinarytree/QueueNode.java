/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbinarytree;


public class QueueNode<E> {
    
    public E data;
    public QueueNode next;
    public QueueNode previous;
    
    public QueueNode( E data, QueueNode<E> next)
    {
        this.data = data;
        this.next = next;
    }
    
    public QueueNode( E data, QueueNode<E> next, QueueNode<E> previous)
    {
        this(data, next);
        this.previous = previous;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

public class Node<E> {
    
    public E data;
    public Node next;
    
    public Node( E data, Node next )
    {
        this.data = data;
        this.next = next;
    }
}

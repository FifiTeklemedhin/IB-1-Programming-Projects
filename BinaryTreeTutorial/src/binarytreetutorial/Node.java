/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreetutorial;

/**
 *
 * @author fifiteklemedhin
 */
public class Node<E> 
{
    int key;
    String name;
    
    Node leftChild;
    Node rightChild;
    
    Node(int key, String name)
    {
        this.key = key;
        this.name = name;
    }
    
    public String toString()
    {
        return ""+ key;
    }
    
}

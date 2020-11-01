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
public class TreeNode<E> 
{
    private E data;
    private TreeNode<E> right;
    private TreeNode<E> left;
    
    public TreeNode(E data)
    {
        this.data = data;
    }
    
}

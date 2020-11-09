/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author fifiteklemedhin
 */
public class BinaryTree<E extends Comparable> implements Iterable<E>
{
    public Node<E> root;
    
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        
        /* testing add, adding non-unique values
            tree.add(5);
            tree.add(1);
            tree.add(2);
            tree.add(3);
            tree.add(0);
            tree.add(3);
            tree.add(2);
            tree.add(5);
            tree.add(30);
        */
        
        
        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        System.out.println(tree);
        
        tree.remove(3);
        //tree.remove(3);
        System.out.println(tree);
    }
    
    public E add(E data)
    {
        return this.add(this.root, data);
    }
    private E add(Node currentNode, E data)
    {
        if(this.root == null)
        {
           root = new Node(data);
           return data; 
        }
       
        
        if(currentNode == null)
        {
            currentNode = new Node(data, null, null);
            return data;
        }
        
        int compare = data.compareTo(currentNode.data);
        
        if(compare < 0)
        {
            if(currentNode.left == null)
            {
                currentNode.left = new Node(data);
                return data;
            }
            return add(currentNode.left, data);
        }
        
        else if(compare > 0)
        {
            if(currentNode.right == null)
            {
                currentNode.right = new Node(data);
                return data;
            }
            return add(currentNode.right, data);
        }
        
        else
        {
            if(subtreeLength(currentNode.right) < subtreeLength(currentNode.left))
            {
                if(currentNode.right == null)
                {
                    currentNode.right = new Node(data);
                    return data;
                }
                return add(currentNode.right, data);
            }
            
            else
            {
                if(currentNode.left == null)
                {
                    currentNode.left = new Node(data);
                    return data;
                }
                return add(currentNode.left, data);
            }
            
        }
        
        
    }
    private E add(Node currentNode, Node nodeToAdd, E data)
    {
        if(nodeToAdd == null)
            return data;
        if(this.root == null)
        {
           root = nodeToAdd;
           return data; 
        }
        
        if(currentNode == null)
        {
            currentNode = nodeToAdd;
            return data;
        }
        int compare = data.compareTo(currentNode.data);
        
        if(compare < 0)
        {
            if(currentNode.left == null)
            {
                currentNode.left = nodeToAdd;
                return data;
            }
            return add(currentNode.left, nodeToAdd, data);
        }
        
        else if(compare > 0)
        {
            if(currentNode.right == null)
            {
                currentNode.right = nodeToAdd;
                return data;
            }
            return add(currentNode.right, nodeToAdd, data);
        }
        
        else
        {
            if(subtreeLength(currentNode.right) < subtreeLength(currentNode.left))
            {
                if(currentNode.right == null)
                {
                    currentNode.right = nodeToAdd;
                    return data;
                }
                return add(currentNode.right, nodeToAdd, data);
            }
            
            else
            {
                if(currentNode.left == null)
                {
                    currentNode.left = nodeToAdd;
                    return data;
                }
                return add(currentNode.left, nodeToAdd, data);
            }
            
        }
        
        
    }
    
    public E remove(E data)
    {
        return this.remove(this.root, null, data);
    }
    public E remove(Node currentNode, Node previousNode, E data)
    {
        
        if(this.root == null || currentNode == null)
        {
           return null; 
        }
        
        int compare = data.compareTo(currentNode.data);
        if(compare != 0)
        {
            if(compare < 0)
                return remove(currentNode.left, currentNode, data);

            else if(compare > 0) 
                return remove(currentNode.right, currentNode, data);
        }

        else
        {
            
            if(currentNode.isLeaf())
            {
                System.out.println("condition 1");
                if(previousNode == null)
                {
                    this.root = null;
                    return data;
                }
                else
                {
                    if(previousNode.right != null && currentNode == previousNode.right)
                    {
                        previousNode.right = null;
                    }
                    if(previousNode.left != null && currentNode == previousNode.left)
                    {
                        previousNode.left = null;
                    }
                }
                return data;
            }
            
            else if(currentNode.right == null && currentNode.left != null)
            {
                if(previousNode.right != null && currentNode == previousNode.right)
                {
                    previousNode.right = previousNode.right.left;
                    return data;
                }
                if(previousNode.left != null && currentNode == previousNode.left)
                {
                    previousNode.left = previousNode.left.left;
                    return data;
                }
            }
            else if(currentNode.left == null && currentNode.right != null)
            {
                if(previousNode.right != null && currentNode == previousNode.right)
                {
                    previousNode.right = previousNode.right.right;
                    return data;
                }
                if(previousNode.left != null && currentNode == previousNode.left)
                {
                    previousNode.left = previousNode.left.right;
                    return data;
                }
            }
            
            if(subtreeLength(currentNode.right) < subtreeLength(currentNode.left))
            {
                
                Node<E> replacementTree = currentNode.left;
                Node right = currentNode.right;

                if(previousNode.right != null && currentNode == previousNode.right)
                {
                    previousNode.right = replacementTree;
                    this.add(previousNode.right, right, (E) right.data);
                    return data;
                }
                if(previousNode.left != null && currentNode == previousNode.left)
                {
                    previousNode.left = replacementTree;
                    this.add(previousNode.left, right, (E) right.data);
                    return data;
                }

            }
            
            else
            {
               Node replacementTree = currentNode.right;
               Node left = currentNode.left;
     
               if(previousNode.right != null && currentNode == previousNode.right)
                {
                    previousNode.right = replacementTree;
                    this.add(previousNode.right, left, (E) left.data);
                    return data;
                }
                if(previousNode.left != null && currentNode == previousNode.left)
                {
                    previousNode.left = replacementTree;
                    this.add(previousNode.left, left, (E) left.data);
                    return data;
                }

               
            }
            
        }
        
        
       return data;
        
    }
    private E remove(Node currentNode, E data, Node target)
    {
        
        if(this.root == null || currentNode == null)
        {
           return data; 
        }
        
        int compare = data.compareTo(currentNode.data);
        if(compare != 0)
        {
            if(compare < 0)
                return remove(currentNode.left, data, target);

            else if(compare > 0) 
                return remove(currentNode.right, data, target);
        }

        else if(currentNode == target)
        {
            //take the bigger side of the current node and keep it there, then take the other side and insert it to that tree
          
            if(currentNode.isLeaf())
            {
                System.out.println("1");
                
                currentNode.right = null;
                currentNode.left = null;
                currentNode = null;
                
                return data;
            }
            
            else if(currentNode.right == null && currentNode.left != null)
            {
                System.out.println("LEFT: " + this.toString(currentNode.left));
                System.out.println("2");
                currentNode = currentNode.left;
                currentNode.left = null;
                currentNode = null;
                return data;
            }
            else if(currentNode.left == null && currentNode.right != null)
            {
                System.out.println("RIGHT: " + this.toString(currentNode.right));
                System.out.println("3");
                currentNode = currentNode.right;
                
                Node<E> parent = getParent(this.root, (E)currentNode.data);
                parent = null;
                return data;
            }
            
            if(subtreeLength(currentNode.right) < subtreeLength(currentNode.left))
            {
                
                System.out.println("4");
                System.out.println("LEFT: " + this.toString(currentNode.left));
                System.out.println("RIGHT: " + this.toString(currentNode.right));
                
                Node<E> replacementTree = currentNode.left;
                Node right = currentNode.right;
               
                currentNode.left = null;
                currentNode.right = null;
               
                this.add(replacementTree, right, (E) right.data);
                currentNode = null;
    
                return data;
               
            }
            
            else
            {
                System.out.println("5");
                System.out.println("LEFT: " + this.toString(currentNode.left));
                System.out.println("RIGHT: " + this.toString(currentNode.right));
                Node replacementTree = currentNode.right;
                Node left = currentNode.left;
     
                currentNode.right = null;
                currentNode.left = null;
               
                this.add(replacementTree, left, (E) left.data);
                currentNode = null;
               
                return data;
               
            }
            
        }
        
        else
        {
            if(currentNode.right != null)
                return remove(currentNode.right, target, data);
            if(currentNode.left != null)
                return remove(currentNode.left, target, data);
        }
        
       return data;
        
    }
    
    public boolean contains(Node currentNode, E data)
    {
        if(currentNode == null)
            return false;
        
        
        int compare = data.compareTo(currentNode.data);
        
        if(compare < 0)
            return contains(currentNode.left, data);
        
        
        else if(compare > 0)
            return contains(currentNode.right, data);
        
        else
            return true;
        
    }
    public boolean contains(Node currentNode, Node target)
    {
        if(currentNode == null)
            return false;
        
        E targetData = (E) target.data;
        int compare = targetData.compareTo(currentNode.data);
        
        if(compare < 0)
            return contains(currentNode.left, target);
        
        
        else if(compare > 0)
            return contains(currentNode.right, target);
        
        else
        {
            if(target == currentNode)
                return true;
            contains(currentNode.right, target);
            return contains(currentNode.left, target);
        }

    }
    
    private Node<E> getParent(Node<E> currentNode, E data)
    {
        if(this.root == null)
        {
           return null; 
        }
        else if(currentNode.isLeaf())
        {
            return null;
        }
        
        else
        {
            System.out.println(currentNode.data);
            if(currentNode.left != null && currentNode.left.data == data)
                return currentNode;
        
            else if(currentNode.right != null && currentNode.right == data)
                return currentNode;

            else
            {
                int compare = data.compareTo(currentNode.data);
                
                if(compare > 1 && currentNode.right != null)
                    getParent(currentNode.right, data);
                else if(compare < 1 && currentNode.left != null)
                    getParent(currentNode.left, data);
                else
                    return null;
            }
           
        }
        return null;
    }
    public int length()
    {
        return inOrder(this.root, new APQueue()).getLength();
    }
    public int subtreeLength(Node<E> start)
    {
        return inOrder(start, new APQueue()).getLength();
    }
    
    private APQueue inOrder(Node<E> currentNode, APQueue queue)
    {
        if(currentNode == null)
            return queue;

        
        if(currentNode.left != null)
            inOrder(currentNode.left, queue);
   
        queue.enqueue(currentNode);
        
        if(currentNode.right != null)
            inOrder(currentNode.right, queue);
        
        return queue;
    }
    
    private APQueue preOrder(Node<E> currentNode, APQueue queue)
    {
        if(currentNode == null || currentNode.isLeaf())
            return queue;
        
        queue.enqueue(currentNode);
        
        if(currentNode.left != null)
            preOrder(currentNode.left, queue);

        if(currentNode.right != null)
            preOrder(currentNode.right, queue);
        
        return queue;
    }
    
    private APQueue postOrder(Node<E> currentNode, APQueue queue)
    {
        if(currentNode == null || currentNode.isLeaf())
            return queue;
        
        if(currentNode.left != null)
            preOrder(currentNode.left, queue);

        if(currentNode.right != null)
            preOrder(currentNode.right, queue);
        
        queue.enqueue(currentNode);
        
        return queue;
    }
  
    public String toString()
    {
        APQueue queue = inOrder(this.root, new APQueue());
        //APQueue queue = breadthFirst(this.root, new APQueue(), new HashSet());
        String result = "TREE: ";
        //System.out.println("length: " + queue.getLength());
        int len = queue.getLength();
        for(int i = 0; i < len; i++)
        {
            Node current = (Node) queue.dequeue();
            result += (E)current.data + ", ";
        }
        
        return result;
    }
    public String toString(Node start)
    {
        APQueue queue = inOrder(start, new APQueue());
        String result = "TREE: ";
        int len = queue.getLength();
        for(int i = 0; i < len; i++)
        {
            Node current = (Node) queue.dequeue();
            result += (E)current.data + ", ";
        }
        return result;
    }
    
    @Override
    public Iterator<E> iterator() 
    {
        return new inOrderIterator(this);
    }
    public Iterator<E> inOrderIterator() 
    {
        return new inOrderIterator(this);
    }
    public Iterator<E> preOrderIterator() 
    {
        return new PreOrderIterator(this);
    }
    public Iterator<E> postOrderIterator() 
    {
       return new PostOrderIterator(this);
    }
    public Iterator<E> breadthFirstIterator()
    {
        return new BreadthFirstIterator(this);
    }
    
    private class inOrderIterator implements Iterator<E>
    {
        private BinaryTree tree; 
        private APQueue queue = new APQueue();
        public inOrderIterator(BinaryTree tree)
        {
            this.tree = tree;      
            this.queue = inOrder(this.tree.root, this.queue);
        }
        
       @Override
        public boolean hasNext() 
        {
           return this.queue.peek() != null;
        }

        @Override
        public E next() 
        {
            if(hasNext())
            {
                Node current = (Node)this.queue.dequeue();
                E currentData = (E) current.data;
                return currentData;
                
            }
            return null;
        }
        
        @Override
        public void remove()
        {
           if(hasNext())
           {
               Node current = (Node)this.queue.dequeue();
               E currentData = (E) current.data;
               this.tree.remove(this.tree.root, current, currentData);
           }
        
        }
    }
    
    private class PreOrderIterator implements Iterator<E>
    {
        private BinaryTree tree; 
        private APQueue queue = new APQueue();
        public PreOrderIterator(BinaryTree tree)
        {
            this.tree = tree;      
            this.queue = preOrder(this.tree.root, this.queue);
        }
        
        @Override
        public boolean hasNext() 
        {
           return this.queue.peek() != null;
        }

        @Override
        public E next() 
        {
            if(hasNext())
            {
                Node current = (Node)this.queue.dequeue();
                E currentData = (E) current.data;
                return currentData;
                
            }
            return null;
        }
        
        @Override
        public void remove()
        {
           if(hasNext())
           {
               Node current = (Node)this.queue.dequeue();
               E currentData = (E) current.data;
               this.tree.remove(this.tree.root, current, currentData);
           }
        
        }
    }
    
    private class PostOrderIterator implements Iterator<E>
    {
        private BinaryTree tree; 
        private APQueue queue = new APQueue();
        public PostOrderIterator(BinaryTree tree)
        {
            this.tree = tree;      
            this.queue = postOrder(this.tree.root, this.queue);
        }
        
        @Override
        public boolean hasNext() 
        {
           return this.queue.peek() != null;
        }

        @Override
        public E next() 
        {
            if(hasNext())
            {
                Node current = (Node)this.queue.dequeue();
                E currentData = (E) current.data;
                return currentData;
                
            }
            return null;
        }
        
        @Override
        public void remove()
        {
           if(hasNext())
           {
               Node current = (Node)this.queue.dequeue();
               E currentData = (E) current.data;
               this.tree.remove(this.tree.root, current, currentData);
           }
        
        }
    }
    
    /*
        Make a queue, add the first node
        Whenever next is called dequeue it and add its two children
        But make sure they aren’t null first
    */
    
     private class BreadthFirstIterator implements Iterator<E>
    {
        private BinaryTree tree; 
        private APQueue queue = new APQueue();
        public BreadthFirstIterator(BinaryTree tree)
        {
            this.tree = tree;      
            this.queue = new APQueue();
            this.queue.enqueue(this.tree.root);
        }
        
        @Override
        public boolean hasNext() 
        {
           return this.queue.peek() != null;
        }

        @Override
        public E next() 
        {
            if(hasNext())
            {
                Node current = (Node) this.queue.peek();
                if(!current.isLeaf())
                {
                    if(current.left != null)
                        this.queue.enqueue(current.left);
                    
                    if(current.right != null)
                        this.queue.enqueue(current.right);
                }
                
                return (E) current.data;
                              
            }
            return null;
        }
        
        @Override
        public void remove()
        {
           if(hasNext())
           {
               Node front = (Node) this.queue.dequeue();
               this.tree.remove(this.tree.root, front, (E)front.data);
           }
            
        
        }
    }
}



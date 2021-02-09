/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author fifiteklemedhin
 */
public class HashTable<E> implements Collection {

    private int size;
    private int lazyCount;
    private Object [] table;
    private Object lazyToken;
       
    /*QUESTIONS
        * add(): had to take in type object instead of E, is that ok?
        * is size inclusive of lazy tokens? yes
        * to remove, need to have the exact item in there
    */
    public static void main (String[] args)
    {
        HashTable table = new HashTable(10);
        
        // Adding/removing manually: works
        System.out.println("ADDING/REMOVING MANUALLY: ");
        System.out.println(table.add(1));
        System.out.println(table.add(2));
        System.out.println(table.add(3));
        System.out.println(table.remove(1));
        System.out.println(table.remove(2));
        
        // Adding in for-loop: works so long as ending index is static (ie not size/length of table, which changes)
        System.out.println("\nADDING W/ LOOP: ");
        for(Integer i = 0; i <= 5; i ++)
         table.add(i);

        // Iterator: works 
        System.out.println("\nITERATOR: ");
        
        Iterator<HashTable> iterator = table.iterator();
        
        for(Object i: table)
            System.out.println(i);
        
        // Size/refresh/lazycount: work 
        System.out.println("\nSIZE/REFRESH/LAZYCOUNT: ");
        
        table.refresh();
        System.out.println(table.size());
        System.out.println(table.lazyCount);
        
        // Contains/isEmpty: works
        System.out.println("\nCONTAINS/ISEMPTY: ");
        
        System.out.println(table.contains(1));
        System.out.println(table.isEmpty());
        
    }
    
    public HashTable(int size)
    {
        this.size = size;
        this.lazyCount = 0;
        this.table = new Object[size];
        this.lazyToken = new Object();
        
    }
    
    public HashTable()
    {
        this(100);
    }
        
    private void resize(int newSize) // when all filled are 10% of size
    {
        Object[] newTable = new Object[newSize]; // make a new table that is 10x the size
            
        for(int j = 0; j < this.table.length; j++) //iterate through the existing table and find the filled spogts
        {
            if(this.table[j] != null) //KEEPS LAZY TOKENS
                newTable[Math.abs(this.table[j].hashCode()) % newTable.length] = this.table[j]; //fill the new table with the old table data in the same indices
        }
        this.table = newTable; //set the old table to be the new table

    }
    private void refresh() //deletes all lazy tokens when 10% of size
    {
                    
        if(this.lazyCount / this.table.length >= .1) // if lazytokesn are 10% of size, resize
             this.table = new Object[this.table.length * 10];
        
        for(int j = 0; j < this.table.length; j++) //deletes lazy tokens and reinserts the items    
        {
            if(this.table[j] == this.lazyToken)
                this.table[j] = null;   
        }
        
        /*   
        Object[] placeholder = new Object[this.table.length]; 
        for(int j = 0; j < this.table.length; j++) //copies all of the objects in the table to another list
        {
            placeholder[j] = this.table[j];
        }
        
        for(int j = 0; j < placeholder.length; j++) //deletes lazy tokens and reinserts the items    
        {
            if(placeholder[j] != this.lazyToken && placeholder[j] != null)
                add(placeholder[j]);   
        }
        */
        
        this.size -= lazyCount;
        this.lazyCount = 0;
       
    }

    @Override
    public boolean add(Object item) // TODO: had to do object instead of E, is that ok?
    {
       if(Math.abs(item.hashCode()) % this.table.length > this.table.length) // if hashcode wont fit in table, resize to include
            resize(Math.abs(item.hashCode()) % this.table.length);
        
       else if(this.size / this.table.length >= .1) // if 10% of the table is filled, resize
           resize(this.table.length * 10);
           
       if(this.table[Math.abs(item.hashCode()) % this.table.length] == null) // if index corresponding with hash is empty, fill spot
       {
           this.table[Math.abs(item.hashCode()) % this.table.length] = item;
           this.size++;
           return true;
       }
       
       else 
       {
           for(int j = Math.abs(item.hashCode()) % this.table.length; j < this.table.length; j++) // else fill the nearest spot after the intended index
           {
               if(this.table[j] == null)
               {
                   this.table[j] = item;
                   this.size++;
                   return true;
               }
           }
       }
       return false; 
    }
    
    @Override
    public boolean remove(Object item) 
    {
        if(Math.abs(item.hashCode()) % this.table.length > this.table.length) //item cannot be in list if code is greater than length
           return false;
        
        if(this.table[Math.abs(item.hashCode()) % this.table.length] == item) //if item is in the ideal index, remove and replace with lazy token
        {
            this.table[Math.abs(item.hashCode()) % this.table.length] = this.lazyToken;
            
            this.lazyCount ++;
            return true;
        }
        
        else//if item is somewhere else in list, remove and replace with lazy token
        {
           for(int j = Math.abs(item.hashCode()) % this.table.length; j < this.table.length; j++) 
           {
               if(this.table[j] == item)
               {
                   this.table[j] = this.lazyToken;
                   
                   this.lazyCount ++;
                   return true;
               }
           }
        }
        
        return false;
            
    }
    
    @Override
    public int size() 
    {
        return this.size;
    }

    @Override
    public boolean isEmpty() // checks for any items stored, not including lazytokens
    {
        return this.size - this.lazyCount == 0;
    }

    @Override
    public boolean contains(Object item) 
    {
        if(Math.abs(item.hashCode()) % this.table.length > this.table.length) //item cannot be in list if code is greater than length
           return false;
        
        if(this.table[Math.abs(item.hashCode()) % this.table.length] == item) //if item is in the ideal index
            return true;
        
        else //if item is somewhere else in list
        {
           for(int j = Math.abs(item.hashCode()) % this.table.length; j < this.table.length; j++) 
           {
               if(this.table[j] == item)
                return true;
           }
        }
        
        return false;
    }

    @Override
    public Iterator iterator() 
    {
      return new HashTableIterator(this);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private class HashTableIterator implements Iterator<E>
    {
        private HashTable table;
        private int index;

        public HashTableIterator(HashTable table)
        {
            this.table = table;
            this.index = 0;
        }
        
        @Override
        public boolean hasNext() 
        {
           if(index == this.table.size()) //if at end, cannot have next
               return false;
           
           for(int j = this.index + 1; j < this.table.size(); j++) //if there is a non-null, non-lazy object
           {
               if(this.table.table[j] != null && this.table.table[j] != this.table.lazyToken)
                   return true;
           }
           
           return false;
        }

        @Override
        public E next() 
        {
            for(int j = this.index + 1; j < this.table.size(); j++) //find the next non-null/non-lazy object if it's there
            {
               if(this.table.table[j] != null && this.table.table[j] != this.table.lazyToken)
               {
                   this.index += j-index;
                   return (E) this.table.table[j];
               }
            }
            
            return null;
        }
        
        @Override
        public void remove()
        {
            this.table.remove(this.table.table[index]);
        }
        
    }
}

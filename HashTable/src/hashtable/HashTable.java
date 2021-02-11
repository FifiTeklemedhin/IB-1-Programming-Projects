/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.util.ArrayList;
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
        
        //******************** Basic Hashtable Properties ********************
        
        // Adding/removing manually: works
        System.out.println("ADDING/REMOVING MANUALLY: ");
        System.out.println("\t" + table.add(1));
        System.out.println("\t" + table.add(2));
        System.out.println("\t" + table.add(3));
        System.out.println("\t" + table.remove(1));
        System.out.println("\t" + table.remove(2));
        
        // Adding in for-loop: works so long as ending index is static (ie not size/length of table, which changes)
        System.out.println("\nADDING W/ LOOP: ");
        for(Integer i = 0; i <= 5; i ++)
         table.add(i);

        // Iterator: works 
        System.out.println("\nITERATOR: ");
        
        Iterator<HashTable> iterator = table.iterator();
        
        for(Object i: table)
            System.out.println("\t" + i);
      
        // Size/refresh/lazycount: works 
        System.out.println("\nSIZE/REFRESH/LAZYCOUNT: ");
   
        table.refresh();

        System.out.println("\t" + table.size());
        System.out.println("\t" + table.lazyCount);
       
        
        // Contains/isEmpty: works
        System.out.println("\nCONTAINS/ISEMPTY: ");
        
        System.out.println("\t" + table.contains(1));
        System.out.println("\t" + table.isEmpty());
        
        //******************** Collection Properties ********************
        
        //toArray(): works
        Object[] modArr = table.toArray();
    
        System.out.println("\nTO ARRAY: ");
        for(int i = 0 ; i < modArr.length; i ++)
            System.out.println("\t" + modArr[i]);
         
        //TODO: toArray(Obj[])
        System.out.println("\nTO ARRAY(Obj[]): TODO");
      
        //containsAll(): works
        System.out.println("\nCONTAINS ALL: ");
        
            //should return true
            System.out.println("\t" + table.containsAll(table));
            
            //should return true
            ArrayList trueArr = new ArrayList();
            Object[] toArr = table.toArray();
            for(Object i : toArr)
            {
                trueArr.add(i);
            }
            System.out.println("\t" + table.containsAll(trueArr));
        
            //should return false
            ArrayList falseArr = new ArrayList();
            falseArr.add(0);
            falseArr.add(1);
            falseArr.add(2);
            falseArr.add(3);
            falseArr.add(4);
            falseArr.add(5);
            falseArr.add(6);
            System.out.println("\t" + table.containsAll(falseArr));
        
        //addAll(): works
        System.out.println("\nADD ALL: ");
        
            //should not work
            ArrayList tensArr = new ArrayList();
            for(int i = 10; i < 100; i+= 10)
                tensArr.add(i + "");
     
            System.out.println("\t" + table.addAll(tensArr));
        
            System.out.print("\t");
            for(Object i: table.toArray())
            System.out.print(i + ", ");
        
           //should work
           System.out.println("\n");
           tensArr = new ArrayList();
           for(int i = 10; i < 100; i+= 10)
                tensArr.add(i);

           System.out.println("\t" + table.addAll(tensArr));

           System.out.print("\t");
           for(Object i: table.toArray())
               System.out.print(i + ", ");

        //removeAll(): works
        System.out.println("\n\nREMOVE ALL: ");
        table.removeAll(tensArr);
        System.out.print("\t");
        for(Object i: table.toArray())
               System.out.print(i + ", ");
                
                

        
        
}
    
    public HashTable(int length)
    {
        this.size = 0;
        this.lazyCount = 0;
        this.table = new Object[length];
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
             this.resize(this.table.length * 10);
        
        for(int j = 0; j < this.table.length; j++) //deletes lazy tokens and reinserts the items    
        {
            if(this.table[j] == this.lazyToken)
                this.table[j] = null;;

        }
        
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
    public Object[] toArray() 
    {
        Object[] modifiedTable = new Object[this.size - lazyCount];
        int modifiedIndex = 0;
        for(int i = 0 ; i < this.table.length; i ++)
        {
            if(this.table[i] != this.lazyToken && this.table[i] != null)
            {
                modifiedTable[modifiedIndex] = this.table[i];
                modifiedIndex += 1;
            }
        }
        
        return modifiedTable;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean contains = true;
        
        for(Object i : c)
        {
            if(this.contains(i) == false)
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        
        // finds objects from hashtable and other collection to compare whether they store same type of data
        Object cObj = "";
        for(Object i : c)
        {
            if(i != null)
               cObj = i;
        }
        
        Object thisObj = 1;
        for(Object i : this)
        {
            if(i != null)
               thisObj = i;
        }
        
        //if they don't, return false
        if(!thisObj.getClass().equals(cObj.getClass()))
            return false;
        
        //else adds
        for(Object i : c)
        {
            this.add(i);
        }    
        return true;
    }
    
    
    @Override
    public boolean removeAll(Collection c) {
    // finds objects from hashtable and other collection to compare whether they store same type of data
        Object cObj = "";
        for(Object i : c)
        {
            if(i != null)
               cObj = i;
        }
        
        Object thisObj = 1;
        for(Object i : this)
        {
            if(i != null)
               thisObj = i;
        }
        
        //if they don't, return false
        if(!thisObj.getClass().equals(cObj.getClass()))
            return false;
        
        //else adds
        for(Object i : c)
        {
            this.remove(i);
        }    
        return true;
    }
    
    @Override
    public Object[] toArray(Object[] a) {
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
        private boolean zeroPassed; //makes sure you start at index 0

        public HashTableIterator(HashTable table)
        {
            this.table = table;
            this.index = 0;
            this.zeroPassed = false;
        }
        
        @Override
        public boolean hasNext() 
        {
           if(index == this.table.table.length) //if at end, cannot have next
               return false;
           
           for(int j = this.index + 1; j < this.table.table.length; j++) //if there is a non-null, non-lazy object
           {
               if(this.table.table[j] != null && this.table.table[j] != this.table.lazyToken)
                   return true;
           }
           
           return false;
        }

        @Override
        public E next() 
        {
            if(index == 0 && !zeroPassed)
            {
                zeroPassed = true;
                return (E) this.table.table[0];
            }
            
            for(int j = this.index + 1; j < this.table.table.length; j++) //find the next non-null/non-lazy object if it's there
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.util.Iterator;

/**
 *
 * @author fifiteklemedhin
 */
public class Tester {
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
        
        //table.refresh(); private func
        System.out.println(table.size());
        //System.out.println(table.lazyCount); private var
        
        // Contains/isEmpty: works
        System.out.println("\nCONTAINS/ISEMPTY: ");
        
        System.out.println(table.contains(1));
        System.out.println(table.isEmpty());
        
    }
    
}

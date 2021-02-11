/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import APClasses.APConsole;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author fifiteklemedhin
 */
public class Tester {
    public static void main (String[] args)
    {
        APConsole console = new APConsole("HashTable Tester");
        HashTable table = new HashTable(10);
        
        //******************** Basic Hashtable Properties ********************
        
        // Adding/removing manually: works
        console.println("ADDING/REMOVING MANUALLY: ");
        console.println("\t" + table.add(1));
        console.println("\t" + table.add(2));
        console.println("\t" + table.add(3));
        console.println("\t" + table.remove(1));
        console.println("\t" + table.remove(2));
        
        // Adding in for-loop: works so long as ending index is static (ie not size/length of table, which changes)
        console.println("\nADDING W/ LOOP: ");
        for(Integer i = 0; i <= 5; i ++)
         table.add(i);

        // Iterator: works 
        console.println("\nITERATOR: ");
        
        Iterator<HashTable> iterator = table.iterator();
        
        for(Object i: table)
            console.println("\t" + i);
      
        // Size/refresh/lazycount: works 
        console.println("\nSIZE/REFRESH/LAZYCOUNT: ");
            //table.refresh();
        console.println("\t" + table.size());
            //console.println("\t" + table.lazyCount);
       
        
        // Contains/isEmpty: works
        console.println("\nCONTAINS/ISEMPTY: ");
        
        console.println("\t" + table.contains(1));
        console.println("\t" + table.isEmpty());
        
        //******************** Collection Properties ********************
        
        //toArray(): works
        Object[] modArr = table.toArray();
    
        console.println("\nTO ARRAY: ");
        for(int i = 0 ; i < modArr.length; i ++)
            console.println("\t" + modArr[i]);
         
        //TODO: toArray(Obj[])
        console.println("\nTO ARRAY(Obj[]):");
        
        String[] hundredsArr = new String[10];
            for(int i = 100; i < hundredsArr.length; i+= 100)
                hundredsArr[i ] = i + "";
        //table.toArray(hundredsArr);
        console.print("\t");
        for(Object i: table)
            console.print(i.getClass() + ", ");
        
        
        //containsAll(): works
        console.println("\nCONTAINS ALL: ");
        
            //should return true
            console.println("\t" + table.containsAll(table));
            
            //should return true
            ArrayList trueArr = new ArrayList();
            Object[] toArr = table.toArray();
            for(Object i : toArr)
            {
                trueArr.add(i);
            }
            console.println("\t" + table.containsAll(trueArr));
        
            //should return false
            ArrayList falseArr = new ArrayList();
            falseArr.add(0);
            falseArr.add(1);
            falseArr.add(2);
            falseArr.add(3);
            falseArr.add(4);
            falseArr.add(5);
            falseArr.add(6);
            console.println("\t" + table.containsAll(falseArr));
        
        //addAll(): works
        console.println("\nADD ALL: ");
        
            //should not work
            ArrayList tensArr = new ArrayList();
            for(int i = 10; i < 100; i+= 10)
                tensArr.add(i + "");
     
            console.println("\t" + table.addAll(tensArr));
        
            console.print("\t");
            for(Object i: table.toArray())
            console.print(i + ", ");
        
           //should work
           console.println("\n");
           tensArr = new ArrayList();
           for(int i = 10; i < 100; i+= 10)
                tensArr.add(i);

           console.println("\t" + table.addAll(tensArr));

           console.print("\t");
           for(Object i: table.toArray())
               console.print(i + ", ");

        //removeAll(): works
        console.println("\n\nREMOVE ALL: ");
        table.removeAll(tensArr);
        console.print("\t");
        for(Object i: table.toArray())
               console.print(i + ", ");
                
        //retainAll(): works
        console.println("\nRETAIN ALL: ");
        table.addAll(tensArr);
        table.retainAll(tensArr);
        console.print("\t");
        for(Object i: table)
               console.print(i + ", ");        

        
    }
    
}

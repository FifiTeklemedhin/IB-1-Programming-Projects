/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import APClasses.APConsole;
import java.awt.Font;

/**
 *
 * @author root
 */
public class ADT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        APConsole console = new APConsole("ADT Tester");
        //makes the font monospaced because ocd
        console.setTextFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        //APLinkedList tester
        try {
            APLinkedList<String> list = new APLinkedList<>();
            APLinkedList<String> list1 = new APLinkedList<>();
            //adds items to the list
            for (int i = 0; i < 10; i++) {
                list1.add("1");
                list.add(i + "");
            }

            //size test
            if (list.size() != 10 || list1.size() != 10) {
                throw new Exception("Failed the size test!\nShould have been 10, was " + list.size());
            }
            
            
            for (int i = 1; i < 10; i++) {
                //firstIndexOf test
                int index = list.firstIndexOf(i + "");
                System.out.println("TESTING: " + i + "\n" + list + "\n");
                if (index != i) {
                    throw new Exception("Failed the firstIndexOf test!\nShould have been " + i + ", was " + index);
                }
            }
            //firstIndexOf test
            int index = list.firstIndexOf("15");
            
            if (index != -1) {
                throw new Exception("Failed the firstIndexOf test!\nShould have been -1, was " + index);
            }

            //conatins test
            boolean has = list.contains("1");
            if (!has) {
                throw new Exception("Failed the contains test!\nShould have been true, was false");
            }
            has = list.contains("15");
            if (has) {
                throw new Exception("Failed the contains test!\nShould have been false, was true");
            }
            
            //add test
            list.add("1");
            list.add("1");
            list.add("2");
            list.add("3");

            //size test
            if (list.size() != 14) {
                throw new Exception("Failed the size test!\nShould have been 14, was " + list.size());
            }

            //contains test
            if (!list.contains("1")) {
                throw new Exception("Failed the contains test!\nShould have been true, was " + list.contains("1"));
            }

            //remove test
            String data = list.remove("1");
            if (!data.equals("1")) {
                throw new Exception("Failed the remove test!\nShould have been 1, was " + data);
            }
            String data1 = list1.remove("1");
            if (!data1.equals("1")) {
                throw new Exception("Failed the remove test!\nShould have been 1, was " + data1);
            }

            //size test
            if (list.size() != 11 || list1.size() != 0) {
                throw new Exception("Failed the size test!\nShould have been 10, was " + list.size());
            }

            console.println("The APLinkedList passed all test cases!");
        } catch (Exception e) {
            console.println("Either the APLinkedList class doesn't exist,\nit failed a test case, or it threw another error.");
            console.println(e);
            console.println("");
        }

    }

}

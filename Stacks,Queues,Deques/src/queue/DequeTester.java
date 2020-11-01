/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import APClasses.APConsole;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import static queue.Main.console;

/**
 *
 * @author fifiteklemedhin
 */
public class DequeTester 
{
    /* STACK TESTER OBJECTS */
    static APDeque<Double> stack = new APDeque<Double>();
    static JFileChooser chooser = new JFileChooser();
    static ArrayList<String> expressionLines;

    static Scanner reader;

    public DequeTester()
    {
        if(functionsCorrectly())
            console.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DEQUEUE: ALL TESTS PASSED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
        else
           console.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DEQUEUE: TESTS FAILED  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
    }
   
    
    public boolean functionsCorrectly()
    {
        return functionsAsQueue() && functionsAsStack();
    }
    public boolean functionsAsQueue()
    {

       APDeque queue = new APDeque();
       
       console.println(">>>>>>>>>>>>> QUEUE >>>>>>>>>>>>>>>");
       
       console.println("**** PEEK, ENQUEUE, AND DEQUEUE ****");
       
       if(queue.seeFront() == null)   
            console.println("no peeking when empty");
       if(queue.getFront() == null)   
            console.println("no dequeueing when empty");
       for(int i = 0; i < 10; i++)
       {
           queue.addFront(i);
           console.println(queue.getFront());
       }
       
      
        queue.addFront("\ndssasa");
        console.println(queue.seeFront());
        console.println(queue.getFront() + "\n");
        
        queue.addFront("fdfsd");
        console.println(queue.seeFront());
        console.println(queue.getFront() + "\n");
        
        queue.addFront(";;;;;'`/.43241sA");
        console.println(queue.seeFront());
        console.println(queue.getFront() + "\n");
       
        
        console.println("**** PRINTING AND SIZE ****");
        for(int i = 0; i < 10; i++)
            queue.addFront(i);
        
        queue.addFront("dssasa");
        queue.addFront("fdfsd");
        queue.addFront(";;;;;'`/.43241sA");
        
        console.println(queue);
        
        return queue.getLength() == 13 && queue.toString().contains("Integer");

    }

    public boolean functionsAsStack()
    {
       console.println("\n>>>>>>>>>>>>>> STACK >>>>>>>>>>>>");
       if(stack.seeFront() == null)   
            console.println("no peeking when empty");
       if(stack.getFront() == null)   
            console.println("no popping when empty\n");
       
        String expressions = "4 5 6 * + 3 -\n" +
                            "34 \n" +
                            "34 22 +\n" +
                            "34 22 2 * +\n" +
                            "34 22 * 2 +\n" +
                            "34 22 + 2 *";
        String answer = "";
        // Reading Input
        reader = new Scanner(expressions);
        expressionLines = new ArrayList<String>();
        //loops through entire file, evaluates each expression
        while(reader.hasNextLine())
        {
            String next = reader.nextLine();
            answer += next + " ---> " + eval(next);
            
            console.println(next + " ---> " + eval(next));
        }
        
        String correct = "4 5 6 * + 3 - ---> 31.0\n" +
                        "34  ---> 34.0\n" +
                        "34 22 + ---> 56.0\n" +
                        "34 22 2 * + ---> 78.0\n" +
                        "34 22 * 2 + ---> 750.0\n" +
                        "34 22 + 2 * ---> 112.0";
        return answer.substring(0, answer.length()).equals(correct.replace("\n","")) && stack.toString().contains("double") || stack.toString().contains("Double");
    }
    public static double eval(String s)
    {
        Scanner tokens = new Scanner(s);
        //loops through entire expression
        while(tokens.hasNext())
        {
            String token = tokens.next();

            // adds the number to the stack
            if(isNumber(token))
                stack.addBack(Double.parseDouble(token));
            //operates on the two most recent nodes if the token is an operator
            else 
                operate(stack.getBack(), stack.getBack(), token);  
        }
        return stack.seeBack();
    }
    
    // operates based off of token
    public static void operate(double num1, double num2, String s)
    {
        if(s.equals("+"))
            stack.addBack(num2 + num1);
        if(s.equals("/"))
           stack.addBack(num2 / num1);
        if(s.equals("*"))
            stack.addBack(num2 * num1);
        if(s.equals("-"))
            stack.addBack(num2 - num1);
       
    }
   
    //need method just in a number is more than one char
    public static boolean isNumber(String s)
    {
        for(int i = 0; i < s.length(); i++)
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.')
                return false;
        return true;
    }
    
}

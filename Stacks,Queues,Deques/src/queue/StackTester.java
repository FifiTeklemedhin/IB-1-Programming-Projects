/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import APClasses.APConsole;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fifiteklemedhin
 */
public class StackTester 
{
        
    static APConsole console = new APConsole("Stack Tester");
    
    //evaluate postfix expressions
    // TODO: prompt user for file of postfix expressions (one per line)
    // TODO: ask if we should be able validate a file based off of whether or not postfix expressions are correct
    // TODO: use stackbased algorithm to evaluate input expression
    // use 2 scanners: 1 for getting expressions, other for reading them
    static APStack<Double> stack = new APStack<Double>();
    static JFileChooser chooser = new JFileChooser();
    static ArrayList<String> expressionLines;
    
    static File file;
    static Scanner reader;

    static String inputPath;
    
    public StackTester()
    {
        console.println("STACK WORKS: " + worksCorrectly());
    }
    public static void main(String[] args)
    {
        new StackTester();
    }
    
    public boolean worksCorrectly()
    {
        console.println("Give me an input file with postfix \n"
                                           + " expressions on each line, and I will evaluate each one.\n");
        
        // Reading and Validating Files
        
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
        return answer.substring(0, answer.length()).equals(correct.replace("\n",""));
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
                stack.push(Double.parseDouble(token));
            //operates on the two most recent nodes if the token is an operator
            else 
                operate(stack.pop(), stack.pop(), token);  
        }
        return stack.top();
    }
    
    // operates based off of token
    public static void operate(double num1, double num2, String s)
    {
        if(s.equals("+"))
            stack.push(num2 + num1);
        if(s.equals("/"))
           stack.push(num2 / num1);
        if(s.equals("*"))
            stack.push(num2 * num1);
        if(s.equals("-"))
            stack.push(num2 - num1);
       
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

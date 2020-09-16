/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postfixexpressions;

 
import APClasses.APConsole;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fifiteklemedhin
 */
public class PostfixExpressions 
{
    //evaluate postfix expressions
    // TODO: prompt user for file of postfix expressions (one per line)
    // TODO: ask if we should be able validate a file based off of whether or not postfix expressions are correct
    // TODO: use stackbased algorithm to evaluate input expression
    // use 2 scanners: 1 for getting expressions, other for reading them
    static Stack<Double> stack = new Stack<Double>();
    static JFileChooser chooser = new JFileChooser();
    static ArrayList<String> expressionLines;
    
    static File file;
    static Scanner reader;

    static String inputPath;
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        APConsole console = new APConsole();
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Give me an input file with postfix \n"
                                           + " expressions on each line, and I will evaluate each one.");
        
        // Reading and Validating Files
        chooser = chooseFile();
        validateInput();
        
        
        // Reading Input
        expressionLines = new ArrayList<String>();
        //loops through entire file, evaluates each expression
        while(reader.hasNextLine())
        {
            String next = reader.nextLine();
            console.println(next + " ---> " + eval(next));
        }
        
        JOptionPane.showMessageDialog(frame, "Done!");
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
        return stack.peek();
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
    public static JFileChooser chooseFile()
    {
        JOptionPane.showMessageDialog(null,"Give me a text input and and I will\n"
                                    + " determine unique words and their frequencies");
        chooser.showOpenDialog(null);
        return chooser;
    }
    public static void validateInput()
    {
        // loops until the input file is chosen
        while(true)
        {
            try
            {
                inputPath = chooser.getSelectedFile().getAbsolutePath();
                break;
            }
            catch(NullPointerException e)
            {
                JOptionPane.showMessageDialog(null, "No file chosen. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if a file was chosen
                chooser = chooseFile();
                continue;
            }
        }
        
        inputPath = chooser.getSelectedFile().getAbsolutePath();
        file = new File(inputPath); 
        
        // makes you choose a new file while file not found
        while(true)
        {
            try
            {
                reader = new Scanner(new FileReader(file));
                break;
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "File not found. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if it is a file
                chooser = chooseFile();
                continue;
            }
        }

    }
}
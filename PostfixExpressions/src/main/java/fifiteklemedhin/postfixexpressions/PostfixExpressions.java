/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifiteklemedhin.postfixexpressions;

 
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
    static JButton inputOpen = new JButton();
    static JFileChooser inputChooser = new JFileChooser();
    static JOptionPane notifier = new JOptionPane();
    static ArrayList<String> expressionLines;
    
    static File inputFile;
    static Scanner reader;

    static String inputFileName;
    static String inputPath;
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Give me an input and output file,\n"
                                           + "and I'll convert the input's content to uppercase,\n"
                                           + "then print the results in the output file");
        
        // Reading and Validating Files
        inputChooser = chooseFile(inputOpen, inputChooser, "input");
        inputPath = validateInput();
        
        
        // Reading Input
        expressionLines = new ArrayList<String>();
        while(reader.hasNextLine())
        {
            String next = reader.nextLine();
            System.out.println(next + " ---> " + eval(next));
        }
        
        
    } 
    
    public static double eval(String s)
    {
        Scanner tokens = new Scanner(s);
        while(tokens.hasNext())
        {
            String token = tokens.next();

            if(isNumber(token))
                stack.push(Double.parseDouble(token));
            else 
                stack.push(operate(stack.pop(), stack.pop(), token));  
        }
        return stack.peek();
    }
    public static double operate(double num1, double num2, String s)
    {
        if(s.equals("+"))
            return num2 + num1;
        if(s.equals("/"))
           return num2 / num1;
        if(s.equals("*"))
            return num2 * num1;
        if(s.equals("-"))
            return num2 - num1;
        System.out.println("operate did not work");
        return .098098098908980;
    }
   
    public static boolean isNumber(String s)
    {
        for(int i = 0; i < s.length(); i++)
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.')
                return false;
        return true;
    }
    public static JFileChooser chooseFile(JButton open, JFileChooser chooser, String type)
    {
        JOptionPane.showMessageDialog(inputOpen, "Select your " + type + " file");
        // need starting directory for file chooser "." sets it to the project location 
        inputChooser = new JFileChooser();
        inputChooser.setCurrentDirectory(new java.io.File("./src/main/java/"));
        inputChooser.setDialogTitle("Select " + type + " file");
        
        //setting it so that file chooser will only show directories
        inputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
       
        // need a button component to open it up, even though it doesn'tactually need to be pressed
        inputChooser.showDialog(inputOpen, "Select your " + type + " file");
        
        return inputChooser;
    }
    
    public static String validateInput()
    {
        // loops until the input file is chosen
        while(true)
        {
            try
            {
                inputPath = inputChooser.getSelectedFile().getAbsolutePath();
                break;
            }
            catch(NullPointerException e)
            {
                JOptionPane.showMessageDialog(inputOpen, "No file chosen. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if a file was chosen
                inputChooser = chooseFile(inputOpen, inputChooser, "input");
                continue;
            }
        }
        
        inputPath = inputChooser.getSelectedFile().getAbsolutePath();
        inputFile = new File(inputPath); 
        
        // makes you choose a new file while file not found
        while(true)
        {
            try
            {
                reader = new Scanner(new FileReader(inputFile));
                break;
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(inputOpen, "File not found. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if it is a file
                inputChooser = chooseFile(inputOpen, inputChooser, "input");
                continue;
            }
        }
        
        /*
          path of the file is a string, and therefore immutable, so the method returns a string
          that inputPath can assign itself to in the main method
        */
        return inputPath;
    }
    
    
}

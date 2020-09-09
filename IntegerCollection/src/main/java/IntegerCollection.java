/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fifiteklemedhin
 */
import APClasses.APConsole;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class IntegerCollection 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        APConsole console = new APConsole("Integer Collection");
        Set<Integer> parsedInts = new HashSet<Integer>();
        
        
        // ************************* Input Selector *************************
        JButton inputOpen = new JButton();
        JFileChooser inputChooser = new JFileChooser();
        JOptionPane notifier = new JOptionPane();
        
        File inputFile;
        Scanner reader;
        
        String inputFileName;
        String inputPath;

        JOptionPane.showMessageDialog(inputOpen, "Select your input file");
        // need starting directory for file chooser "." sets it to the project location 
        inputChooser.setCurrentDirectory(new java.io.File("."));
        inputChooser.setDialogTitle("Select Input File");
        
        //setting it so that file chooser will only show directories
        inputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // need a button component to open it up, even though it doesn'tactually need to be pressed
        inputChooser.showDialog(inputOpen, "Select Input File");
        
        // prints out what user clicked on
        inputFileName = inputChooser.getSelectedFile().getName();
        inputPath = inputChooser.getSelectedFile().getAbsolutePath();
        inputFile = new File(inputPath);
        reader = new Scanner(new FileReader(inputFile));
        System.out.println("Input File: " + inputPath);
        
        // ************************* Delimiting String and Filling Set *************************

        while(reader.hasNextLine())
        {
            String currentLine = reader.nextLine();
            String delimitedLine[] = currentLine.split(" ");
            
            for(String s: delimitedLine)
                parsedInts.add(Integer.valueOf(s));
        }
        
        // ************************* Traversing Set *************************
        for(Integer i: parsedInts)
            console.print(i + "\n");
    }
}

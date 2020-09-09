/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uppercase;

/**
 *
 * @author fifiteklemedhin
 */
import APClasses.APConsole;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Uppercase 
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        JButton inputOpen = new JButton();
        JButton outputOpen = new JButton();
        JFileChooser inputChooser = new JFileChooser();
        JFileChooser outputChooser = new JFileChooser();
        JOptionPane notifier = new JOptionPane();
        
        File inputFile;
        File outputFile;
        Scanner reader;
        FileWriter writer;
        
        String inputFileName;
        String inputPath;
        String outputPath; 
        ArrayList<String> outputLines;
        
        // ************************* Input Selector *************************
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
        
        // ************************* Reading Input *****************************
        outputLines = new ArrayList<String>();
        while(reader.hasNextLine())
            outputLines.add(reader.nextLine());
        
        // ************************* Output Selector ***************************
        JOptionPane.showMessageDialog(outputOpen, "Select your output file");
        // need starting directory for file chooser "." sets it to the project location 
        outputChooser.setCurrentDirectory(new java.io.File("."));
        outputChooser.setDialogTitle("Select Output File");
        System.out.println("current directory and dialog");
        
        //setting it so that file chooser will only show directories
        outputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        System.out.println("selection mode");
        
        // need a button component to open it up, even though it doesn'tactually need to be pressed
        outputChooser.showDialog(outputOpen, "Select Output File");
        System.out.println("show");
       
        outputPath = outputChooser.getSelectedFile().getAbsolutePath();
        outputFile = new File(outputPath);
        outputFile.canWrite();
        writer = new FileWriter(outputFile);
        System.out.println("Output File: " + outputPath);
        
        // ************************* Writing Output ****************************
        for(int i = 0; i < outputLines.size(); i++)
        {    System.out.println(outputLines.get(i).toUpperCase());
            writer.write(outputLines.get(i).toUpperCase() + "\n");
        }
        
        writer.close();
        
        

    } 
}

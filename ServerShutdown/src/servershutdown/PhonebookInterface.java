package servershutdown;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fifiteklemedhin
 */
public class PhonebookInterface 
{
    
    public static void main(String[] args)
    {
        new PhonebookServer();
        new PhonebookDaemon();
    }
}

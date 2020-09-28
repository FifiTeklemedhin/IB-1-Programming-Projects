/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

/**
 *
 * @author fifiteklemedhin
 */
public class Transcript 
{
    private String str;
    
    public Transcript()
    {
        str = "";
    }
    public void add(String more)
    {
        str  = str + more + "\n";
    }
    public String toString()
    {
        return str;
    }
}

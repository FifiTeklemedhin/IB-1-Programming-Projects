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
public class MultiClientChatInterface 
{
    public static Transcript transcript = new Transcript();
    
    public static void main(String[] args)
    {
        new ChatRoomServer();
        new MultiClientChatDaemon();
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;
import java.util.*;
import javax.swing.JOptionPane;
import static war.War.addPile;
/**
 *
 * @author mercyougothis
 */
public class WarModel 
{
    private Deck deck;
    private static ArrayList<Card> unplayed1;
    private static ArrayList<Card> unplayed2;
    private static int winningPile1;
    private static int winningPile2;
    private static int warPile;
    private static String roundWinner;
    private static String winner;
    private static String player1;
    private static String player2;
    private static String card1;
    private static String card2;
    private static Card currCard1;
    private static Card currCard2;
    private static boolean gameWon = false;
    
    public WarModel()
    {
        deck = new Deck();
        deck.deal(26);
        
        unplayed1 = toArrayList(deck);
        ArrayList<Card> winningPile1 = new ArrayList();
        
        deck.shuffle();
        deck.deal(26);
        
        unplayed2 = toArrayList(deck);
        winningPile2 = 0;
        
        warPile = 0;
        setPlayers("Player 1", "Player 2");
        gameWon = false;
    }
    public WarModel(String root)
    {
        deck = new Deck(root);
        deck.shuffle();
        deck.deal(26);
        
        unplayed1 = toArrayList(deck);
        ArrayList<Card> winningPile1 = new ArrayList();
        
        deck.shuffle();
        deck.deal(26);
        
        unplayed2 = toArrayList(deck);
        winningPile2 = 0;
        
        warPile = 0;
        setPlayers("Player 1", "Player 2");
        gameWon = false;
    }
    public static void setPlayers(String player1, String player2)
    {
        WarModel.player1 = player1;
        WarModel.player2 = player2;
    }
    public static void play()
    {
        if(!(unplayed1.isEmpty() || unplayed2.isEmpty()))
        {
            int compResult = unplayed1.get(0).compareTo(unplayed2.get(0));
            currCard1 = unplayed1.get(0);
            currCard2 = unplayed2.get(0);
             card1 = unplayed1.get(0) + "";
             card2 = unplayed2.get(0) + "";
            if(compResult < 0)
            {
               winningPile2 += warPile;
                roundWinner =  player2 + "";
                warPile = 0;
            }
            
            else if(compResult > 0)
            {
                winningPile1 += warPile;
                roundWinner = player1 + "";
                warPile = 0;
            }
            
            if(compResult == 0)
            { 
                warPile += 2;
                roundWinner = "WAR";
                JOptionPane.showMessageDialog(null, "PREPARE FOR WAR!!");
            }
            unplayed1.remove(0);
            unplayed2.remove(0);
        }
        else
        {    
            gameWon = true;
          if(winningPile1  > winningPile2)
            {
                winner = "*************\nWinner: User 1!\n************";
            }

            winner = "************\nWinner: User 2!\n**************";
            
        }
    }
    
    public static String card1()
    {
        return card1;
    }
    public static String card2()
    {
        return card2;
    }
    public static boolean gameWon()
    {
        return gameWon;
    }
    public static String getRoundWinner()
    {
        return roundWinner;
    }
    public static String getWinner()
    {
        return winner;
    }
    public static void addPile(ArrayList urPile, ArrayList warPile)
    {
        for(int j = 0; j < warPile.size(); j++)
        {
            if(warPile.get(j) != null)
                urPile.add(urPile.size(), warPile.get(j));
        }
        warPile = new ArrayList();
    }
    public static ArrayList toArrayList(Deck deck)
    {
        Card dealer[] = deck.deal(26);
        
        ArrayList<Card> cards = new ArrayList();
        
        for(int j = 0; j < deck.deal(26).length; j++)
        {
            cards.add(cards.size(), dealer[j]);
        }
            
        return cards;
    }
    public Card getCard1()
    {
        return currCard1;
    }
    public Card getCard2()
    {
        return currCard2;
    }
}

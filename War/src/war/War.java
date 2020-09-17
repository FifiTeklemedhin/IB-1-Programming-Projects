/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;
import java.util.*;
import APClasses.APConsole;
import javax.swing.JOptionPane;
/**
 *
 * @author mercyougothis
 */
public class War 
{

    public static void main(String[] args) 
    {
        APConsole console = new APConsole();
        Deck deck = new Deck();
        
        deck.shuffle();
        deck.deal(26);
        ArrayList<Card> unplayed1 = toArrayList(deck);
        ArrayList<Card> winningPile1 = new ArrayList();
        deck.shuffle();
        deck.deal(26);
        ArrayList<Card> unplayed2 = toArrayList(deck);
        ArrayList<Card> winningPile2 = new ArrayList();
        ArrayList<Card> warPile = new ArrayList();
        int count = 0;
        
        String lbr = "---------------------------------------------------------------";
        String war = "********************************** WAR *********************************";
        console.println(lbr);
        JOptionPane.showMessageDialog(null, "There are two players in the game of War. During the course of a game,\n"
                + "each player will have three piles of cards, named an unplayed pile,\n"
                + "a war pile, and a winnings pile, respectively.\n"
                + "\nThe game moves forward as cards move from the unplayed piles to the war\n"
                + "piles and then to the winnings piles.The game ends when a player’s unplayed\n"
                + "pile has no more cards.");
        
        while(!(unplayed1.isEmpty() || unplayed2.isEmpty()))
        {
            
            int compResult = unplayed1.get(0).compareTo(unplayed2.get(0));
            console.println("User 1 plays: " + unplayed1.get(0) + "\nUser 2 plays: " + unplayed2.get(0));
            if(compResult < 0)
            {
                addPile(winningPile2, warPile);
                console.println("----> User 2 wins!");
                console.println(lbr);
            }
            
            if(compResult > 0)
            {
                addPile(winningPile1, warPile);
                console.println("----> User 1 wins!");
                console.println(lbr);
            }
            
            if(compResult == 0)
            { 
                warPile.add(count, unplayed1.get(count));
                warPile.add(count, unplayed2.get(count));
                console.println(war);
             }
            unplayed1.remove(0);
            unplayed2.remove(0);
        }
        if(winningPile1.size() > winningPile2.size())
        {
            console.println("\n\n*************************\nWinner: User 1!\n*************************");
            return;
        }
        console.println("\n\n*************************\nWinner: User 2!\n*************************");
    }
    public static void addPile(ArrayList urPile, ArrayList warPile)
    {
        for(int j = 0; j < warPile.size(); j++)
            urPile.add(urPile.size(), warPile.get(j));
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
}

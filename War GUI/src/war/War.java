/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;
import java.util.*;
/**
 *
 * @author mercyougothis
 */
public class War 
{

    public static void main(String[] args) 
    {
        Deck deck = new Deck();
        
        deck.deal(26);
        ArrayList<Card> unplayed1 = toArrayList(deck);
        ArrayList<Card> winningPile1 = new ArrayList();
        deck.shuffle();
        deck.deal(26);
        ArrayList<Card> unplayed2 = toArrayList(deck);
        ArrayList<Card> winningPile2 = new ArrayList();
        ArrayList<Card> warPile = new ArrayList();
        int count = 0;
        
        while(!(unplayed1.isEmpty() || unplayed2.isEmpty()))
        {
            System.out.print("--------------------------\nWAR!\n--------------------------\n");
            int compResult = unplayed1.get(0).compareTo(unplayed2.get(0));
            System.out.println("User 1 plays: " + unplayed1.get(0) + "\nUser 2 plays: " + unplayed2.get(0));
            if(compResult < 0)
            {
                addPile(winningPile2, warPile);
                System.out.println("User 2 wins!");
            }
            
            if(compResult > 0)
            {
                addPile(winningPile1, warPile);
                System.out.println("User 1 wins!");
            }
            
            if(compResult == 0)
            { 
                warPile.add(count, unplayed1.get(count));
                warPile.add(count, unplayed2.get(count));
                System.out.println("Tie");
             }
            unplayed1.remove(0);
            unplayed2.remove(0);
        }
        if(winningPile1.size() > winningPile2.size())
        {
            System.out.println("*************************\nWinner: User 1!\n*************************");
            return;
        }
        System.out.println("*************************\nWinner: User 2!\n*************************");
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

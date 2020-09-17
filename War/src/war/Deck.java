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
public class Deck 
{
    public static final int MAX_SIZE= 52;
    private ArrayList<Card> cards = new ArrayList<Card>(52);
    private String root;
    public Deck()
    {
        reset();
    }
    public Deck(String root)
    {
        this();
        this.root = root;
    }
    public void reset()
    {
        // create new list and add 13 cards from each suit
        cards = new ArrayList<Card>();
        addSuit(Suit.spade);
        addSuit(Suit.heart);
        addSuit(Suit.diamond);
        addSuit(Suit.club);  
    
    }
    //helper method to add 13 cards from a single suit
    public void addSuit(Suit suit)
    {
        for(int j = 2; j <= 14; j++)
        {
            String fLetter = Character.toString(suit.getName().charAt(0));
            String letter = "\\" + j + fLetter.toUpperCase() + ".png";
            cards.add(new Card(suit, j, getRoot() + letter));
            cards.get(cards.size()-1).setFullPath(getRoot() + letter);
        }
    }
    
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }   
    public int size()
    {
        return cards.size();
    }
    public Card deal()
    {
        if(isEmpty())
            return null;
        
        else
            return cards.remove(cards.size() - 1);
    }
    public Card[] deal(int number)
    {
        reset();
        shuffle();
        if(number > cards.size())
            return null;
        else
        {
            
            Card[] hand = new Card[number];
            for(int i = 0; i < hand.length; i ++)
                hand[i] = deal();
            return hand;
        }
    }
    public void shuffle()
    {
        if(cards.size() < MAX_SIZE)
            return;
        Random gen = new Random();
        //Remove cards from the list and place at randm positions in array
        Card[] array = new Card[MAX_SIZE];
        while(cards.size() > 0)
        {
            Card card = cards.remove(cards.size() - 1);
            int i = gen.nextInt(MAX_SIZE);
            while(array[i] != null)
                i = gen.nextInt(MAX_SIZE);
            array[i] = card;
        }
        //Transfer the shuffled cards back to the list
        for(Card card : array)
            cards.add(card);
    }
    public String toString()
    {
        String result = "";
        for(Card card : cards)
            result += card + "\n";
        return result;
    }

   public String getRoot()
   {
       return root;
   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import javax.swing.*;

/**
 *
 * @author mercyougothis
 */
public class Card implements Comparable
{
    private Suit suit;
    private int rank;
    private boolean faceUp;
    private ImageIcon image;
    private String path;
    private String fullPath;
    
    public Card(Suit suit, int rank)
    {
        this.suit = suit;
        this.rank = rank;
        faceUp = false;
    }
    public Card(Suit suit, int rank, String path)
    {
        this(suit, rank);
        image = new ImageIcon(path);
    }
    public Card(Suit suit, int rank, ImageIcon image)
    {
        this(suit, rank);
        this.image = image;
    }
    public ImageIcon getImage()
    {
        return image;
    }
    public String getPath()
    {
        return path;
    }
    public void setFullPath(String str)
    {
        fullPath = str;
    }
    public String getFullPath()
    {
        return fullPath;
    }
    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        else if(!(other instanceof Card))
            return false;
        else
        {
            Card otherCard = (Card) other;
            return rank == otherCard.rank;
        }
    }
    
    @Override
    public int compareTo(Object other) 
    {
        if(!(other instanceof Card))
            throw new IllegalArgumentException("Paramter must be a Card");
        Card otherCard = (Card) other;
        return rank - otherCard.rank;
    }
    
    public int getRank()
    {
        return rank;
    }
    public Suit getSuit()
    {
        return suit;
    }
    public boolean isFaceUp()
    {
        return faceUp;
    }
    public boolean isRed()
    {
        return suit == Suit.heart || suit == Suit.diamond;
    }
    public void turn()
    {
        faceUp =! faceUp;
    }
    public String toString()
    {
        return rankToString() + " of " + suit; 
    }
    private String rankToString()
    {
        if(rank == 14)
            return "Ace";
        else if(rank == 11)
            return "Jack";
        else if(rank == 12)
            return "Queen";
        else if(rank == 13)
            return "King";
        else
            return "" + rank;
    }
}

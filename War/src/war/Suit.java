/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

/**
 *
 * @author mercyougothis
 */
public class Suit implements Comparable
{
    static public final Suit spade = new Suit(4, "spades");
    static public final Suit heart = new Suit(4, "hearts");
    static public final Suit diamond = new Suit(4, "diamonds");
    static public final Suit club = new Suit(4, "clubs");
    
    private int order;
    private String name;
    
    private Suit(int ord, String nm)
    {
        name = nm;
        order = ord;
    }
    @Override
    public int compareTo(Object other) 
    {
        if(!(other instanceof Suit))
            throw new UnsupportedOperationException("Parameter must be s Suit");
        Suit otherSuit = (Suit)other;
        return order - otherSuit.order;
    }
    public String getName()
    {
        return name;
    }
    public String toString()
    {
        return name;
    }
}

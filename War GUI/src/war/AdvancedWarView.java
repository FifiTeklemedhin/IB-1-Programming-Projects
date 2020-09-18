/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mercyougothis
 */
public class AdvancedWarView extends JFrame
{
    private WarModel model;
    private int countRound = 0;
    private JButton playButton = new JButton("play!");
    private JButton resetButton = new JButton("reset");
    private JLabel p1Label = new JLabel("Player 1");
    private JLabel p2Label = new JLabel("Player 2");
    private JLabel winnerLabel = new JLabel("  Winner: ");
    
    private JTextField p1Field = new JTextField(" Put a name here ");
    private JTextField p2Field = new JTextField(" Put a name here ");
    private JTextField winnerField = new JTextField("                              ");
    private ImagePanel card1 = new ImagePanel("");
    private ImagePanel card2= new ImagePanel("");
    
    public AdvancedWarView(WarModel m)
    {
        model = m;
        
        p1Field.setEditable(true);
        p2Field.setEditable(true);
        winnerField.setEditable(false);
        
        card1.setBackground(Color.white);
        card2.setBackground(Color.black);
        p1Field.setBackground(Color.white);
        p2Field.setBackground(Color.white);
        winnerField.setBackground(Color.white);
        
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();
        
        Container container = this.getContentPane();
        container.add(northPanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        container.add(southPanel, BorderLayout.SOUTH);
        
        northPanel.add(p1Label);
        northPanel.add(p1Field);
        northPanel.add(p2Label);
        northPanel.add(p2Field);
        
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.add(card1);
        centerPanel.add(card2);
        
        southPanel.add(playButton);
        southPanel.add(resetButton);
        southPanel.add(winnerLabel);
        southPanel.add(winnerField);
        
        playButton.addMouseListener(new PlayListener());
        resetButton.addMouseListener(new ResetListener());
        
        card1.setSize(this.getWidth()/3,this.getHeight()/3);
        card2.setSize(this.getWidth()/3,this.getHeight()/3);
        
        setTitle("War GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public static void main(String args[])
   {
       WarModel model = new WarModel("cards");
       AdvancedWarView view = new AdvancedWarView(model);
       JOptionPane.showMessageDialog(null, "There are two players in the game of War. During the course of a game,\n"
                + "each player will have three piles of cards, named an unplayed pile,\n"
                + "a war pile, and a winnings pile, respectively.\n"
                + "\nThe game moves forward as cards move from the unplayed piles to the war\n"
                + "piles and then to the winnings piles.The game ends when a player’s unplayed\n"
                + "pile has no more cards.");
   }
    private class PlayListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent me) 
        {
            model.setPlayers(p1Field.getText(), p2Field.getText());
            model.play();
            
            Card u1 = model.getCard1();
            Card u2 = model.getCard2();
            
            if(model.gameWon())
            {
                card1.setImage(new ImageIcon(u1.getPath()));
                card2.setImage(new ImageIcon(u2.getPath()));
                
                JOptionPane.showMessageDialog(AdvancedWarView.this, model.getWinner());
                return;
            }
            card1.setImage(new ImageIcon(u1.getPath()));
            card2.setImage(new ImageIcon(u2.getPath()));
            winnerField.setText(model.getRoundWinner());
            //JOptionPane.showMessageDialog(AdvancedWarView.this, model.getRoundWinner());
                
        }

        @Override
        public void mousePressed(MouseEvent me) {}

        @Override
        public void mouseReleased(MouseEvent me) {}

        @Override
        public void mouseEntered(MouseEvent me) {}

        @Override
        public void mouseExited(MouseEvent me) {}
        
    }
    private class ResetListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent me) 
        {
            model = new WarModel("cards");
            countRound = 26;
            p1Field = new JTextField(" Put a name here ");
            p2Field = new JTextField(" Put a name here ");
            winnerField = new JTextField("                     ");
            card1.setImage(new ImageIcon(""));
            card2.setImage(new ImageIcon(""));
        }

        @Override
        public void mousePressed(MouseEvent me) {}

        @Override
        public void mouseReleased(MouseEvent me) {}

        @Override
        public void mouseEntered(MouseEvent me) {}

        @Override
        public void mouseExited(MouseEvent me) {}
        
    }
}


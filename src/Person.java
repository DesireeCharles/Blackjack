// player and dealer needs set of cards, way to print their cards to the console

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public abstract class Person {

    private Hand hand;
    private String name;

    public Person(){
        //Give them a Hand and a name
        this.hand = new Hand();
        this.name = "";
    }

    public Hand getHand(){
        return this.hand;
    }
    public void setHand(Hand hand){
        this.hand = hand;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void printHand(JLabel[] cardPics){
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());


        //iterate through each card, update pic, hide remaining
        for(int i = 0; i < 11; i++){
            cardPics[i].setVisible(false);
        }
        for(int i = 0; i < this.hand.getHandSize(); i++){
            String rank = this.hand.getCard(i).getRank().toString();
            String suit = this.hand.getCard(i).getSuit().toString();
            String filename = rank + suit + ".png";
            cardPics[i].setIcon(new ImageIcon(new ImageIcon(Main.IMAGE_PATH+filename).getImage().getScaledInstance(Game.CardWidth, Game.CardHeight, Image.SCALE_DEFAULT)));
            cardPics[i].setVisible(true);
        }

    }

    public void hit(Deck deck, Deck discard){

        //If there's no cards left in the deck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
    }
   
    public void seeRules(){
        JFrame rulesGUI = new JFrame();// creating instance of JFrame

        // Title text area
        JTextArea title = new JTextArea("Blackjack Rules");
        title.setFont(new FontUIResource("Arial", Font.BOLD, 30));
        title.setBackground(new java.awt.Color(92, 153, 8));
        title.setEditable(false);
        title.setBounds(50, 25, 300, 40);

        // List of rules
        JLabel rules = new JLabel("<html><ul>" +
                "<li>Play against the Dealer.</li>" +
                "<li>Each player gets 2 cards.</li>" +
                "<li>All picture cards are worth 10.</li>" +
                "<li>Ace is worth 11 OR 1.</li>" +
                "<li>21 points is Blackjack.</li>" +
                "<li>Go over 21 and you're Bust, you lose.</li>" +
                "<li>If you get 5 cards and are not bust, thats a 5 card trick, you win!</li>" +
                "<li>Press 'Hit' to draw a card.</li>" +
                "<li>Press 'Stand' to pass your turn.</li>" +
                "<li>The Dealer will then finish his hand.</li>" +
                "<li>Higher score wins!</li>" +
                "</ul><html>");

        rules.setFont(new FontUIResource("Arial", Font.PLAIN, 15));
        rules.setBackground(new java.awt.Color(92, 153, 8));
        rules.setBounds(20, 90, 340, 300);

        // "Close" button
        JButton exit = new JButton("Close");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rulesGUI.dispose();
            }

        });
        exit.setBounds(165, 410, 70, 35);
        // Add all to GUI
        rulesGUI.add(title);
        rulesGUI.add(rules);
        rulesGUI.add(exit);
        rulesGUI.getContentPane().setBackground(new java.awt.Color(92, 153, 8));
        rulesGUI.setSize(400, 500);// 400 width and 500 height
        rulesGUI.setLayout(null);// using no layout managers
        rulesGUI.setVisible(true);// making the frame visible
    }

    public boolean hasBlackjack(){
        if(this.getHand().calculatedValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }

    public void exit() {
        System.exit(0);
    }
}






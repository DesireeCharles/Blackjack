// Start new rounds, keep track of the score

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Game extends JPanel {

    //Declare variables needed for Game class
    private Deck deck;
    private Deck discarded;
    private Dealer dealer;
    private Player player;
    private int win;
    private int lose;
    private int push;
    public static final int CardWidth = 100;
    public static final int CardHeight = 145;
    JButton btnHit, btnStand, btnNext, btnSeeRules, btnClose;
    JLabel lblDealerCards[], lblPlayerCards[], lblMessage, lblPlayerValueHand, lblDeaValueHand, lblScore;

    public Game() {

        //Create a new deck with 52 cards
        deck = new Deck(true);
        discarded = new Deck();
        dealer = new Dealer();
        player = new Player();
        deck.shuffle();
        setupGUI();
        startRound();
    }
    private void setupGUI(){
        //Size of JPanel
        this.setSize(800, 500);
        //Make Buttons
        btnHit = new JButton("Hit");
        btnHit.setBounds(10, 10, 100, 20);
        btnStand = new JButton("Stand");
        btnStand.setBounds(110, 10, 100, 20);
        btnNext = new JButton("Next Round");
        btnNext.setBounds(210, 10, 110, 20);
        btnSeeRules = new JButton("See Rules");
        btnSeeRules.setBounds(210, 10, 100, 20);
        btnClose = new JButton("Close");
        btnClose.setBounds(310, 10, 100, 20);

        //When someone clicks see rules
        btnSeeRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.seeRules();
            }
        });

        //When someone clicks close
        btnClose.addActionListener(new ActionListener() {
            JButton exit = new JButton("Close");

            @Override
            public void actionPerformed(ActionEvent e) {
                player.exit();


            }
        });
        //When someone clicks the Hit button
        btnHit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                player.hit(deck, discarded);
                updateScreen();
                checkBusts();

            }
        });

        //when someone clicks the "Stand" button
        btnStand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealersTurn();
                checkWins();
                updateScreen();
                dealer.printHand(lblDealerCards);

                //make only the next round and close button visible, they cannot hit/stand at this point
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
                btnSeeRules.setVisible(false);
                btnClose.setVisible(true);
            }
        });

        //someone hits the next round button
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset buttons and start next round
                btnNext.setVisible(false);
                btnHit.setVisible(true);
                btnStand.setVisible(true);
                btnSeeRules.setVisible(true);
                btnClose.setVisible(true);
                startRound();
            }
        });

        lblDealerCards = new JLabel[11];
        lblPlayerCards = new JLabel[11];
        int initialCardX = 10, initialCardY = 150;

        //for each card that can possibly be displayed in the array
        for (int i = 0; i < lblDealerCards.length; i++) {
            lblDealerCards[i] = new JLabel(new ImageIcon(new ImageIcon(Main.IMAGE_PATH+"CardDown.png").getImage().getScaledInstance(CardWidth, CardHeight, Image.SCALE_DEFAULT)));
            lblPlayerCards[i] = new JLabel(new ImageIcon(new ImageIcon(Main.IMAGE_PATH+"CardDown.png").getImage().getScaledInstance(CardWidth, CardHeight, Image.SCALE_DEFAULT)));
            lblDealerCards[i].setBounds(initialCardX, initialCardY, CardWidth, CardHeight);
            lblPlayerCards[i].setBounds(initialCardX, initialCardY+250, CardWidth, CardHeight);
            this.add(lblDealerCards[i]);
            this.add(lblPlayerCards[i]);
            initialCardX +=  50;
            initialCardY -= 18;
        }

        //make scoreboard
        lblScore = new JLabel("[Wins: 0]   [Losses: 0]   [Pushes: 0]");
        lblScore.setBounds(450,10, 300, 50);
        this.add(lblScore);

        //message board
        lblMessage = new JLabel("Starting round! Hit or Stand?");
        lblMessage.setBounds(100, 50, 600, 40);
        lblMessage.setFont(new Font("Arial", 1, 20));
        this.add(lblMessage);

        //hand values on display
        lblDeaValueHand = new JLabel("Dealer's Hand Value:");
        lblPlayerValueHand = new JLabel("Player's Hand Value:");
        lblDeaValueHand.setBounds(20, 280, 300, 50);
        lblPlayerValueHand.setBounds(20, 530, 300, 50);
        this.add(lblDeaValueHand);
        this.add(lblPlayerValueHand);

        //show/add buttons
        this.add(btnHit);
        this.add(btnStand);
        this.add(btnNext);
        this.add(btnSeeRules);
        this.add(btnClose);
        this.setLayout(null);

        //give color to labels
        lblMessage.setForeground(Color.WHITE);
        lblDeaValueHand.setForeground(Color.WHITE);
        lblPlayerValueHand.setForeground(Color.WHITE);
        lblScore.setForeground(Color.WHITE);

        //make next round button hidden to start
        btnNext.setVisible(false);

    }

    private void updateScreen(){

        lblPlayerValueHand.setText("Player's Hand Value: " + player.getHand().calculatedValue());
        lblDeaValueHand.setText("Dealer's Hand Value: " + dealer.getHand().calculatedValue());

        player.printHand(lblPlayerCards);
        //score
        lblScore.setText("[Wins: " + win + "]   [Losses: " + lose + "]   [Pushes: "+push+"]");

    }
    private void startRound() {
        lblMessage.setText("Welcome to Blackjack! Would you like to Hit or Stand?");

        if (win > 0 || lose > 0 || push > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + win + " Losses: " + lose + " Pushes: " + push);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        //checking cards
        if (deck.cardsLeft() < 4) {
            //reload the deck from discard pile if we're out of cards
            deck.reloadDeckFromDiscard(discarded);
        }

        //Give cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);
        updateScreen();

        //print the dealer's hand and hide second one
        dealer.printHand(lblDealerCards);
        lblDealerCards[1].setIcon(new ImageIcon(new ImageIcon("C:/Users/desir/development/BlackJack/src/cardsImages/CardDown.png").getImage()
                .getScaledInstance(CardWidth,CardHeight, Image.SCALE_DEFAULT)));
        lblDeaValueHand.setText("Dealer's hand value: " + dealer.getHand().getCard(0).getValue() + " + ?");

        //Check if dealer has BlackJack to start
        if (dealer.hasBlackjack()) {
            //Show the dealer has BlackJack
            dealer.printHand(lblDealerCards);
            //Check if the player also has BlackJack
            if (player.hasBlackjack()) {
                //End the round with a push
                lblMessage.setText("Both 21 - Push");
                push++;
                //start a new round
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
                btnSeeRules.setVisible(false);
                btnClose.setVisible(true);
            } else {
                lblMessage.setText("Dealer has Blackjack!");
                dealer.printHand(lblDealerCards);
                lose++;
                //player lost, start a new round
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
                btnSeeRules.setVisible(false);
                btnClose.setVisible(true);
            }
        }

        //Check if player has blackjack to start
            if (player.hasBlackjack()) {
            lblMessage.setText("You have Blackjack!");
            win++;
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);
            btnSeeRules.setVisible(false);
            btnClose.setVisible(true);
        }

    }
    private void checkBusts(){
        //Check if they busted
        if (player.getHand().calculatedValue() > 21) {
            lblMessage.setText("You BUST - Over 21");
            lose++;
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);
            btnSeeRules.setVisible(false);
            btnClose.setVisible(true);
        }
    }

    private void checkWins(){

        updateScreen();

        //Check who wins and count wins or losses
        if (dealer.getHand().calculatedValue() > 21) {
            lblMessage.setText("Dealer Busts! You win!");
            win++;
        } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
            lblMessage.setText("Dealer wins - Higher hand");
            lose++;
        } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
            lblMessage.setText("You win - Higher hand");
            win++;
        } else {
            lblMessage.setText("Equal Value Hands - Push");
            push++;
        }
    }

    private void dealersTurn(){
        while (dealer.getHand().calculatedValue() < 17) {
            dealer.hit(deck, discarded);
            updateScreen();
        }
    }

    //color background
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#5c9a08"));
        g.fillRect(0,0,1000,1000);
    }


}
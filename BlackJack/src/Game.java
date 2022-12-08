// Start new rounds, keep track of the score

public class Game {

    // Variables used in Game class
    private int win;
    private int lose;
    private int push;
    private Deck deck;
    private Deck discarded;
    private Dealer dealer;
    private Player player;

    // Constructor
    public Game() {
        //Score = 0 (start)
//    win = 0;
//    lose = 0;
//    push = 0;
        // Create a new deck (52 cards)
        deck = new Deck(true);

        //Empty deck
        discarded = new Deck();

        //people in the game
        dealer = new Dealer();
        player = new Player();

        // first rounds
        deck.shuffle();
        startRound();
    }

    // logic for each round
    private void startRound() {
        
        if (win>0 || lose>0 || push>0){
            System.out.println();
            System.out.println("Starting next round");
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }
            if (deck.cardsLeft() < 4){
                deck.reloadDeckFromDiscard(discarded);
            }
        // dealer 2 cards
        // player 2 cards
        // print hand
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);
        dealer.printFirstHand();
        player.printHand();

        


        //check if dealer has blackjack to start
        if (dealer.hasBlackjack()) {
            dealer.printHand();


            //check if player has blackjack
            if (player.hasBlackjack()) {
                System.out.println("You both have 21 -push.");
                push++;
                startRound();
            }
            else {
                System.out.println("Dealer has Blackjack - you lose :'(");
                dealer.printHand();
                lose++;
                startRound();

            }
        }
        if (player.hasBlackljack()) {
            System.out.println("You have Blackjack - you win :D");
            win++;
            startRound();
        }

        player.makeDecision(deck, discarded);
        
        if (player.getHand().calculatedValue()>21){
            System.out.println("You have gone over 21");
            lose++;
            startRound();
        }
        dealer.printHand();
        while (dealer.getHand().calculatedValue()<17){
            dealer.hit(deck, discarded);
        }
        
        //check who win and lose
        if(dealer.getHand().calculatedValue()>21){
            System.out.println("Dealer buts");
            win++;
            
        } else if (dealer.getHand().calculatedValue()> player.getHand().calculatedValue()) {
            System.out.println("You lose :'(");
            lose++;

        } else if (player.getHand().calculatedValue()> dealer.getHand().calculatedValue()) {
            System.out.println("You win :D");
            win++;

        } else {
            System.out.println("Push");
        }
        startRound();
    }

}

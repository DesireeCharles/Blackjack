//here will be the objects that handle the dealer's actions and the player's choices

import  java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }

    public void takeCardFromDeck(Deck deck){
        hand.add(deck.takeCard());
    }
    //print dealer or player hand
    public String toString(){
        String output = "";
        for(Card card: hand){
            output += card + " - ";
        }
        return output;
    }

    // calculate how much the hand is
    //var for ace count and value
    //for each card in this hand ass rhe card value to the hand
    //count how many aces have been added
    public int calculatedValue(){
        int value = 0;
        int aceCount = 0;

        for (Card card: hand) {
            value+= card.getValue();
            if (card.getValue() ==11){
                aceCount++;
            }
        }
        // turning ace value into 1 (look at rules)
        if (value > 21 && aceCount>0){
            while (aceCount>0 && value>21){
                aceCount --;
                value -=10;
            }
        }
        return value;
    }

    public Card getCard(int idx){
        return hand.get(idx);
    }

    public void discardHandToDeck(Deck discardDeck){
        discardDeck.addCards(hand);
        hand.clear();
    }
}

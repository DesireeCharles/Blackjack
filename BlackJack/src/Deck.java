// shuffle the deck


import java.util.ArrayList;

public class Deck {
    // need an array for the cards
    private ArrayList<Card> deck;

public Deck(){
    //empty for now , change later!!
    deck = new ArrayList<Card>();
}

    public Deck (boolean makeDeck) {
        deck = new ArrayList<Card>();
        if(makeDeck){
            //all suits (go through)
            for (Suit suit : Suit.values()) {
                // all ranks now
                for (Rank rank: Rank.values()) {
                    //add a new card containing each iterations suit/rank
                    deck.add(new Card(suit, rank));
                }
            }
        }

    }

    public ArrayList<Card> getCards(){
    return deck;
    }
//add cards to the deck
public void addCard(Card card){

    deck.add(card);
}
    // take first card, remove and return it
    public Card takeCard(){
        Card cardToTake = new Card(deck.get(0));
        deck.remove(0);
        return cardToTake;
    }

// A string to hold everything that it's going to return
public String toString() {
    String output = "";

//for each Card "card" in the deck
for (Card card: deck){
//add card
    output += card;
    output += "\n";
}
    return output;
}




//need something to shuffle the cards
public void shuffle(){
    ArrayList<Card> shuffled = new ArrayList<Card>();
    while (deck.size()>0){
       //random index to pull and add this random card to the new deck
        //then remove it from the deck bc of repeated cards
       int cardToPull = (int)(Math.random()*(deck.size()-1));
       shuffled.add(deck.get(cardToPull));
       deck.remove(cardToPull);
    }
    deck = shuffled;

}
// check how many cards is in the Deck

    public boolean hasCards(){
        if (deck.size()>0){
            return true;
        } else {
            return false;
        }
     }

     public void emptyDeck(){
        deck.clear();
     }

     public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
     }

     //if deck empty , add all cards and replace shuffled
    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("New deck");
    }
    public int cardsLeft(){
    return deck.size();
    }

}




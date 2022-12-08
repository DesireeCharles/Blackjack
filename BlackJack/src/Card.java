//Cards (1 to 10), Jack, Queen, King, Ace , Values (1 to 10/11)

public class Card {
    private Suit suit;
    private Rank rank;


 // create a card given the suit and rank
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Card card){
        this.suit= card.getSuit();
        this.rank= card.getRank();
    }
    // need a way for the cards get the values - getter/to String

    public int getValue(){
        return rank.rankValue;
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    public String toString(){
        return (rank+ " of " + suit + " -> " + this.getValue());

    }


}

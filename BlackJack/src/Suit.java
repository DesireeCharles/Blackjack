// enum - set of related constants
// A deck is made of cards and the cards need a rank and a suit
// Contains the types/names of the cards

public enum Suit {
    CLUB ("Clubs"),
    DIAMOND("Diamonds"),
    HEART ("Hearts"),
    SPADE ("Spades");
    String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }

    public String toString(){
        return suitName;
    }
}

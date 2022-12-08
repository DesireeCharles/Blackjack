// player and dealer needs set of cards, way to print their cards to the console

public abstract class Person {

    private Hand hand;
    private String name;

    public Person() {
        this.hand = new Hand();
        this.name = "";
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHand() {
        System.out.println(this.name + "'s hand: ");
        System.out.println(this.hand + " value:  " + this.hand.calculatedValue());

    }

    public boolean hasBlackjack() {
        if (this.getHand().calculatedValue() == 21) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasBlackljack() {
        if (this.getHand().calculatedValue() == 21) {
            return true;
        } else {
            return false;
        }
    }

    public void hit(Deck deck, Deck discard){
         //no cards in the deck
        if(!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();
    }
}






// I am using Enum bc its unchangeable - cannot be overridden
// A deck is made of cards and the cards need a rank and a suit
//Value of the cards
//Confirm how much ACE is worth!! 1,10,11?? both? :'(

import javax.swing.plaf.FontUIResource;
//change ace value
public enum Rank {
    ACE ("Ace",11),
    TWO ("Two",2),
    THREE("Three",3),
    FOUR ("Four",4),
    FIVE ("Five",5),
    SIX ("Six",6),
    SEVEN ("Seven",7),
    EIGHT ("Eight",8),
    NINE ("Nine",9),
    TEN ("Ten",10),
    JACK ("Jack",10),
    QUEEN ("Queen",10),
    KING ("King",10);

    String rankName;
    int rankValue;

    Rank (String rankName, int rankValue){
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    public String toString(){
        return rankName;
    }
}

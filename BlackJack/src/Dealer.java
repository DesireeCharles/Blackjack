//Draw cards to 16 and stand on 17 or above

public class Dealer extends  Person {

    public Dealer(){
        super.setName("Dealer");
    }

    //Dealer hand
    public void printFirstHand(){
        System.out.println("Dealer's hand:");
        System.out.println(super.getHand().getCard(0));
        System.out.println("Second card is facing down.");
    }


}

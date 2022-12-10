//Start the game

import javax.swing.*;

public class Main {
    static final String IMAGE_PATH="C:/Users/desir/development/BlackJack/src/cardsImages/";

    public static void main(String[] args) {

        //Create the Window - give it title, set size, set close operation (exit on close)
        JFrame frame = new JFrame("Blackjack - the game");
        frame.setSize(750,650);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //start a new  game
        Game blackjack = new Game();

        //Add game to window gui
        frame.add(blackjack);

        //visiability
        frame.setVisible(true);

    }
}
# Blackjack
LM173 - Game Blackjack for CS4431

Group names: Desirèe Charles, Eanna Bonner, John Foley, Pardis Norouzi and Tamara Orosz.


We have decided to create Blackjack on a console version using Java. In this project I used the fundamental Java concepts including Array lists, basic algorithms, inheritance and etc. My first step was to understand the rules of the game, then I made a diagram using Visual Paradigm.


Rules:
Deck of 52 cards, with one player at time, starting with 2 cards (player and dealer). Jack, Queen, and King are valued at 10, while aces are either valued at 1 or 11 depending on the situation (if the value of the hand with an ace pushes it beyond 21, the ace is valued at 1, otherwise, it's 11). Number cards are valued at their corresponding number. If a player starts with 21, they are given Blackjack and win right away. In the event that they both score 21, it is a draw. The player is always free to stand. If they get more than 21, they lose and bust. When a player stands, the dealer starts drawing their third, fourth cards and so on, ending the player's turn. Until the dealer has a hand worth 17 or more, they will continue to draw cards. The player with the highest score wins the round if neither the player nor the dealer busts or receives Blackjack. It shuffles the deck and re-deal the cards if it runs out.


Classes:

Player - Manage player specific actions, such choosing whether to hit or stand. Player extends person and set name for the player.

Main – Begin playing the game. I have created a test class on my code that has nothing to do with the game, I only used it to test the code while I was coding. In the main class I created a window that has the title  “Blackjack - the game”, created a game object, added a game window to GUI and made the game visible.

Person- Serve as the Dealer and Player's parent class. They both require playing cards, a name, a method of printing their hands on the console, and other things. Because abstract classes cannot be instantiated directly, I constructed a person class as an abstraction. It is abstract because I wont make an individual "Person" objects for the game, instead, I just made "Players" and "Dealers." The two classes share logic through the usage of the "Person" class. That’s why “Dealer” and “Player” extends “Person” (inheritance). Here is where the players hand updates, is also where if player takes a card from the deck Deck, is where we discard the card to the deck Discard, in case it needs to reshuffle this when the game runs out of deck cards. Here I used Eanna’s code for the “See rules” , which I made it visible through the whole game, and the last thing is to check if the person has 21 and the exit code.

Hand- Calculates the total value of the cards in the player's and dealer's hands while showing the cards they are holding. I used an Array list of Cards in the hand class, it includes methods for determining the hand's worth, drawing cards from the deck, returning the hand to the deck, and adding a method to add a card from the deck.

Dealer- to manage actions particular to the dealer, such as drawing cards up to 16 and stopping on 17 or higher. Dealer extends person and set name for the dealer.

Card- A card's representation and management of card operations. Each card requires a rank and a suit. I made two Enums named Suit and Rank since every deck is made up of cards, and every card requires a rank and a suit. Variables were created and then added a constructor that sets the variable in accordance with a Suit and a Rank as its parameters. I had to determine the rank, suit, and value of the cards, so I used getter methods. Made a to string function that returns a representation of the Card as a String that includes its Suit, Rank, and Value. 

Deck- Holding many cards while performing tasks like rearranging the deck. As a result, I began by going to the Deck class and creating an instance variable with the name deck and the data type Array list Card, then I created a Deck constructor (deck is being copied). Added an add method, to string with a for loop that adds each Card to a String that returns after repeating over each Card in the deck, makes a standard deck of cards if true. After the card being added to this deck, that In order to generate a fresh deck of 52 playing cards, I first added a function to the Deck class, then an array list of cards to be added to this deck, added a shuffle method, then I copy and take the first card from the deck, and then I check if the deck still cards left and then return it to the deck, “creating” a new one.

Game- to take care of the majority of the game's logic, including initiating new rounds and calculating  the score, etc. First I declared variables needed for the game class, such as deck (deck and discarded), dealer , player, win, loose and push. Then I set the width and height of the images and declared variable for the GUI elements. Created a new deck with 52 cards, a new empty deck, created the Dealer, Player, and shuffled the deck and start first round. Then I set up a GUI setting the size of JPanel and make buttons for "Hit" ,"Stand" , “See rule”, “Close” and "Next Round" actions. Set up the visibility of the buttons, and set up the card’s location on the screen, the buttons, made a score board, set up the message board and personalised it.

Enums:

We learned in class that Enums is a data type that allows a variable to represent a collection of specified constants, the variable must match one of the previously established values. It also minimizes mistakes brought on by numerical transposition or typing errors, and enables future value changes to be made easily.

Suit- to store  Club, Diamond, Heart, and Spade suits. Each suit only has one value, a String with the name of the suit. For the Suit Enum, I made a constructor that accepts the String value suit name. Further I declared the String suit name and a to string function that yields the suit's name.

Rank- To store the numerical values of the ranks, such as 1-10, Jack, King, Queen, and Ace (1 or  11). I did the same as in the Enums Suit, the only difference is that I attached specific values to them.


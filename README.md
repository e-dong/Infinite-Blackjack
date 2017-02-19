# Infinite-Blackjack
This was done for my Intermediate Java class

Compilation: javac BlackJack.java

Dependencies: Card.java, Player.java

to run: java BlackJack

Rules of the Game:

Rules are the same as traditional BlackJack except there are an infinite number of cards.
The player begins with 100 dollars. He may bet anywhere between 0.01 to 100.00 dollars. If the player beats the dealer, the player wins the amount of he bet. If the player loses a hand, the player will lose that amount of money. 

When the player has no more money, the player loses and the program ends. 

If the player loses the game, the player may start a new game. 

Program Summary:

When the program starts, it prompts the end user for their name. After the user inputs the name, a new text file created by the name they inputed if the file does not exist. Otherwise, then the program will load the information from the existing textfile and the user can play where she/he left off.  The contents of the text file will hold their name (String), amount of money they can bet (double), total hands (int) played, and hands won (int). After each hand is played, the information stated above is written to the text file. 

If the player lost a game and tries to resume the game with the same name, then statistics are reset and the player plays a new game.

String Representation of Cards

First character is the card type
  - 2 - 10 = 2 - 10
  - J = Jack
  - Q = Queen
  - K = King
  - A = Ace
  
Second character is the card's suit
  - c = Clubs
  - s = Spades
  - d = Diamonds
  - h = Hearts

//Assignment 2: Infinite Black Jack
//Author: Eric Dong
//***************PURPOSE OF ASSIGNMENT**********************************//
/*create a blackJack game where an infinte number of cards can be drawn*/
//**********************************************************************//
//need a Card Class: data of the cards that can be drawn and played
//need Player Class: contains info on the player
//need Random Class: generate random numbers so that random cards can be drawn
//Date Created: 2/2/2016
//Last Modification Date: 2/23/2016
import java.util.*; //necessary for Random and Scanner Class
import java.io.*;   //necessary for File and PrintWriter Class
public class BlackJack
{
//Main Method that enables user to play "Black Jack"
	public static void main(String [] args) throws IOException
	{
		//allow User to enter the name
		Scanner console=new Scanner(System.in);
		System.out.println("Enter your name.");
		String name=console.nextLine();
		Player p=new Player();//instance of Player class is created
		p.setName(name);
		double bet=0.00; //the amount of money the user will bet
	  double currentMoney=0.0; //will hold the current Money that user has.
		int numberOfHandsCounter; //running total that keeps track of the number of hands played
		int numberOfHandsWon;//running total that keeps track of the number of hands won

		//if the file exists, then information will be read from the file and the user will resume
		//the game as he left it
		File in =new File(name+".TXT");
		if (in.exists()) { //if it exists read from file and resume save game
			p.resumeSave();
			currentMoney=p.getMoney();
			//if the user lost the game (no more money to bet)
			if(currentMoney==0.00){
				//reset info back to default if user lost the last game.
				System.out.println("Hello "+name+"\nIt appears you have lost last game."
														+"\nStarting new game...");
				p.resumeSave();
				p.setMoney(100.0);
				currentMoney=p.getMoney();
				p.setNumOfHands(0);
				numberOfHandsCounter=0;
				p.setHandsWon(0);
				numberOfHandsWon=0;
				p.saveGame();
				p.displayPlayerInfo();

			}else{//otherwise Resume game just as the player left it.
			System.out.println("Welcome back, " + name + "\nResuming game...");

				numberOfHandsCounter=p.getHands();
				numberOfHandsWon=p.getHandsWon();
				p.displayPlayerInfo();
				currentMoney=p.getMoney();
			}
		}else{//If the file does not exist or the name entered does not exist. Start a New game
			System.out.println("Welcome to a new game of Infinite Blackjack!");
			PrintWriter newPlayer=new PrintWriter(name+".TXT");
			numberOfHandsCounter=0; //running total that keeps track of the number of hands played
			numberOfHandsWon=0;//running total that keeps track of the number of hands won
			p.setMoney(100.00);
			currentMoney=p.getMoney();
			p.saveGame();
			p.displayPlayerInfo();
		}
		//Main loop if player wishes to play a hand.
		//User will enter a "Y" or "y" to play a hand or a
		//"N" or "n" to quit.
		//if the player quits, information will be written to a file so they can resume it next time
		//P.saveGame() are used periodically in case Program crashes
		String playAgain;
		System.out.println("\nDo you wish to play a hand? (Y/N)");
		playAgain=console.nextLine();
		//input Validation, user must enter a Y or N. Program will not accept anything else.
				while(!playAgain.equals("Y")&&(!playAgain.equals("y")&&(!playAgain.equals("N")&&(!playAgain.equals("n"))))){

					System.out.println("Invalid entry."+
												"\nPlease enter a \"Y\" or a \"N\"." );
					playAgain=console.nextLine();
				}
		//Loops while player wants to play a hand and they have money.
	 while((playAgain.equals("Y")||playAgain.equals("y")&&currentMoney>0.00)){
		  ++numberOfHandsCounter; //increment number of hands played
			p.setNumOfHands(numberOfHandsCounter);

		 	System.out.println("Enter an amount to bet (at least $0.01)"
												+"\nand less than or equal to your current money.");

				//value must be a double inorder to continue
				while (!console.hasNextDouble()){
					System.out.println("Invalid Value!");
  				System.out.println("Please enter a amount to bet (atleast $0.01)");
			  	console.next();

				}

			bet=console.nextDouble();
			console.nextLine();
				//Input Validation,the double value entered must between 0.01 and the current money they have.
			while(bet<0.01||bet>currentMoney&&currentMoney!=0.00){
				System.out.printf("Please enter a value between $0.01 and $%.2f.\n",currentMoney);
				//must be a double value
				while (!console.hasNextDouble()){
					System.out.println("Invalid Value!");
  				System.out.println("Please enter a amount to bet (atleast $0.01)");
			  	console.next();

				}
				bet=console.nextDouble();
				console.nextLine();
			}

			//*******************************Player plays first***********************//
			System.out.println("\nPLAYER DEAL.");

			//Random object created,
			Random randCard = new Random();

			int score=0;
			int blackJackScore=0; //add the points of the two cards, if it equals to 21, player will obtain BlackJack Victory
			int numOfAcesCounterP=0; //counts the number of aces the player has drawn.

			//CardIOne and cardITwo will deal with the 13 card types
			int cardIOne=randCard.nextInt(13);
			int cardITwo=randCard.nextInt(13);
			//suitIOne and suitITwo will deal with the possible suit types
			int suitIOne=randCard.nextInt(4);
			int suitITwo=randCard.nextInt(4);

			//inital draw 2 cards
			//future reference perhaps use a Card Array next time.
			Card	cardOne = new Card(cardIOne,suitIOne);
			Card cardTwo = new Card(cardITwo,suitITwo);
			//increment the Ace counter if aces are found
				if (cardIOne==12){
					++numOfAcesCounterP;
				}
				if (cardITwo==12){
					++numOfAcesCounterP;
				}
				cardOne.setCardV(cardIOne);
				cardOne.setSuitV(suitIOne);
				System.out.print(cardOne.toString()+" ");
				cardTwo.setCardV(cardITwo);
				cardTwo.setSuitV(suitITwo);
				System.out.print(cardTwo.toString()+"\n");

				// add the intial number of points without accounting for the aces
				score=cardOne.getCardValue(cardIOne)+cardTwo.getCardValue(cardITwo);
				//accounts the number of aces into the score to obtain the best hand possible without going over 21
				score=getScore(score,numOfAcesCounterP);
				System.out.println("Score: " + score + "\n");

				//store the value of the the sum of the two cards.
				//this variable will be used later on to determine what happens when the user draws a blackjack.
				blackJackScore=cardOne.getCardValue(cardIOne)+cardTwo.getCardValue(cardITwo);



			//hit or stay.
			String hit;
			System.out.println("Do you wish to hit or stay? (H/S)");

			hit=console.nextLine();

			//Input Validation, will only accept "H/h" or "S/s" as outputs
			while (!hit.equals("H")&&(!hit.equals("h")&&(!hit.equals("S")&&(!hit.equals("s"))))){
				System.out.println("Invalid Entry!" +
														" Please Enter a \"H\" or a \"S\".");
				hit=console.nextLine();
			}
			if (hit.equals("H")||hit.equals("h")){
			while (score<=21 && (hit.equals("H")|| hit.equals("h"))){
				//user can escape this loop by entering a "s" or "S" at the end of this loop.
				int cardI=randCard.nextInt(13);
				int suitI=randCard.nextInt(4);
				//new Card object created to deal with extra cards dealt if player chooses to hit.
				Card extraCardHit=new Card(cardI,suitI);

				extraCardHit.setCardV(cardI);
				extraCardHit.setSuitV(suitI);
				System.out.println("\nCard Dealt: "+extraCardHit.toString());


				score+=extraCardHit.getCardValue(cardI);
				score=getScore(score,numOfAcesCounterP);
				System.out.println("Score: "+score);
				if (score>21){
					System.out.println("Bust!");
					System.out.printf("You lost $%.2f\n\n",bet);
					currentMoney=currentMoney-bet;
					p.setMoney(currentMoney);
					//write to file
					p.saveGame();
				}else{
					System.out.println("Do you wish to hit or stay? (H/S)");
					hit=console.nextLine();


					while (!hit.equals("H")&&(!hit.equals("h")&&(!hit.equals("S")&&(!hit.equals("s"))))){
						System.out.println("Invalid Entry!" +
																" Please Enter a \"H\" or a \"S\".");
						hit=console.nextLine();
					}

				}

			}
		}
		//************************DEALER TURN**********************************************************************//
		//very similar to how the user plays
		int numOfAcesCounterD=0; //counts the number of Aces per hand... will reset to zero after the hand is played
		int dScore=0; //counts the number of points of cards dealt
		int cardI;
		int suitI;
		//when the player stays, the dealer will begin.
		if ((hit.equals("S")||hit.equals("s"))&&currentMoney>0.0) {
			System.out.println("\nYou have stayed, Dealers Turn!");
			//As long as the player did not bust(over 21 points), the dealer play.
			if(score<=21&&dScore<=21){
				Card[] dCardArray=new Card[2];
				for(int i=0;i<2;i++){
					cardI=randCard.nextInt(13);
					suitI=randCard.nextInt(4);
					if (cardI==12) {
						++numOfAcesCounterD;
					}
				//set the card values to be used in the toString method
				dCardArray[i]=new Card(cardI,suitI);
				dCardArray[i].setCardV(cardI);
				dCardArray[i].setSuitV(suitI);
					System.out.print(dCardArray[i].toString()+" ");
					dScore+=dCardArray[i].getCardValue(cardI); // add the intial number of points without accounting for the aces
					dScore=getScore(dScore,numOfAcesCounterD); //accounts the number of aces into the score to obtain the best hand possible without going over 21
				}
				System.out.println("Score: " + dScore + "\n");
				//As long as the dealer score is less than 18 he/she will hit.
				while(dScore<18){
					int k=0;
					k++;
					System.out.println("Hit!");

					cardI=randCard.nextInt(13);
					suitI=randCard.nextInt(4);
					if (cardI==12) {
						++numOfAcesCounterD;
					}
					dCardArray[k]=new Card(cardI,suitI);
					dCardArray[k].setCardV(cardI);
					dCardArray[k].setSuitV(suitI);
						System.out.println("card dealt: "+dCardArray[k].toString());
						dScore+=dCardArray[k].getCardValue(cardI);
						dScore=getScore(dScore,numOfAcesCounterD);
						System.out.println("Score: " + dScore + "\n");
				}
				//The house will stay if the total number of points is greater than equal to 18 while being less than 21.
				if (dScore>=18&&dScore<=21){
					System.out.println("Stay!\n");
				}
				//***************************WINNING/LOSING CONDTIONS****************************************//
				//PUSH when both the dealer and player get the same score.
				//Player loses no money
						if(dScore==score){
							System.out.println("Your Score: " + score);
							System.out.println("Dealer Score: " + dScore);
							System.out.println("Push\n"); //aka. a tie.
						}
				//DEALER BUSTED when points are greater than 21
				if (dScore>21&&blackJackScore!=21){
					System.out.println("\nDealer Bust!");
					//you win and get bet x2 back
					p.setMoney((bet)+currentMoney);
					currentMoney=p.getMoney();
					System.out.printf("You have won: $%.2f\n\n",bet);
					++numberOfHandsWon;
					p.setHandsWon(numberOfHandsWon);
					p.saveGame();
				}



				//PLAYER WINS if his score is greater than the dealer without busting.
				if(score>dScore){
					System.out.println("\nYour Score: " + score);
					System.out.println("Dealer Score: " + dScore);
					System.out.printf("You have won: $%.2f\n\n",bet);
					p.setMoney(bet+currentMoney);
					currentMoney=p.getMoney();
					++numberOfHandsWon;
					p.setHandsWon(numberOfHandsWon);
					p.saveGame();
				}
				//Player gains BLACKJACK when the sum of the first 2 cards dealt are equal to 21.
				//The only way for the player to not get black jack is when the dealer also gets 21 points from the first 2 cards.
				//Player wins back 150 percent of bet back.
				if (blackJackScore==21&&blackJackScore!=dScore){
					System.out.println("\nBLACKJACK!");
					p.setMoney((1.5*bet)+currentMoney);
					currentMoney=p.getMoney();
					System.out.printf("You have won: $%.2f\n\n",1.5*bet);
					++numberOfHandsWon;
					p.setHandsWon(numberOfHandsWon);
					p.saveGame();
				}
				//Player loses:
				// when the dealer has more points than the player without busting.
				if(dScore>score&&dScore<=21){
					System.out.println("Your Score: " + score);
					System.out.println("Dealer Score: " + dScore);
					System.out.println("The dealer has won!");
					System.out.printf("You have lost $%.2f\n\n",bet);
					p.setMoney(currentMoney-bet);
					currentMoney=p.getMoney();
					p.saveGame();
				}
			}

		}

		//If the user has greater than $0.00 then he has the option to play another hand.
		if (currentMoney>0.0){
			 p.displayPlayerInfo();
			 System.out.println("\nDo you wish to play another hand (Y/N)");
			 playAgain=console.nextLine();
			 //Input Validation
					while(!playAgain.equals("Y")&&(!playAgain.equals("y")&&(!playAgain.equals("N")&&(!playAgain.equals("n"))))){

						System.out.println("Invalid entry."+
																"\nPlease enter a \"Y\" or a \"N\"." );
						playAgain=console.nextLine();
				}
			}

		}

		//if player selects no...write information to text file.
		if(currentMoney>0.0 && (playAgain.equals("N")||playAgain.equals("n"))){
			System.out.println("Saving Game..." + "\nGoodbye.");
			p.saveGame();

		}
		//The player loses the game when he has no more money left
		//The program terminates.
		if(currentMoney==0){
				System.out.println("You have no more money to bet!");
				System.out.println("Thank you for playing Infinite BlackJack.");

			}
			console.close(); //Scanner no longer needed
		}



//STATIC SCORING method
//algorithim calculates the score of the house and the player
//@param 2 integer
//the number of points and number of Aces are passed in as integers
//@return 1 integer
//final calculated score is returned back to the calling code
	private static int getScore(int points, int numAces) {
	int score = 0;

	// If there are no aces, or if score is less than 21 with aces at
	// 11 points each, then the actual score is just
	// equal to the number of points.

	if (numAces == 0 || points <= 21) {
	    score = points;
	} else {

	    // Otherwise, we need to check what is the BEST score is,
	    // and that gets a little complicated.  We set a placeholder
	    // -1 for best score, and a placeholder potential score.
	    // We will keep track of what the best score is, and try
	    // different potential scores against it.  Whatever is
	    // highest without going over 21 will win as the best score.

	    int bestScore = -1;
	    int potentialScore = points;

	    // Loop through _number of aces_ times.  Each time, try an
	    // increasing number of aces as a 1 value instead of an
	    // 11 value (thus, subtract 10 * j from the total points
	    // value, which assumes all Aces are equal to 11 points).

	    for (int j = 0; j <= numAces; j++) {
				potentialScore = (points - (10 * j));

			// For each iteration, if the potential score is
			// better than the already-best score, but it is NOT
			// over 21 (causing us to bust), then the
			// potential score should count as our new best score.

				if (potentialScore > bestScore && potentialScore <= 21) {
		    	bestScore = potentialScore;
				}
	    }

	    // We could have busted even when all of our aces were set
	    // to one point.  In this case, we might never have gotten a
	    // valid "best" score.  But our best potential score is the closest
	    // to a best score we have, so we will replace our placeholder -1
	    // best with the best potential score we got.

	    // Otherwise, just set the score to the best score.

	    if (bestScore == -1) {
				score = potentialScore;
	    } else {
					score = bestScore;
	    	}
		}
	return score;
  }

}








			//create method for scoring, e.g


		//this method displayers player hands info before playing a hand

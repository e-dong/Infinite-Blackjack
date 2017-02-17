import java.util.*; //import statement required for Scanner
import java.io.*;   //import statement required to use File and PrintWriter Class

public class Player
{
  //************************information about the player********************//

  private String _name;
  private double _money;
  private int _numOfHands;
  private int _handsWon;

  //Constructors
  //initialize the variables above
  public Player(){
    _name="";
    _money=0.0;
    _numOfHands=0;
    _handsWon=0;
  }

  //make accessors methods
  //return player name
  public String getName(){
    return _name;
  }
  //returns the amount of money the user has
  public double getMoney() {
    return _money;
  }
  //returns the number of hands the player has played
  public int getHands() {
    return _numOfHands;
  }
  //returns the number of hands the player has won.
  public int getHandsWon() {
    return _handsWon;
  }
  //Mutator methods
  //sets the player name
  public void setName(String nam){
    _name=nam;
  }
  //sets the the amount of money
  public void setMoney(double mon){
    _money=mon;
  }
  //sets the number of hands the player has played
  public void setNumOfHands(int numOfHands){
      _numOfHands=numOfHands;
  }
  //sets the number of hands the player has won
  public void setHandsWon(int handsWon){
    _handsWon=handsWon;
  }

  //mutator method that sets vales from information from a textfile.
  //allows user to resume the game as they left it.
  public void resumeSave() throws IOException{ //file and Scanner
    File in =new File(_name+".TXT");
    Scanner file=new Scanner(in);
    _name=file.nextLine();
    _money=file.nextDouble();
    _numOfHands=file.nextInt();
    _handsWon=file.nextInt();
    file.close();
  }

  //allows user to save information to a textfile
  public void saveGame() throws IOException {
    PrintWriter playerInfo = new PrintWriter(_name+".txt");
    playerInfo.println(_name);
    playerInfo.println(_money);
    playerInfo.println(_numOfHands);
    playerInfo.println(_handsWon);
    playerInfo.close();
  }
  //Printout player information to console.
  public void displayPlayerInfo(){
    System.out.printf("Name:%21s", _name);
    System.out.printf("\nMoney:%,20.2f", _money);
    System.out.printf("\nTotal Hands:%14d", _numOfHands);
    System.out.printf("\nHands Won:%16d\n", _handsWon);
  }

}

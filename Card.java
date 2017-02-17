
public class Card
{
//***********************Class contains information about Cards that will be played in this game*******************//

  //array of ints for the cards
  //13 card values possible (2-10, Jack, Queen, King, Ace)
  private int[] _card=new int[13] ;
  // array of Suits to hold "heart" ,"club", "diamond", and "spade"
  private String[] _suit = new String[4];
  private int _cardV=0;
  private String _suitV="";
  //constructor
  //instantiate card with their respecitve values in this decision structure
  //random class will determine the cardIndex and suitType in the calling code
  //specific elements will be initalized due to this constructor.
  public Card(int cardIndex, int suitType){
      switch (cardIndex){
        //card 2
        case 0:
          _card[cardIndex]=2;
        break;
        //card 3
        case 1:
          _card[cardIndex]=3;
        break;
        //card 4
        case 2:
          _card[cardIndex]=4;
        break;
        //card5
        case 3:
          _card[cardIndex]=5;
        break;
        //card 6
        case 4:
          _card[cardIndex]=6;
        break;
        //card 7
        case 5:
          _card[cardIndex]=7;
        break;
        //card 8
        case 6:
          _card[cardIndex]=8;
        break;
        //card 9
        case 7:
          _card[cardIndex]=9;
        break;
        //card 10
        case 8:
          _card[cardIndex]=10;
        break;
        //Jack
        case 9:
          _card[cardIndex]=10;
        break;
        //Queen
        case 10:
          _card[cardIndex]=10;
        break;
        //King
        case 11:
          _card[cardIndex]=10;
        break;
        //Ace
        case 12:
          _card[cardIndex]=11;
        break;
        }
      //Suits
      switch (suitType){
        //club
        case 0:
        _suit[suitType]="c";
        break;
        //Spade
        case 1:
        _suit[suitType]="s";
        break;
        //Heart
        case 2:
        _suit[suitType]="h";
        break;
        //Diamond
        case 3:
        _suit[suitType]="d";
        break;

      }
    }
    //Mutator Methods are used to set the _cardV and _suitV values so that they can be printed in the
    //toString() Method
    //@param: copies values of integers and pass them to the _card array.
    public void setCardV(int cardIndex){
      _cardV=_card[cardIndex];
    }
    public void setSuitV(int suitType){
      _suitV=_suit[suitType];
    }




    //accessor return card value
    //@param: integers are passed in will be the index of the _card and _suit arrays
    //@return: return the respective values of the element of the array that is called.
    public int getCardValue(int cardIndex){
      return _card[cardIndex];
    }
    public String getSuitType(int suitType){
      return _suit[suitType];
    }




    //toString method to display card properly (e.g 5c for "five of clubs")
    //1-9 card printed out
    //T card 10
    //J card jack
    //Q card Queen
    //K card King
    //A card Ace
    //Suits: h for heart, s for spade, d for diamond, c for clubs.

    public String toString(){
      //condition if card "10" selected then T is printed with suit.
      if (_card[8]==10){
        String result="T"+_suitV+" ";
        return result;

      }
      //If jack is selected, then "J" is printed out with suit.
      else if (_card[9]==10){
        String result="J" + _suitV+" ";
        return result;
      }
      //If Queen is selected, then "Q" is printed out with suit.
      else if (_card[10]==10) {
        String result="Q" + _suitV+" ";
        return result;
      }
      //If King is selected, then "K" is printed out with suit.
      else if (_card[11]==10) {
        String result="K" + _suitV+ " ";
        return result;
      }
      //if ace is selected, then "A" is printed out and with suit
      else if(_card[12]==11 || _card[12]==1){
        String result="A"+_suitV+" ";
        return result;

      }
      //otherwise the number cards are printed out along with suit
      else{
      String result=Integer.toString(_cardV)+_suitV+" ";
      return result;
      }

    }
  }

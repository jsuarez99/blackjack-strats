/*
 * A CardDeck object.  Contains all useful functions for a card deck.
 */

package blackjack

import scala.collection.mutable.Stack

class CardDeck (shoeSize:Int = 1) {

  /*
   * CONSTRUCTOR
   * Creates a new deck (1 by default) and populates it with strings of numbers
   * First number => Suite (1=Diamonds,2=Clubs,3=Hearts,4=Spades)
   * Second number => Card (2 through 10,11=Jack,12=Queen,13=King,14=Ace)
   */
  if(shoeSize < 1) throw new IllegalArgumentException("Shoe size must be 1 or greater")
  private var cards = new Array[String](shoeSize*52);
  var k = 0;
  for(h <- 0 until shoeSize)
    for(i <- 1 to 4; j <- 2 to 14){
      cards(k) = i + "-" + j
      k += 1
    }

  /*
   * Prints out the contents of the deck in the order they will be drawn
   */
  def printDeck()= cards.foreach(println)

  /*
   * A shuffle function. 
   */
  def shuffle()= cards = scala.util.Random.shuffle(cards.toSeq).toArray

  /*
   * Draw a card from the top of the deck
   */
  def draw(asValueString:Boolean = false):String={
    if(isEmpty) return "The deck is empty";
    var s = new Stack[String]
    val topCard = s.pushAll(cards.reverse).pop
    cards = s.toArray
    if(asValueString) return cardValue(topCard)
    else return topCard
  }

  /*
   * Card Value
   * Convert from numbers to a card suit-face value
   */
  def cardValue(c:String):String={
    val suites = Map(1 -> "Diamonds",2 -> "Clubs",3 -> "Hearts",4 -> "Spades")
    val faces = Map(11 -> "Jack",12 -> "Queen",13 -> "King",14 -> "Ace")
    val suiteVal:String = c.split('-')(0)
    val faceVal:String = c.split('-')(1)
    if(faceVal.toInt > 10) return faces(faceVal.toInt) + " of " + suites(suiteVal.toInt)
    else return faceVal + " of " + suites(suiteVal.toInt)
  }

  /*
   * isEmpty
   * Returns true if the deck is empty
   */
  def isEmpty()= cards.isEmpty

  /*
   * DeckCount
   * returns the number of cards left in the deck
   */
  def DeckCount()= cards.length

} //end of class

/*
 * Player class
 */

package blackjack

import scala.collection.mutable.Stack
import blackjack.BlackJackStrategy.Strategy

class Player (startingCash:Double = 0, isDealer:Boolean = false) {
  /*
   * CONSTRUCTOR
   * Creates a new player object.  Set the isDealer value to true for all
   * game rules that require one.  The strategy variable is a function that is
   * assigned from the BlackJackStrategy class.
   */
  var cash = startingCash
  val dealer = isDealer
  
  //Set the dealer strategy automatically if this player is a dealer
  var strategy:Strategy = null.asInstanceOf[Strategy]//= new DealerStrategy

  var wins = 0
  var totalGames = 0

  var hand = new Array[String](0)

  /*
   * getWinPercentage
   * returns the player's win rate
   */
  def getWinPercentage():Double = wins.toDouble/totalGames.toDouble * 100

  /*
   * addCardToHand
   * Adds a card (string) to the player's hand array
   */
  def addCardToHand(s:String)= hand = new Stack[String].pushAll(hand).push(s).toArray

  /*
   * clearHand
   * Removes all cards from the hand array
   */
  def clearHand()= hand = new Array[String](0)
}
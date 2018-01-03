/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack

import blackjack.BlackJackStrategy._

import scala.collection.mutable.ArrayBuffer

class BlackJackRules(shoeSize:Int = 1){

  var d = new CardDeck(shoeSize)
  d.shuffle

  def runHand():Unit={

    var p = new Player()
    p.strategy = (new NeverBustStrategy).asInstanceOf[Strategy]
    var dealer = new Player(0,true)
    dealer.strategy = (new DealerStrategy).asInstanceOf[Strategy]

    //Run games until we are out of cards
    for(i <- 1 to 10000){
      if(d.DeckCount <= (shoeSize*52)/6){
        println(p.strategy.stratName + ": " + "%3.2f".format(p.getWinPercentage) + "%")
        return
      }

      //Deal out the first two cards to each player
      println("---Game " + i.toString + "---")
      p.addCardToHand(d.draw())
      dealer.addCardToHand(d.draw())
      p.addCardToHand(d.draw())
      dealer.addCardToHand(d.draw())

      //Player-----------
      println("Player:")
      p.hand.foreach(e => println("-" + d.cardValue(e)))
      println("Hand value: " + handValue(p.hand))

      //First, draw cards for the player depending on how long the strategy will return true
      while(p.strategy(handValue(p.hand))){
        val drawnCard = d.draw()
        println(d.cardValue(drawnCard))
        p.addCardToHand(drawnCard)
        println("Hand value: " + handValue(p.hand))
      }

      //Dealer----------------
      println("Dealer:")
      dealer.hand.foreach(e => println("-" + d.cardValue(e)))
      println("Hand value: " + handValue(dealer.hand))

      //Now the dealer draws
      while(dealer.strategy(handValue(dealer.hand))){
        val drawnCard = d.draw()
        println(d.cardValue(drawnCard))
        dealer.addCardToHand(drawnCard)
        println("Hand value: " + handValue(dealer.hand))
      }

      //Determine the winner -----------------
      if(handValue(p.hand) > handValue(dealer.hand)){
        println("Player wins!")
        p.wins += 1
      }
      else if(handValue(p.hand) < handValue(dealer.hand))
        println("Dealer wins")
      else
        println("Push")

      p.totalGames += 1
      dealer.clearHand
      p.clearHand
    }
  }

  /*
   * Determines the value of the player's hand.  Takes the hand ArrayBuffer as a parameter
   */
  def handValue(h:ArrayBuffer[String]):Int={
    var total:Int = 0;

    var cardVals:List[Int] = h.map{card =>
        val face:Int = card.split('-')(1).toInt
        if(face <= 10) face
        else if(face > 10 && face != 14) 10
        else if(face == 14) 11
      }
      .toList
      .map(_.toString.toInt)

    total = cardVals.sum

    //If the sum is greater than 21, start converting 11 Ace values to 1 values by subtracting 10 for each one
    //Each ace is converted to 1 while the total is >21 until there are no more.
    while(cardVals.contains(11) && cardVals.sum > 21) {
      total -= 10
      cardVals = cardVals.patch(cardVals.indexOf(11),Nil,1)
    }

    if(total > 21){
      return -1
    }
    else
      return total
  }
}

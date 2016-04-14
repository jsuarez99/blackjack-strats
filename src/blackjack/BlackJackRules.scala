/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack

import blackjack.BlackJackStrategy._

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
      p.addCardToHand(d.draw())
      dealer.addCardToHand(d.draw())
      p.addCardToHand(d.draw())
      dealer.addCardToHand(d.draw())

      //First, draw cards for the player depending on how long the strategy
      //will return true
      while(p.strategy(handValue(p.hand))) p.addCardToHand(d.draw())

      while(dealer.strategy(handValue(dealer.hand))) dealer.addCardToHand(d.draw())


      println("---Results---\nPlayer:")
      p.hand.foreach(e => println(d.cardValue(e)))
      println(handValue(p.hand))
      println("Dealer:")
      dealer.hand.foreach(e => println(d.cardValue(e)))
      println(handValue(dealer.hand))
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
   * Determines the value of the player's hand.  Takes the hand array as a
   * parameter
   */
  def handValue(h:Array[String]):Int={
    var total:Int = 0;
    for(i <- 0 until h.length){
      val face:Int = h(i).split('-')(1).toInt
        if(face <= 10) total = total + face;
        else if(face > 10 && face != 14) total = total + 10;
        else if(face == 14 && total <= 10) total = total + 11;
        else if(face == 14 && total > 10) total = total + 1;
    }
    if(total > 21) return -1
    else return total
  }
}

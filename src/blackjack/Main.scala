/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack

object Main {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    
//    for(i <- 1 to 4){
//      if(i % 2 == 1) println("You: " + d.draw(true))
//      else println("Dealer: " + d.draw(true))
//    }
//    d.printDeck

    var bj = new BlackJackRules(8)
    bj.runHand
    
  }

}

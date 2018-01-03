/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack.BlackJackStrategy

class DealerStrategy extends Strategy {

  val stratName:String = "Dealer Strategy"

  /*
   * Returns true to hit, false to stay;  Dealers stand on 17 or higher
   */
  override def apply(handVal:Int):Boolean={
    if(handVal == -1){
      println("Bust")
      false
    }
    else if(handVal >= 17) {
      println("Stand")
      false
    }
    else{
      print("Hit...")
      true
    }
  }
}

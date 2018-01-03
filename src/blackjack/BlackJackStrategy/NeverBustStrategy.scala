/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack.BlackJackStrategy

class NeverBustStrategy extends Strategy {

  val stratName:String = "Never Bust Strategy"

  /*
   * Returns true to hit, false to stay;  Never hit on anything greater than 11
   */
  override def apply(handVal:Int):Boolean={
    if(handVal == -1){
      println("Bust")
      false
    }
    else if(handVal > 11){
      println("Stand")
      false
    }
    else{
      print("Hit...")
      true
    }
  }
}

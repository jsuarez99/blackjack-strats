/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack.BlackJackStrategy

abstract class Strategy {
  val stratName:String
  def apply(handVal:Int):Boolean=false
}

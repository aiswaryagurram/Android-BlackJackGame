/*************************************************************
 *        class name : CardHandler.java						 *
 *         													 *
 *         purpose: This java class is used the conditions	 *
 *                  for bust , win and gets the total 		 *
 *                  amount									 *
 ************************************************************/
package edu.niu.cs.z1807688.blackjack_game;
import java.util.Vector;
abstract class CardHand extends Vector<Todo.Card> {
	public CardHand()
	{
		super();
	}
	public void clear()
	{
		super.clear();
	}
	public int get_Total() {
		int total = 0;
		for (Todo.Card eachCard : this)
		{
			total = total +eachCard.getValue();
		}
		return total;
	}
	public boolean is_Bust() {
		if(get_Total() > 21)
			return true;
		else
			return false;
	}
	public boolean is_Won() {
		if ( get_Total() == 21 && this.size()>2)
			return true;
		else
			return false;
	}
	public boolean has_Blackjack() {
		if(get_Total() == 21 && this.size() == 2)
			return true;
		else
			return false;
	}
	public String toString() {
		return super.toString() + " (" + get_Total() + ")";
	}
}
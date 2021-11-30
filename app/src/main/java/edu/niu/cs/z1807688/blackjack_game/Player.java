/**********************************************************
 *   class name : Player.java                        	  *
 *   												 	  *
 *   purpose : This java class is used to add or subtract *
 *   			money from the balance when the user wins * 
 *   		    or loses and for other actions			  *
 *														  *
 *********************************************************/


package edu.niu.cs.z1807688.blackjack_game;

import java.io.Serializable;

class Player implements Serializable {
	public transient PlayerCardHand hand = new PlayerCardHand();
	private float wallet = 100;
	private double bet = 0;
	public Player() {
		super();
	}
	public Player(float amount) {
		setWallet(amount);
	}
	public void setWallet(float amount) {
		this.wallet = amount;
	}
	public double getWallet() {
		return this.wallet;
	}
	public boolean setBet(double bet) {
		boolean bet_placed = false;
		if (bet <= (getWallet() + getBet())) {
			// reset old bet
			this.wallet += this.bet;
			// set new bet
			this.bet = bet;
			// update wallet
			this.wallet -= bet;
			bet_placed = true;
		}

		return bet_placed;
	}


	public double getBet() {
		return this.bet;
	}

	public void clearBet() {
		this.wallet += this.bet;
		this.bet = 0.0;
	}


	public boolean doubleBet() {
		boolean bet_doubled = false;
		if (setBet(getBet() * 2)) {
			bet_doubled = true;
		}

		return bet_doubled;
	}
	public void loses() {
		this.bet = 0.0;
	}
	public void wins(int amount) {
		this.wallet += amount;
		this.bet = 0.0;
		Todo.PutData(MyApplication.getAppContext(),"amount",wallet);
	}
	public boolean betPlaced()
	{
		if(getBet()>0.0)
			return true;
		else
			return false;
	}
	// wallet less than 1 in case .5 is in there from a blackjack
	public boolean isBankrupt() {
		if(getBet()<= 0 && getWallet() < 1)
			return true;
		else
			return false;
		//return (getWallet() < 1 && getBet() <= 0) ? true : false;
	}
	public boolean canDouble() {
		boolean answer = false;
		if (getBet() <= getWallet()) {
			answer = true;
		}
		return answer;
	}
	public PlayerCardHand getHand() {
		return this.hand;
	}
}
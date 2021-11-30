package edu.niu.cs.z1807688.blackjack_game;

import java.io.Serializable;


class Dealer implements Serializable {
	private Deck deck;
	public Todo.DealerCardHand hand = new Todo.DealerCardHand();
	private boolean firstDeal = true;
	public static final int DEALER_STANDS_ON = 17;
	public   int c_pack = Todo.decks;
	private boolean game_Over = true;
	private boolean cfaceup = false;
	private boolean playerCanDouble = true;
	private String said = "Please place your wager.";
	public Dealer() {

		deck = new Deck(c_pack);
	}
	public void say(String announcement) {
		said = announcement;
		System.out.println(said);
	}



	public String says() {

		return said;
	}

	public boolean isGameOver()
	{
		return game_Over;
	}

	public boolean areCardsFaceUp() {

		return cfaceup;
	}


	public boolean acceptBetFrom(Player player, double bet) {
		boolean betSet = player.setBet(bet);

		if (player.betPlaced()) {
			say("Thank you for your bet of $" + player.getBet()
					+ ". Would you like me to deal?");
		} else {
			say("Please place your bet.");
		}

		return (betSet) ? true : false;
	}


	public boolean deal(Player player) {
		boolean cardsDealt = false;

		if (player.betPlaced() && !player.isBankrupt()) {
			game_Over = false;
			cfaceup = false;

			playerCanDouble = true;

			player.hand = new PlayerCardHand();
			hand = new Todo.DealerCardHand();

			say("Initial deal made.");

			player.hand.add(deck.deal());
			this.hand.add(deck.deal());

			player.hand.add(deck.deal());
			this.hand.add(deck.deal());

			cardsDealt = true;
			firstDeal = false;

			if (player.hand.has_Blackjack()) {
				say("Blackjack!");
				go(player);
			}

		} else if (!player.betPlaced()) {
			say("Please place your bets.");
			game_Over = true;
		}

		return cardsDealt;
	}


	public void hit(Player player) {
		say("Player" + " hits.");
		player.hand.add(deck.deal());

		playerCanDouble = false;

		if (player.hand.is_Bust()) {
			say("Player" + " busts. Loses $" + player.getBet());
			Todo.PutData(MyApplication.getAppContext(),"amount", (int) (Todo.GetData(MyApplication.getAppContext(),"amount")-player.getBet()));
			player.loses();
		//	Toast.makeText(MyApplication.getAppContext(),"Here"+player.getBet(),Toast.LENGTH_LONG).show();
			game_Over = true;
		}

		if (player.hand.is_Won()) {
			say("Player" + " Won. Win $" + player.getBet());

		//	Toast.makeText(MyApplication.getAppContext(),"Here2",Toast.LENGTH_LONG).show();
		//	Helper.PutData(MyApplication.getAppContext(),"amount", (int) (Helper.GetData(MyApplication.getAppContext(),"amount")-player.getBet()));
			player.wins((int) (player.getBet()*2));
		//	Toast.makeText(MyApplication.getAppContext(),"Here bet"+player.getBet(),Toast.LENGTH_LONG).show();
			game_Over = true;
		}


	}


	public void playDouble(Player player) {
		if (player.doubleBet() && playerCanDouble) {
			player.hand.add(deck.deal());
			say("Player" +" plays double.");

			if (player.hand.is_Bust()) {
				say("Player" +" busts. Loses $" + player.getBet());

			//	Toast.makeText(MyApplication.getAppContext(),"Here3",Toast.LENGTH_LONG).show();
				Todo.PutData(MyApplication.getAppContext(),"amount", (int) (Todo.GetData(MyApplication.getAppContext(),"amount")-player.getBet()));
				player.loses();
				game_Over = true;
			} else {
				go(player);
			}
		} else {
			say("Player" +", you can't double. Not enough money.");
		}
	}


	public void stand(Player player) {
		say("Player"  + " stands. " + "Player"  + " turn.");
		go(player);
	}


	private void go(Player player) {
		cfaceup = true;

		if (!hand.has_Blackjack()) {
			while (hand.get_Total() < DEALER_STANDS_ON) {
				hand.add(deck.deal());
				say("Player" + " hits.");
			}

			if (hand.is_Bust()) {
				say("Player" + "  is BUST");
			} else {
				say("Player" + " stands on " + hand.get_Total());
			}
		} else {
			say("Player" + " has BLACKJACK!");
		}

		if (hand.has_Blackjack() && player.hand.has_Blackjack()) {
			say("Push");
			player.clearBet();
		} else if (player.hand.has_Blackjack()) {
			double winnings = (player.getBet() * 3) / 2;
			say("Player"  + " wins with Blackjack $" + winnings);
			player.wins((int) (player.getBet() + winnings));
			//Helper.PutData(MyApplication.getAppContext(),"amount", (int) (player.getBet() + winnings));
		} else if (hand.has_Blackjack()) {
			say("Dealer has Blackjack. " + "Player" + " loses $" + player.getBet());
			//	Toast.makeText(MyApplication.getAppContext(),"Here4",Toast.LENGTH_LONG).show();
			Todo.PutData(MyApplication.getAppContext(),"amount", (int) (Todo.GetData(MyApplication.getAppContext(),"amount")-player.getBet()));
			player.loses();
		} else if (hand.is_Bust()) {
			say("Dealer is bust. " +"Player" + " wins $"
					+ player.getBet());
			player.wins((int) (player.getBet() * 2));

		} else if (player.hand.get_Total() == hand.get_Total()) {
			say("Push");

			player.clearBet();
		} else if (player.hand.get_Total() < hand.get_Total()) {
			say("Player" + " loses $" + player.getBet());

		//	Toast.makeText(MyApplication.getAppContext(),"Here5",Toast.LENGTH_LONG).show();
			Todo.PutData(MyApplication.getAppContext(),"amount", (int) (Todo.GetData(MyApplication.getAppContext(),"amount")-player.getBet()));
			player.loses();
		} else if (player.hand.get_Total() > hand.get_Total()) {
			say("Player" +" wins $" + player.getBet());
			player.wins((int) (player.getBet() * 2));
		}

		game_Over = true;
	}

	public int cardsLeftInPack() {
		return deck.size();
	}

	public void newDeck() {
		deck = new Deck(c_pack);
	}

	public boolean canPlayerDouble(Player player) {
		return (playerCanDouble && player.canDouble()) ? true : false;
	}

	public Todo.DealerCardHand getHand() {
		return hand;
	}
}
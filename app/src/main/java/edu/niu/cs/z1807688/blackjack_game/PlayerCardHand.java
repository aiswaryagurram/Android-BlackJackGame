package edu.niu.cs.z1807688.blackjack_game;

class PlayerCardHand extends CardHand {
	public PlayerCardHand() {
		super();
	}
	public boolean add(Todo.Card card) {
		boolean card_add = false;
		if (!is_Bust() && !has_Blackjack()) {
			card_add = super.add(card);
			// if bust, check if all Aces are set to 1, if not, do it!
			if (is_Bust()) {
				for (Todo.Card eachCard : this) {
					// switch ace to low 1 switching...
					eachCard.getFace().switchAce();
					// if hand isn't bust anymore, then stop switching
					if (!is_Bust()) {
						// needed to break out of loop to stop switching aces
						break;
					}
				}
			}
		}
		return (card_add) ? true : false;
	}
}
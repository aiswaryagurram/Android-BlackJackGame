package edu.niu.cs.z1807688.blackjack_game;

import java.util.Collections;
import java.util.Stack;

/****************************************************************
 *   class name : Deck.java 									*
 *   															*
 *   purpose: This java class sets the pack of cards according  *
 *             to the user selection and also shuffle the cards *
 *             for the game										*
 *   															*
 ***************************************************************/

class Deck extends Stack<Todo.Card>
{
	private int no_of_Packs;
	public Deck() {
		super();
		setNumberOfPacks(Todo.decks);
		this.addAll(new Todo.CardPack());
		shuffle();
	}
	public Deck(int packs) {
		super();
		setNumberOfPacks(packs);
		int i =0;
		while( i < packs)
		 {
			this.addAll(new Todo.CardPack());
			i++;
		}
		shuffle();
	}
	private void setNumberOfPacks(int n)
	{
		this.no_of_Packs = n;
	}
	public int getNumberOfPacks()
	{
		return this.no_of_Packs;
	}
	public void shuffle()
	{
		Collections.shuffle(this);
	}
	public Todo.Card deal() {
		if (this.empty()) {
			System.out.println("Run out of cards. New Deck.");
			int i = 0;
			while( i < no_of_Packs)
			 {
				this.addAll(new Todo.CardPack());
				i++;
			}
			shuffle();
		}
		return this.pop();
	}
}
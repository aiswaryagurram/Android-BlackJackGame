package edu.niu.cs.z1807688.blackjack_game;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Stack;
public class Todo {
    public static int decks=1;
    public static void PutData(Context context, String key, float  value)
    {
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("ssshhhh!!!", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static float GetData(Context context, String key)
    {
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("ssshhhh!!!", Context.MODE_PRIVATE);
            return sharedpreferences.getFloat(key, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    static class Game {
        private Dealer dealer;
        private Player player;
        public Game(Dealer dealer, Player player) {
            this.dealer = dealer;
            this.player = player;
        }
        public void setDealer(Dealer dealer) {
            this.dealer = dealer;
        }
        public void setPlayer(Player player) {
            this.player = player;
        }
        public Dealer getDealer() {
            return dealer;
        }
        public Player getPlayer() {
            return player;
        }
        public void increaseBet(float amount) {
            dealer.acceptBetFrom(player, amount + player.getBet());
        }
        public void hit() {
            dealer.hit(player);
        }
        public void stand() {
            dealer.stand(player);
        }
        public void playDouble() {
            dealer.playDouble(player);
        }
        public void clearBet() {
            player.clearBet();
        }
        public void newGame() {
            dealer.deal(player);
        }
    }
    static class Suit {
        private String name;
        public Suit(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public String toString() {
            return getName();
        }
    }
    static class Table {
        private DealerCardHand dealer;
        private PlayerCardHand player;
        private boolean showAllDealerCards;
        public void setDealer(DealerCardHand dealer) {
            this.dealer = dealer;
        }
        public void setPlayer(PlayerCardHand player) {
            this.player = player;
        }
        public DealerCardHand getDealer() {
            return dealer;
        }
        public PlayerCardHand getPlayer() {
            return player;
        }
        public boolean isShowAllDealerCards() {
            return showAllDealerCards;
        }
        public void setShowAllDealerCards(boolean showAllDealerCards) {
            this.showAllDealerCards = showAllDealerCards;
        }
        public void updateAll(DealerCardHand dealer, PlayerCardHand player,
                              boolean showDealer) {
            setDealer(dealer);
            setPlayer(player);
            setShowAllDealerCards(showDealer);
        }
    }
    static class Card {
        private edu.niu.cs.z1807688.blackjack_game.Todo.Suit suit;
        private Face face;
        private int code;
        public Card(Face face, edu.niu.cs.z1807688.blackjack_game.Todo.Suit suit, int code) {
            setFace(face);
            setSuit(suit);
            setCode(code);
        }
        private void setFace(Face face) {
            this.face = face;
        }
        public Face getFace() {
            return this.face;
        }
        private void setSuit(edu.niu.cs.z1807688.blackjack_game.Todo.Suit suit) {
            this.suit = suit;
        }
        public edu.niu.cs.z1807688.blackjack_game.Todo.Suit getSuit() {
            return this.suit;
        }
        private void setCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public int getValue() {
            return this.getFace().getValue();
        }
        public String toString() {
            return getFace() + " of " + getSuit();
        }
    }
    static class CardPack extends Stack<Card> {
        public static final int CARDS_IN_PACK = 52;
        public CardPack() {
            super();
            final String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
            int cardCode = 1;
            for (String suit : suits) {
                for (int i = 1; i < 14; i++) {
                    this.push(new edu.niu.cs.z1807688.blackjack_game.Todo.Card(new Face(i), new edu.niu.cs.z1807688.blackjack_game.Todo.Suit(suit), cardCode));
                    cardCode++;
                }
            }
        }
    }
    static class DealerCardHand extends CardHand {
        public DealerCardHand() {
            super();
        }
        public boolean add(edu.niu.cs.z1807688.blackjack_game.Todo.Card card) {
            boolean cardAdded = false;

            if (!is_Bust() && !has_Blackjack()) {
                cardAdded = super.add(card);
            }
            return (cardAdded) ? true : false;
        }
    }
}

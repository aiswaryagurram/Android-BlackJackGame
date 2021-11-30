package edu.niu.cs.z1807688.blackjack_game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends Activity {
	private Todo.Game game = null;
	private Todo.Table table = null;
	float bet;

	// dealer cards array
	private ImageView p1cards[] = new ImageView[10];

	// player cards array
	private ImageView p2cards[] = new ImageView[10];

	private Map<Integer, Integer> cards = new HashMap<Integer, Integer>();
	// update view method to initialize all values and put cards into hashmap

	private void updateViews() {
		table.updateAll(game.getDealer().getHand(), game.getPlayer().getHand(), (game.getDealer().areCardsFaceUp()) ? true : false);

		// putting all cards into hashmap
		cards.put(1, R.drawable.card01);
		cards.put(2, R.drawable.card02);
		cards.put(3, R.drawable.card03);
		cards.put(4, R.drawable.card04);
		cards.put(5, R.drawable.card05);
		cards.put(6, R.drawable.card06);
		cards.put(7, R.drawable.card07);
		cards.put(8, R.drawable.card08);
		cards.put(9, R.drawable.card09);
		cards.put(10, R.drawable.card10);
		cards.put(11, R.drawable.card11);
		cards.put(12, R.drawable.card12);
		cards.put(13, R.drawable.card13);
		cards.put(14, R.drawable.card14);
		cards.put(15, R.drawable.card15);
		cards.put(16, R.drawable.card16);
		cards.put(17, R.drawable.card17);
		cards.put(18, R.drawable.card18);
		cards.put(19, R.drawable.card19);
		cards.put(20, R.drawable.card20);
		cards.put(21, R.drawable.card21);
		cards.put(22, R.drawable.card22);
		cards.put(23, R.drawable.card23);
		cards.put(24, R.drawable.card24);
		cards.put(25, R.drawable.card25);
		cards.put(26, R.drawable.card26);
		cards.put(27, R.drawable.card27);
		cards.put(28, R.drawable.card28);
		cards.put(29, R.drawable.card29);
		cards.put(30, R.drawable.card30);
		cards.put(31, R.drawable.card31);
		cards.put(32, R.drawable.card32);
		cards.put(33, R.drawable.card33);
		cards.put(34, R.drawable.card34);
		cards.put(35, R.drawable.card35);
		cards.put(36, R.drawable.card36);
		cards.put(37, R.drawable.card37);
		cards.put(38, R.drawable.card38);
		cards.put(39, R.drawable.card39);
		cards.put(40, R.drawable.card40);
		cards.put(41, R.drawable.card41);
		cards.put(42, R.drawable.card42);
		cards.put(43, R.drawable.card43);
		cards.put(44, R.drawable.card44);
		cards.put(45, R.drawable.card45);
		cards.put(46, R.drawable.card46);
		cards.put(47, R.drawable.card47);
		cards.put(48, R.drawable.card48);
		cards.put(49, R.drawable.card49);
		cards.put(50, R.drawable.card50);
		cards.put(51, R.drawable.card51);
		cards.put(52, R.drawable.card52);

		p1cards[0] = (ImageView) findViewById(R.id.player1card1);
		p1cards[1] = (ImageView) findViewById(R.id.player1card2);
		p1cards[2] = (ImageView) findViewById(R.id.player1card3);
		p1cards[3] = (ImageView) findViewById(R.id.player1card4);
		p1cards[4] = (ImageView) findViewById(R.id.player1card5);
		p1cards[5] = (ImageView) findViewById(R.id.player1card6);
		p1cards[6] = (ImageView) findViewById(R.id.player1card7);
		p1cards[7] = (ImageView) findViewById(R.id.player1card8);
		p1cards[8] = (ImageView) findViewById(R.id.player1card9);
		p1cards[9] = (ImageView) findViewById(R.id.player1card10);

		p2cards[0] = (ImageView) findViewById(R.id.player2card1);
		p2cards[1] = (ImageView) findViewById(R.id.player2card2);
		p2cards[2] = (ImageView) findViewById(R.id.player2card3);
		p2cards[3] = (ImageView) findViewById(R.id.player2card4);
		p2cards[4] = (ImageView) findViewById(R.id.player2card5);
		p2cards[5] = (ImageView) findViewById(R.id.player2card6);
		p2cards[6] = (ImageView) findViewById(R.id.player2card7);
		p2cards[7] = (ImageView) findViewById(R.id.player2card8);
		p2cards[8] = (ImageView) findViewById(R.id.player2card9);
		p2cards[9] = (ImageView) findViewById(R.id.player2card10);

		((TextView) findViewById(R.id.message_text)).setText(game.getDealer().says());
		if( game.getDealer().says().contains("loses") || game.getDealer().says().contains("Loses")){
			show_result_popup(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,2);
			table.setShowAllDealerCards(true);
		}
		// below code executed when announcement comes of push mean when push occur
		else if (game.getDealer().says().contains("Win") || game.getDealer().says().contains("wins")){
			show_result_popup(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,1);
			table.setShowAllDealerCards(true);
		}
		// below code executed when announcement comes of push mean when push occur
		else if (game.getDealer().says().contains("Push") || game.getDealer().says().contains("Push")){
			show_result_popup(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,3);
			table.setShowAllDealerCards(true);
		}

		// below code of showing available cards and total cards
		((TextView) findViewById(R.id.available_cards_text)).setText("" + game.getDealer().cardsLeftInPack());
		((TextView) findViewById(R.id.total_cards_text)).setText("" + (game.getDealer().c_pack * Todo.CardPack.CARDS_IN_PACK));
		((TextView) findViewById(R.id.bet_text)).setText("Bet - " + game.getPlayer().getBet());
		((TextView) findViewById(R.id.balance_text)).setText("Balance - " + game.getPlayer().getWallet());
		((TextView) findViewById(R.id.player_1_name_text)).setText("Dealer");

		// code executed when dealer show all cards to player
		if (table.isShowAllDealerCards() == true) {
			((TextView) findViewById(R.id.player_1_score_text)).setText(""+ table.getDealer().get_Total());
		} else {
			((TextView) findViewById(R.id.player_1_score_text)).setText("");
		}
		((TextView) findViewById(R.id.player_2_name_text)).setText("Player");
		// player total and show in textview
		((TextView) findViewById(R.id.player_2_score_text)).setText("" + table.getPlayer().get_Total());
		int i = 0;
		for (ImageView view : p1cards) {
			view.setImageBitmap(null);
		}
		// this below function for showing dealer cards
		for (Todo.Card aCard : table.getDealer()) {
			p1cards[i].setImageResource(cards.get(aCard.getCode()));
			i++;
		}
		if (table.isShowAllDealerCards() == false && table.getDealer().size() > 0) {
			p1cards[0].setImageResource(R.drawable.back);
		}
		for (ImageView view : p2cards) {
			view.setImageBitmap(null);
		}
		// this below for showing user cards
		i = 0;
		for (Todo.Card aCard : table.getPlayer()) {
			p2cards[i].setImageResource(cards.get(aCard.getCode()));
			i++;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		game = new Todo.Game(new Dealer(), new Player( Todo.GetData(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,"amount")));
		table = new Todo.Table();
		// below code to get bet amount that user chooses in previous activity
		bet = getIntent().getExtras().getInt("bet",0);
		// below code is of surrender after user presses that mean he surrender and half of amount will give to dealer
		((Button) findViewById(R.id.btnSurrender))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						game.clearBet();
						Todo.PutData(MyApplication.getAppContext(),"amount", (float) (Todo.GetData(MyApplication.getAppContext(),"amount")-bet/2));
						new AlertDialog.Builder(edu.niu.cs.z1807688.blackjack_game.GameActivity.this)
								.setTitle("Black Jack Game")
								.setCancelable(false)
								.setMessage("Player Surrender and losses half of money "+bet/2)
								.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int which) {
								finish();
								startActivity(new Intent(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,BettingActivity.class));
									}
								})
								.setIcon(android.R.drawable.ic_dialog_alert)
								.show();
					}
				});

		((Button) findViewById(R.id.deal_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						game.newGame();
						updateViews();
					}
				});
		// below button is for when user hit
		((Button) findViewById(R.id.hit_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						game.hit();
						updateViews();
					}
				});
		// this button function is what when player trying to double
		((Button) findViewById(R.id.double_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						game.playDouble();
						updateViews();
					}
				});

		((Button) findViewById(R.id.stand_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						game.stand();
						updateViews();
					}
				});
		game.increaseBet(bet);
		// start of new game
		game.newGame();
		// update all view in below code
		updateViews();
	}

	public  void show_result_popup(Context context,int value)
	{
		LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
		View mView = layoutInflaterAndroid.inflate(R.layout.popup_win_lose_tie, null);
		AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
		alertDialogBuilderUserInput.setView(mView);
		Button btn_play_again=mView.findViewById(R.id.btn_play_again);
		btn_play_again.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
				startActivity(new Intent(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,BettingActivity.class));
			}
		});
		ImageView img=mView.findViewById(R.id.img);
		if(value==1){
			img.setBackgroundResource(R.drawable.win);
		}
		else if(value==2){

			img.setBackgroundResource(R.drawable.lose);
		}
		else{
			img.setBackgroundResource(R.drawable.tie);
		}

		AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
		alertDialogAndroid.setCancelable(false);
		alertDialogAndroid.setOnKeyListener(new Dialog.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface arg0, int keyCode,
								 KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					finish();
					startActivity(new Intent(edu.niu.cs.z1807688.blackjack_game.GameActivity.this,BettingActivity.class));
					alertDialogAndroid.dismiss();
				}
				return true;
			}
		});
		alertDialogAndroid.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		alertDialogAndroid.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(alertDialogAndroid.getWindow().getAttributes());
		lp.width = 500;
		lp.height = 500;
		lp.y=100;
		alertDialogAndroid.getWindow().setAttributes(lp);
	}

}

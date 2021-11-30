/***************************************************************
 *                                                             
 *                                                             *
 * Class Name:  BettingActivity                                *
 *                                                             *                          *
 *                                                             *
 * Purpose: This java class is used to display the  bet amount *
 *          and the remaining balance with the player          *
 *                                                             *
 ***************************************************************/
package edu.niu.cs.z1807688.blackjack_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    Button mEnterButton;
    private Todo.Game game = null;
    String[] decks = { "1", "2", "3", "4"};
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.betting_main);

        // this below part of code to set initial amount
        if(Todo.GetData(edu.niu.cs.z1807688.blackjack_game.BettingActivity.this,"amount")==0.0){
            Todo.PutData(edu.niu.cs.z1807688.blackjack_game.BettingActivity.this,"amount",1000);
        }

        game = new Todo.Game(new Dealer(), new Player( Todo.GetData(edu.niu.cs.z1807688.blackjack_game.BettingActivity.this,"amount")));

        ((TextView) findViewById(R.id.betTotal)).setText("Balance " + game.getPlayer().getWallet()+"$");

        // this below button is to place of one dollar and increase when keep pressing
        ((ImageButton) findViewById(R.id.one)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game.increaseBet(1);
                        ((TextView) findViewById(R.id.cashTotal)).setText("Bet Total " +"$"+game.getPlayer().getBet());
                        ((TextView) findViewById(R.id.betTotal)).setText(" Balance " + "$"+game.getPlayer().getWallet());
                    }
                });

        // this below button is to place of five dollar and increase when keep pressing
        ((ImageButton) findViewById(R.id.five))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game.increaseBet(5);
                        ((TextView) findViewById(R.id.cashTotal)).setText("Bet Total " + "$"+ game.getPlayer().getBet());
                        ((TextView) findViewById(R.id.betTotal)).setText("Balance " +"$"+ game.getPlayer().getWallet());
                    }
                });

        // this below button is to place of twenty five dollar and increase when keep pressing
        ((ImageButton) findViewById(R.id.twenty_five))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game.increaseBet(25);
                        ((TextView) findViewById(R.id.cashTotal)).setText("Bet Total " +"$"+ game.getPlayer().getBet());
                        ((TextView) findViewById(R.id.betTotal)).setText("Balance " + "$"+game.getPlayer().getWallet());
                    }
                });
        // this below button is to place of fifty dollar and increase when keep pressing

        ((ImageButton) findViewById(R.id.fifty))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game.increaseBet(50);
                        ((TextView) findViewById(R.id.cashTotal)).setText("Bet Total " + "$"+game.getPlayer().getBet());
                        ((TextView) findViewById(R.id.betTotal)).setText("Balance " + "$"+game.getPlayer().getWallet());
                    }
                });
        // this below button is to place of hundred dollar and increase when keep pressing
        ((ImageButton) findViewById(R.id.hundred))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game.increaseBet(100);
                        ((TextView) findViewById(R.id.cashTotal)).setText("Bet Total " + "$"+game.getPlayer().getBet());
                        ((TextView) findViewById(R.id.betTotal)).setText("Balance " + "$"+game.getPlayer().getWallet());
                    }
                });


        // this below button is to make deal and bring user to game activity
        mEnterButton = (Button)findViewById(R.id.deal);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( (int)game.getPlayer().getBet()>0){
                    Intent intent = new Intent(edu.niu.cs.z1807688.blackjack_game.BettingActivity.this, GameActivity.class);
                    intent.putExtra("bet", (int)game.getPlayer().getBet());  // pass your values and retrieve them in the other Activity using keyName
                    intent.putExtra("wallet", String.valueOf(game.getPlayer().getWallet()));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(edu.niu.cs.z1807688.blackjack_game.BettingActivity.this,"Select bet first",Toast.LENGTH_LONG).show();

                }

            }
        });

        // below part of code to populate decks for user choice that he can select upto four decks
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,decks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) arg0.getChildAt(0)).setTextSize(25);
        Todo.decks=Integer.parseInt(decks[position]);

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

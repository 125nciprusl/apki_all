package com.example.pruus.guessmynumber;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    int myNumber;
    int userNumber;
    int guessCount = 0;
    TextView textView;
    TextView textView2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    int bestScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

        sharedPreferences = getSharedPreferences("com.example.pruus.guessmynumber", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // tworzy nowa gre juz na poczatku programu i mozna zgadywac przed kliknieciem new game
        Random rand = new Random();
        myNumber = rand.nextInt((100-0)+1)+0;
        guessCount = 0;
        textView.setText("Times guessed: "+ guessCount);
        textView2.setText("Best Score: "+ sharedPreferences.getInt("BestScore", 100));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    // METODY:

    public void newGame(View view) {
        Random rand = new Random();
        myNumber = rand.nextInt((100-0)+1)+0;
        guessCount = 0;
        textView.setText("Times guessed: "+ guessCount);


    }

    public void takeTheGuess(View view)
    {
        guessCount++;

        EditText editText = (EditText)findViewById(R.id.editText);
        userNumber = Integer.parseInt(editText.getText().toString());
        String message ="";

        if(userNumber>myNumber){
            message = "Wylosowana liczba jest mniejsza";
        }
        else if (userNumber<myNumber){
            message = "Wylosowana liczba jest wieksza";
        }
        else if (userNumber==myNumber){
            message = "Zgadles!!!";
            if(guessCount< sharedPreferences.getInt("BestScore", 100))
                {
                editor.putInt("BestScore", guessCount);
                editor.commit();
                }
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        textView.setText("Times guessed: "+ guessCount);
        textView2.setText("Best Score: "+ sharedPreferences.getInt("BestScore", 100));
    }
}

package com.prus.poleprostokta;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PoleTrojkata extends ActionBarActivity {

    double a = 0,h = 0;
    double pole, obwod;
    TextView textView3;
    TextView textView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole_trojkata);
        textView3 = (TextView)findViewById(R.id.textView3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pole_trojkata, menu);
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

    public void oblicz(View view)
    {
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        if (editText1.getText().toString().matches("")) {a = 0;}
        else a = Double.parseDouble(editText1.getText().toString());

        EditText editText2 = (EditText) findViewById(R.id.editText2);
        if (editText2.getText().toString().matches("")) {h = 0;}
        else h = Double.parseDouble(editText2.getText().toString());

        pole = a*h/2;

        if (a == 0 || h == 0)
        {
            String message;
            if (a == 0 || h == 0) {
                message = "Wpisz poprawne dane";
            } else {
                message = "";
            }
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }
        else
        {
            textView3.setText("Pole: " + pole);
        }

    }

    public void prostokat(View view)
    {
        finish();
    }
}

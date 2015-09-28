package com.prus.kalkulatorloupre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Callable;


public class MainActivity extends ActionBarActivity {

    double p0 = 0, p3 = 0.03, p6 = 0.06, p9 = 0.09, p12 = 0.12, p15 = 0.15, p18 = 0.18, p21 = 0.21;
    double pLeft=0, pRight=0, pMoje=0; // POZIOMY
    double pktLeft=0, pktRight=0, pktMoje=1, pktWlasne=0; // pMoje - wszystkie punkty razem
    double prowizja;
    double rownowaga;
    TextView textView;
    TextView textView2;
    TextView textView4;
    private static ImageView imgViewW;
    private static ImageView imgViewL;
    private static ImageView imgViewR;
    private int moje_image_index;
    private int left_image_index;
    private int right_image_index;
    int[] images = {R.drawable.p_0,R.drawable.p_3,R.drawable.p_6,R.drawable.p_9,R.drawable.p_12,R.drawable.p_15,R.drawable.p_18,R.drawable.p_21};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        textView2= (TextView)findViewById(R.id.textView2);
        textView4= (TextView)findViewById(R.id.textView4);


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

    public void trzyLinie(View view) {

       // final EditText editTextL = (EditText) findViewById(R.id.editTextL);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
      //  intent.putExtra("punktyJeden", editTextL.getText().toString());
        startActivity(intent);

    };




    public void oblicz(View view) {

        imgViewW = (ImageView)findViewById(R.id.imageViewW);
        imgViewL = (ImageView)findViewById(R.id.imageViewL);
        imgViewR = (ImageView)findViewById(R.id.imageViewR);

        EditText editTextL = (EditText)findViewById(R.id.editTextL);
        if (editTextL.getText().toString().matches("")) {pktLeft=0;}
        else pktLeft = Double.parseDouble(editTextL.getText().toString());
        EditText editTextR = (EditText)findViewById(R.id.editTextR);
        if (editTextR.getText().toString().matches("")) {pktRight=0;}
        else pktRight = Double.parseDouble(editTextR.getText().toString());
        EditText editTextW = (EditText)findViewById(R.id.editTextW);
        if (editTextW.getText().toString().matches("")) {pktWlasne=0;}
        else pktWlasne = Double.parseDouble(editTextW.getText().toString());

        pktMoje = pktLeft + pktRight + pktWlasne;

        if(pktLeft<200){pLeft = p0; left_image_index = 0;}
        else if(pktLeft>=200 && pktLeft<1200){pLeft = p3; left_image_index = 1;}
        else if(pktLeft>=1200 && pktLeft<3600){pLeft = p6; left_image_index = 2;}
        else if(pktLeft>=3600 && pktLeft<7200){pLeft = p9; left_image_index = 3;}
        else if(pktLeft>=7200 && pktLeft<12000){pLeft = p12; left_image_index = 4;}
        else if(pktLeft>=12000 && pktLeft<20400){pLeft = p15; left_image_index = 5;}
        else if(pktLeft>=20400 && pktLeft<30000){pLeft = p18; left_image_index = 6;}
        else if(pktLeft>=30000){pLeft = p21; left_image_index = 7;}

        if(pktRight<200){pRight = p0; right_image_index = 0;}
        else if(pktRight>=200 && pktRight<1200){pRight = p3; right_image_index = 1;}
        else if(pktRight>=1200 && pktRight<3600){pRight = p6; right_image_index = 2;}
        else if(pktRight>=3600 && pktRight<7200){pRight = p9; right_image_index = 3;}
        else if(pktRight>=7200 && pktRight<12000){pRight = p12; right_image_index = 4;}
        else if(pktRight>=12000 && pktRight<20400){pRight = p15; right_image_index = 5;}
        else if(pktRight>=20400 && pktRight<30000){pRight = p18; right_image_index = 6;}
        else if(pktRight>=30000){pRight = p21; right_image_index = 7;}

        if(pktMoje<200){pMoje = p0; moje_image_index = 0;}
        else if(pktMoje>=200 && pktMoje<1200) {pMoje = p3; moje_image_index = 1;}
        else if(pktMoje>=1200 && pktMoje<3600){pMoje = p6; moje_image_index = 2;}
        else if(pktMoje>=3600 && pktMoje<7200){pMoje = p9; moje_image_index = 3;}
        else if(pktMoje>=7200 && pktMoje<12000){pMoje = p12; moje_image_index = 4;}
        else if(pktMoje>=12000 && pktMoje<20400){pMoje = p15; moje_image_index = 5;}
        else if(pktMoje>=20400 && pktMoje<30000){pMoje = p18; moje_image_index = 6;}
        else if(pktMoje>=30000){pMoje = p21; moje_image_index = 7;}

        // wyswietl dane zdjecie
        imgViewW.setImageResource(images[moje_image_index]);
        imgViewL.setImageResource(images[left_image_index]);
        imgViewR.setImageResource(images[right_image_index]);

        prowizja = (pMoje-pLeft)*pktLeft + (pMoje-pRight)*pktRight + pMoje*pktWlasne;
        if(pktLeft>pktRight) {rownowaga = pktLeft/pktMoje*100;}
        else if (pktLeft==0 && pktWlasne==0 || pktRight==0 && pktWlasne==0 ){rownowaga = 100;}
        else {rownowaga = pktRight/pktMoje*100;}

        // zaokroglenie
        prowizja *= 100;
        prowizja = Math.round(prowizja);
        prowizja /= 100;

        rownowaga *=100;
        rownowaga = Math.round(rownowaga);
        rownowaga /=100;

        textView.setText("Prowizja: " + prowizja + " PLN");
        textView2.setText("Równowaga: " + rownowaga + "%");
        textView4.setText("Punkty Grupy: " + pktMoje );



    }
}
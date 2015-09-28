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


public class SecondActivity extends ActionBarActivity {

    double p0 = 0, p3 = 0.03, p6 = 0.06, p9 = 0.09, p12 = 0.12, p15 = 0.15, p18 = 0.18, p21 = 0.21;
    double pJeden=0, pDwa=0, pTrzy=0, pMoje=0; // POZIOMY
    double pktJeden=0, pktDwa=0, pktTrzy=0, pktMoje=1, pktWlasne=0; // pMoje - wszystkie punkty razem
    double prowizja;
    double rownowaga;
    TextView textView;
    TextView textView2;
    TextView textView4;
    private static ImageView imgViewW;
    private static ImageView imgViewJeden;
    private static ImageView imgViewDwa;
    private static ImageView imgViewTrzy;
    private int moje_image_index;
    private int jeden_image_index;
    private int dwa_image_index;
    private int trzy_image_index;
    int[] images = {R.drawable.p_0,R.drawable.p_3,R.drawable.p_6,R.drawable.p_9,R.drawable.p_12,R.drawable.p_15,R.drawable.p_18,R.drawable.p_21};
    EditText editTextJeden;
    EditText editTextDwa;
    EditText editTextTrzy;
    EditText editTextW;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView)findViewById(R.id.textView);
        textView2= (TextView)findViewById(R.id.textView2);
        textView4= (TextView)findViewById(R.id.textView4);


       // editTextJeden.setText(getIntent().getExtras().getString("punktyJeden"));

        /*

        editTextJeden.setText(pktJeden+"");
        editTextDwa.setText(pktDwa+"");
        editTextTrzy.setText(pktTrzy+"");
        editTextW.setText(pktWlasne+"");
*/




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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




    public void dwieLinie(View view) {



        //startActivity(new Intent(SecondActivity.this, MainActivity.class));
        //moveTaskToBack(true);
        finish();

    };




    public void oblicz(View view)

    {

        imgViewW = (ImageView) findViewById(R.id.imageViewW);
        imgViewJeden = (ImageView) findViewById(R.id.imageViewJeden);
        imgViewDwa = (ImageView) findViewById(R.id.imageViewDwa);
        imgViewTrzy = (ImageView) findViewById(R.id.imageViewTrzy);

        EditText editTextJeden = (EditText) findViewById(R.id.editTextJeden);
        if (editTextJeden.getText().toString().matches("")) {pktJeden = 0;}
        else pktJeden = Double.parseDouble(editTextJeden.getText().toString());

        EditText editTextDwa = (EditText) findViewById(R.id.editTextDwa);
        if (editTextDwa.getText().toString().matches("")) {pktDwa = 0;}
        else pktDwa = Double.parseDouble(editTextDwa.getText().toString());

        EditText editTextTrzy = (EditText) findViewById(R.id.editTextTrzy);
        if (editTextTrzy.getText().toString().matches("")) {pktTrzy = 0;}
        else pktTrzy = Double.parseDouble(editTextTrzy.getText().toString());

        EditText editTextW = (EditText) findViewById(R.id.editTextW);
        if (editTextW.getText().toString().matches("")) {pktWlasne = 0;}
        else pktWlasne = Double.parseDouble(editTextW.getText().toString());

        pktMoje = pktJeden + pktDwa + pktTrzy + pktWlasne;

        if(pktJeden<200){pJeden = p0; jeden_image_index = 0;}
        else if(pktJeden>=200 && pktJeden<1200){pJeden = p3; jeden_image_index = 1;}
        else if(pktJeden>=1200 && pktJeden<3600){pJeden = p6; jeden_image_index = 2;}
        else if(pktJeden>=3600 && pktJeden<7200){pJeden = p9; jeden_image_index = 3;}
        else if(pktJeden>=7200 && pktJeden<12000){pJeden = p12; jeden_image_index = 4;}
        else if(pktJeden>=12000 && pktJeden<20400){pJeden = p15; jeden_image_index = 5;}
        else if(pktJeden>=20400 && pktJeden<30000){pJeden = p18; jeden_image_index = 6;}
        else if(pktJeden>=30000){pJeden = p21; jeden_image_index = 7;}

        if(pktDwa<200){pDwa = p0; dwa_image_index = 0;}
        else if(pktDwa>=200 && pktDwa<1200){pDwa = p3; dwa_image_index = 1;}
        else if(pktDwa>=1200 && pktDwa<3600){pDwa = p6; dwa_image_index = 2;}
        else if(pktDwa>=3600 && pktDwa<7200){pDwa = p9; dwa_image_index = 3;}
        else if(pktDwa>=7200 && pktDwa<12000){pDwa = p12; dwa_image_index = 4;}
        else if(pktDwa>=12000 && pktDwa<20400){pDwa = p15; dwa_image_index = 5;}
        else if(pktDwa>=20400 && pktDwa<30000){pDwa = p18; dwa_image_index = 6;}
        else if(pktDwa>=30000){pDwa = p21; dwa_image_index = 7;}

        if(pktTrzy<200){pTrzy = p0; trzy_image_index = 0;}
        else if(pktTrzy>=200 && pktTrzy<1200){pTrzy = p3; trzy_image_index = 1;}
        else if(pktTrzy>=1200 && pktTrzy<3600){pTrzy = p6; trzy_image_index = 2;}
        else if(pktTrzy>=3600 && pktTrzy<7200){pTrzy = p9; trzy_image_index = 3;}
        else if(pktTrzy>=7200 && pktTrzy<12000){pTrzy = p12; trzy_image_index = 4;}
        else if(pktTrzy>=12000 && pktTrzy<20400){pTrzy = p15; trzy_image_index = 5;}
        else if(pktTrzy>=20400 && pktTrzy<30000){pTrzy = p18; trzy_image_index = 6;}
        else if(pktTrzy>=30000){pTrzy = p21; trzy_image_index = 7;}

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
        imgViewJeden.setImageResource(images[jeden_image_index]);
        imgViewDwa.setImageResource(images[dwa_image_index]);
        imgViewTrzy.setImageResource(images[trzy_image_index]);

        prowizja = (pMoje - pJeden) * pktJeden + (pMoje - pDwa) * pktDwa + (pMoje - pTrzy) * pktTrzy + pMoje * pktWlasne;
        if (pktJeden > pktDwa && pktJeden > pktTrzy) {rownowaga = pktJeden / pktMoje * 100;}
        else if(pktDwa > pktJeden && pktDwa > pktTrzy) {rownowaga = pktDwa / pktMoje * 100;}

        else if (pktJeden == 0 && pktDwa == 0 && pktWlasne == 0 || pktJeden == 0 && pktTrzy == 0 && pktWlasne == 0 || pktDwa == 0 && pktTrzy == 0 && pktWlasne == 0)
        {rownowaga = 100;}

        else { rownowaga = pktTrzy / pktMoje * 100;}

        // zaokroglenie
        prowizja *= 100;
        prowizja = Math.round(prowizja);
        prowizja /= 100;

        rownowaga *= 100;
        rownowaga = Math.round(rownowaga);
        rownowaga /= 100;

        textView.setText("Prowizja: " + prowizja + " PLN");
        textView2.setText("Równowaga: " + rownowaga + "%");
        textView4.setText("Punkty Grupy: " + pktMoje);


    }


}

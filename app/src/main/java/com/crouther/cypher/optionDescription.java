package com.crouther.cypher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class optionDescription extends Activity {

    public ImageView wave;
    public TextView optionDescription, optionHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creates Layouts based on Activity Design
        setContentView(R.layout.activity_option_description);

        //Grabs previous activity variables and actions
        Intent intent = getIntent();

        Typeface typeface = ResourcesCompat.getFont(this, R.font.appfont);

        //Grabs current activity variables
        optionHeader = (TextView) findViewById(R.id.option);
        optionHeader.setTypeface(typeface);
        optionDescription = (TextView) findViewById(R.id.description);
        optionDescription.setTypeface(typeface);

        ImageView wave = (ImageView) findViewById(R.id.wave);

        //Sets title and description text based on button press
        final int s = modes.getSelection();
        switch (s){
            case 1:
                optionHeader.setText(R.string.continuous);
                optionDescription.setText(R.string.continuousDescription);
                break;
            case 2:
                optionHeader.setText(R.string.burst);
                optionDescription.setText(R.string.burstDescription);
                break;
            case 3:
                optionHeader.setText(R.string.custom);
                optionDescription.setText(R.string.customDescription);
                break;
        }

        wave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s==3){optionDescription.setText(R.string.customPremium);}
                else {startPlay();}
            }
        });

    }

    //Starts next activity
    public void startPlay() {
        int intMode = modes.getSelection();
        switch(intMode) {
            case 1:
                Intent startGame = new Intent(this, play.class);
                startActivity(startGame);
                break;
            case 2:
                Intent startGameBurst = new Intent(this, burst.class);
                startActivity(startGameBurst);
                break;
            case 3:
                Intent startGameCustom = new Intent(this, play.class);
                startActivity(startGameCustom);
                break;
        }
    }
}

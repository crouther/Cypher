package com.crouther.cypher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class modes extends Activity {
    private static int selection;

    public TextView modes;
    public Button continuous, burst, custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);

        Intent intent = getIntent();

        Typeface typeface = ResourcesCompat.getFont(this, R.font.appfont);
        modes = (TextView) findViewById(R.id.modes);
        modes.setTypeface(typeface);

        continuous = (Button) findViewById(R.id.continuous);
        continuous.setTypeface(typeface);
        burst = (Button) findViewById(R.id.burst);
        burst.setTypeface(typeface);
        custom = (Button) findViewById(R.id.custom);
        custom.setTypeface(typeface);



    }

    public void playContinuous(View view) {
        Intent continuousActivity = new Intent (this, optionDescription.class);
        selection = 1;
        startActivity(continuousActivity);
    }

    public void playBurst(View view) {
        Intent burstActivity = new Intent(this, optionDescription.class);
        selection = 2;
        startActivity(burstActivity);
    }

    public void playCustom(View view) {
        Intent customActivity = new Intent(this, optionDescription.class);
        selection = 3;
        startActivity(customActivity);
    }

    public static int getSelection(){
        return selection;
    }
}

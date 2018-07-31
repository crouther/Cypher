package com.crouther.cypher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.widget.Toast;

import com.ajts.androidmads.fontutils.FontUtils;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class play extends Activity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    ///Activity Variables
    //Widgets
    public ImageView card1, card2, card3, card4, card5, temp, record, start, stop, listen, newRecording, stopMusic;
    public MediaRecorder myAudioRecorder;
    public MediaPlayer mediaPlayer, mp;
    public GestureDetector gestureDetector;
    public TextView optionHeader, songInfo;
    public TextView line, line2, line3, line4, line5, lineTemp;

    public String outputFile;
    final rap rapLyrics = new rap();

    //Primitive Data Values
    int current = 0;

    double defaultCordinates[][] = new double[5][2];
    double lineCoordinates[][] = new double[5][2];

    boolean allowGestures = true;
    boolean allowShare = false;

    ///MAIN METHOD FOR INITIATING THE APPLICATION
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creates Layouts based on Activity Design
        setContentView(R.layout.activity_play);

        //Grabs current activity variables
        card1 = (ImageView) findViewById(R.id.first);
        card2 = (ImageView) findViewById(R.id.second);
        card3 = (ImageView) findViewById(R.id.third);
        card4 = (ImageView) findViewById(R.id.fourth);
        card5 = (ImageView) findViewById(R.id.fifth);

        //Default Location Values
        double[][] defaultCordinates = {
                { card1.getX(), card1.getY()},
                { card2.getX(), card2.getY()},
                { card3.getX(), card3.getY()},
                { card4.getX(), card4.getY()},
                { card5.getX(), card5.getY()}
        };
        temp = card1;

        Typeface typeface = ResourcesCompat.getFont(this, R.font.appfont);

        //Populates Textviews with appropriate content
        optionHeader = (TextView) findViewById(R.id.option);
        optionHeader.setTypeface(typeface);

        line  = (TextView) findViewById(R.id.verse);
        line.setTypeface(typeface);
        line2 = (TextView) findViewById(R.id.verse2);
        line2.setTypeface(typeface);
        line3 = (TextView) findViewById(R.id.verse3);
        line3.setTypeface(typeface);
        line4 = (TextView) findViewById(R.id.verse4);
        line4.setTypeface(typeface);
        line5 = (TextView) findViewById(R.id.verse5);
        line5.setTypeface(typeface);

        songInfo = (TextView) findViewById(R.id.songInfo);
        songInfo.setTypeface(typeface);

        lineTemp = line;

        //Default Location Values
        double[][] lineCoordinates = {
                { line.getX(), line.getY()},
                { line2.getX(), line2.getY()},
                { line3.getX(), line3.getY()},
                { line4.getX(), line4.getY()},
                { line5.getX(), line5.getY()}
        };

        line.setText(R.string.line);
        line2.setText(R.string.line);
        line3.setText(R.string.line);
        line4.setText(R.string.line);
        line5.setText(R.string.line);

        //Creates instances for gesture detection
        this.gestureDetector = new GestureDetector(this,this);
        gestureDetector.setOnDoubleTapListener(this);

        //Sets title text based on button press
        int s = modes.getSelection();
        switch (s){
            case 1:
                optionHeader.setText(R.string.continuous);
                break;
            case 2:
                optionHeader.setText(R.string.burst);
                break;
            case 3:
                optionHeader.setText(R.string.custom);
                break;
        }

        record = (ImageView) findViewById(R.id.record);
        listen = (ImageView) findViewById(R.id.listen);
        stop = (ImageView) findViewById(R.id.stop);
        newRecording = (ImageView) findViewById(R.id.newRecording);
        start = (ImageView) findViewById(R.id.start);
        stopMusic = (ImageView) findViewById(R.id.stopMusic);

        listen.setEnabled(false);
        stop.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    myAudioRecorder = new MediaRecorder();
                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    myAudioRecorder.setOutputFile(outputFile);

                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                    ViewCompat.setElevation(record, 0);
                } catch (IllegalStateException ise){
                    //make something
                } catch (IOException ioe){
                    //make something
                }

                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;

                ViewCompat.setElevation(stop, 0);

                record.setEnabled(true);
                stop.setEnabled(false);
                listen.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio Recording Sucessful", Toast.LENGTH_LONG).show();
            }
        });

        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(outputFile);

                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    ViewCompat.setElevation(listen, 0);
                    songInfo.setText("Tap To Share");
                    allowShare = true;

                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (IOException ioe){
                    //make something
                }
            }
        });

        songInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allowShare){
                    File audio = new File(outputFile);
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("audio/*");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(audio));
                    startActivity(Intent.createChooser(share, "Share Sound File"));
                }
            }
        });

        newRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                ViewCompat.setElevation(record, 6);
                ViewCompat.setElevation(stop, 5);
                ViewCompat.setElevation(listen, 4);
                ViewCompat.setElevation(newRecording, 3);
                songInfo.setText(R.string.song_title_album);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = new MediaPlayer();
                mp = MediaPlayer.create(getApplicationContext(), R.raw.pullup);
                songInfo.setText(R.string.pull_up);
                mp.start();

                ViewCompat.setElevation(start, 0);
                ViewCompat.setElevation(stopMusic, 4);

                Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
            }
        });

        stopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.release();
                    mp = null;
                }

                ViewCompat.setElevation(stopMusic, 0);
                ViewCompat.setElevation(start, 5);
            }
        });
    }

    @Override
    public void onStop() {
        ViewCompat.setElevation(stopMusic, 0);
        ViewCompat.setElevation(start, 5);
        super.onStop();
        if (myAudioRecorder != null) {
            myAudioRecorder.release();
            myAudioRecorder = null;
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    //Method Moves cards to original location
    public void resetCards(){
        //Resets Card Identities
        card1 = (ImageView) findViewById(R.id.first);
        card2 = (ImageView) findViewById(R.id.second);
        card3 = (ImageView) findViewById(R.id.third);
        card4 = (ImageView) findViewById(R.id.fourth);
        card5 = (ImageView) findViewById(R.id.fifth);

        //Randomize Card Colors
        randomizeContent(card1, line);
        randomizeContent(card2, line2);
        randomizeContent(card3, line3);
        randomizeContent(card4, line4);
        randomizeContent(card5, line5);

        //Reset Verse and card positions
        line.setTranslationX((float) lineCoordinates[0][0]);
        line.setTranslationY((float) lineCoordinates[0][1]);
        line2.setTranslationX((float) lineCoordinates[1][0]);
        line2.setTranslationY((float) lineCoordinates[1][1]);
        line3.setTranslationX((float) lineCoordinates[2][0]);
        line3.setTranslationY((float) lineCoordinates[2][1]);
        line4.setTranslationX((float) lineCoordinates[3][0]);
        line4.setTranslationY((float) lineCoordinates[3][1]);
        line5.setTranslationX((float) lineCoordinates[4][0]);
        line5.setTranslationY((float) lineCoordinates[4][1]);

        card1.setTranslationX((float) defaultCordinates[0][0]);
        card1.setTranslationY((float) defaultCordinates[0][1]);
        card2.setTranslationX((float) defaultCordinates[1][0]);
        card2.setTranslationY((float) defaultCordinates[1][1]);
        card3.setTranslationX((float) defaultCordinates[2][0]);
        card3.setTranslationY((float) defaultCordinates[2][1]);
        card4.setTranslationX((float) defaultCordinates[3][0]);
        card4.setTranslationY((float) defaultCordinates[3][1]);
        card5.setTranslationX((float) defaultCordinates[4][0]);
        card5.setTranslationY((float) defaultCordinates[4][1]);
    }

    //Method Randomizes Card Content Starting With Color
    public void randomizeContent(ImageView card, TextView lyric){
        Random rand = new Random();
        int  n = rand.nextInt(10) + 1;

        //Uses randomized n value to set cards new color
        if (n == 1){
            card.setImageResource(R.drawable.red);
            randomizeText(lyric, n);
        }
        else if (n == 2){
            card.setImageResource(R.drawable.orange);
            randomizeText(lyric, n);
        }
        else if (n == 3){
            card.setImageResource(R.drawable.yellow);
            randomizeText(lyric, n);
        }
        else if (n == 4){
            card.setImageResource(R.drawable.green);
            randomizeText(lyric, n);
        }
        else if (n == 5){
            card.setImageResource(R.drawable.teal);
            randomizeText(lyric, n);
        }
        else{
            Random rd = new Random();
            int  y = rand.nextInt(4) + 2;

            switch(y) {
                case 2:
                    card.setImageResource(R.drawable.orange);
                    randomizeText(lyric, y);
                case 3:
                    card.setImageResource(R.drawable.yellow);
                    randomizeText(lyric, y);
                case 4:
                    card.setImageResource(R.drawable.green);
                    randomizeText(lyric, y);
            }
        }
    }

    //Method Randomizes Card Text
    public void randomizeText(TextView tv, int x){
        int size = 1;
        switch(x){
            case 1: size = rapLyrics.getOrignal().size(); break;
            case 2: size = rapLyrics.getFamousQuote().size(); break;
            case 3: size = rapLyrics.getNewSchoolQuote().size(); break;
            case 4: size = rapLyrics.getOldSchoolQuote().size(); break;
            case 5: size = rapLyrics.getSongInspired().size(); break;
        }

        size -= 1;

        Random randTx = new Random();
        int z = randTx.nextInt(size) + 1;

        switch(x){
            case 1: tv.setText(rapLyrics.getOrignal().get(z)); break;
            case 2: tv.setText(rapLyrics.getFamousQuote().get(z)); break;
            case 3: tv.setText(rapLyrics.getNewSchoolQuote().get(z)); break;
            case 4: tv.setText(rapLyrics.getOldSchoolQuote().get(z)); break;
            case 5: tv.setText(rapLyrics.getSongInspired().get(z)); break;
        }

        tv.setTextSize(24);
    }

    ///GESTURE METHODS
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {return false;}

    @Override
    public boolean onDoubleTap(MotionEvent e) {return false;}

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {return false;}

    @Override
    public boolean onDown(MotionEvent e) {return false;}

    @Override
    public void onShowPress(MotionEvent e) {}

    @Override
    public boolean onSingleTapUp(MotionEvent e) {return false;}

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {return false;}

    @Override
    public void onLongPress(MotionEvent e) {}

    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           final float velocityX, final float velocityY) {

        if(allowGestures && e2.getX() > e1.getX()) {
            lineTemp.setTranslationX(e2.getX()*100);
            temp.setTranslationX(e2.getX()*100);

            cardRotation();
        }
        return true;
    }


    //Method counts which card holds the available action
    public void cardRotation(){
        allowGestures = false;
        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        current++;

                        if (current == 0) {temp = card1; lineTemp = line;}
                        if (current == 1) {temp = card2; lineTemp = line2;}
                        if (current == 2) {temp = card3; lineTemp = line3;}
                        if (current == 3) {temp = card4; lineTemp = line4;}
                        if (current == 4) {temp = card5; lineTemp = line5;}
                        if (current == 5) {
                            current = 0;
                            temp = card1;
                            lineTemp = line;
                            play.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    // update UI here
                                    resetCards();
                                }
                            });
                        }
                        allowGestures = true;
                    }
                },
                400
        );
    }
}
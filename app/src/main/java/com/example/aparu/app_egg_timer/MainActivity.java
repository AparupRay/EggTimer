package com.example.aparu.app_egg_timer;

import android.app.Activity;
import android.app.ActivityManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timertextView;
    SeekBar seekBar;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    Button buttonTimer;


    public void resetTimer()
    {
        timertextView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        buttonTimer.setText("START");
        seekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondleft)
    {
         int minutes = (int) secondleft / 60;
         int second = secondleft - minutes * 60;

        String secondstring = Integer.toString(second);

        if(second <=9)
        {
            secondstring = "0" + secondstring;

        }

        timertextView.setText(Integer.toString(minutes) + ":" + secondstring);
    }


    public void controlTimer(View view)
    {
        if(counterIsActive == false) {

            counterIsActive = true;
            seekBar.setEnabled(false);
            buttonTimer.setText("STOP");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                    mplayer.start();

                }
            }.start();
        }
        else{

            resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timertextView = (TextView) findViewById(R.id.timertextView);
        buttonTimer =(Button) findViewById(R.id.buttonTimer);

        timertextView.setText("0:30");
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {

                Log.i("Runnable has run !!","A second must have passed");
                handler.postDelayed(this,1000);

            }
        };

        handler.post(run);
        */


    }
}

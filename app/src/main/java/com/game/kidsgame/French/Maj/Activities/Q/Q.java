package com.game.kidsgame.French.Maj.Activities.Q;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.game.kidsgame.French.Maj.Activities.P.P;
import com.game.kidsgame.French.Maj.ListLettersFrMaj;
import com.game.kidsgame.*;

public class Q extends AppCompatActivity {

    private PaintView paintView;
    ImageView next,previous;
    public static ImageView ops;
    public static ObjectAnimator opsLength,opsHeight,opsDown;
    public static int maxX, maxY;
    private int play;
    private ImageView soundControl;
    private static Activity myActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.game.kidsgame.R.layout.activity_q_fr_maj);
        ImageView home = findViewById(R.id.home);
        myActivity = this;
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        play = 0;
        soundControl = findViewById(R.id.soundControl21);
        soundControl.setImageResource(R.mipmap.sound);
        soundControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play++;
                if(play%2!=0) {
                    soundControl.setImageResource(R.mipmap.nosound);
                    soundControl.refreshDrawableState();
                    SoundService.getPlayer().pause();

                }
                else {
                    soundControl.setImageResource(R.mipmap.sound);
                    soundControl.refreshDrawableState();
                    SoundService.getPlayer().start();

                }
            }
        });
        ops = findViewById(com.game.kidsgame.R.id.ops1);
        paintView  = findViewById(com.game.kidsgame.R.id.paintView);
        next = findViewById(com.game.kidsgame.R.id.next);
        previous = findViewById(com.game.kidsgame.R.id.previous);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.game.kidsgame.French.Maj.Activities.R.R.class));
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), P.class));
            }
        });
    }
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        paintView = findViewById(com.game.kidsgame.R.id.paintView);
        maxX= paintView.getWidth();
        maxY= paintView.getHeight();

    }
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), ListLettersFrMaj.class);
        startActivity(i);
        finish();
    }

    public static void onFailed(){
        MediaPlayer player =  moblibAudioFileManager.getRandomAudioFile(myActivity,"encouragement","FR");
        if(player!=null)
            player.start();
        ops.setTranslationY(-2000f);
        ops.setImageResource(com.game.kidsgame.R.mipmap.essaye1);
        ops.animate().translationYBy(2000f).setDuration(500);
        opsLength =  ObjectAnimator.ofFloat(ops, "scaleX", 0.9f);
        opsHeight = ObjectAnimator.ofFloat(ops, "scaleY", 1.1f);
        opsHeight.setDuration(2000);
        opsLength.setDuration(2000);
        opsHeight.setRepeatCount(ValueAnimator.INFINITE);
        opsLength.setRepeatCount(ValueAnimator.INFINITE);
        opsHeight.setRepeatMode(ValueAnimator.REVERSE);
        opsLength.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet welcomeAnimation = new AnimatorSet();
        welcomeAnimation.play(opsHeight).with(opsLength);
        welcomeAnimation.start();

        opsDown =  ObjectAnimator.ofFloat(ops, "translationY", 2000f);
        opsDown.setDuration(500);
        opsDown.setStartDelay(1500);
        opsDown.start();
    }
    public static void onSuccess(){
        MediaPlayer player =  moblibAudioFileManager.getRandomAudioFile(myActivity,"good","FR");
        if(player!=null)
            player.start();
        ops.setTranslationY(-2000f);
        ops.setImageResource(com.game.kidsgame.R.mipmap.succes);
        ops.animate().translationYBy(2000f).setDuration(500);
        opsLength =  ObjectAnimator.ofFloat(ops, "scaleX", 0.9f);
        opsHeight = ObjectAnimator.ofFloat(ops, "scaleY", 1.1f);
        opsHeight.setDuration(2000);
        opsLength.setDuration(2000);
        opsHeight.setRepeatCount(ValueAnimator.INFINITE);
        opsLength.setRepeatCount(ValueAnimator.INFINITE);
        opsHeight.setRepeatMode(ValueAnimator.REVERSE);
        opsLength.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet welcomeAnimation = new AnimatorSet();
        welcomeAnimation.play(opsHeight).with(opsLength);
        welcomeAnimation.start();

        opsDown =  ObjectAnimator.ofFloat(ops, "translationY", 2000f);
        opsDown.setDuration(500);
        opsDown.setStartDelay(2000);
        opsDown.start();

    }



}

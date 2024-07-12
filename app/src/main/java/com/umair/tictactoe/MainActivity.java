package com.umair.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0-X
    // 1-O
    // 2- Null
    int counter=0;
    int activeplayer = 0;
    static boolean active=true;
    static int[] gameStatus = {2,2,2,2,2,2,2,2,2};

    //all winning position
    static int[][] winpos ={{0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}};

    static String win="";
    public void over(View view){
        if(!active){
            restart(view);
        }
    }
    public void playTap(View view){
        ((TextView)findViewById(R.id.textView2)).setVisibility(View.INVISIBLE);
        ImageView img= (ImageView) view;
        int tappedImage= Integer.parseInt(img.getTag().toString());
        if(!active){
            restart(view);
        }
        else if(gameStatus[tappedImage]==2){
            gameStatus[tappedImage]=activeplayer;
            img.setScaleX(0f);
            img.setScaleY(0f);
            if(activeplayer==0){
                img.setImageResource(R.drawable.x);
                activeplayer=1;
            }
            else{
                img.setImageResource(R.drawable.o);
                activeplayer=0;
            }

            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(img, "scaleX", 1f);
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(img, "scaleY", 1f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
            animatorSet.setDuration(300);
            animatorSet.start();
            if(winner()){

                ((TextView)findViewById(R.id.textView2)).setText(win);
                ((TextView)findViewById(R.id.textView2)).setVisibility(View.VISIBLE);
            }
            else if(counter==8){
                active=false;
                ((TextView)findViewById(R.id.textView2)).setText("Game Draw Tap To Reset");
                ((TextView)findViewById(R.id.textView2)).setVisibility(View.VISIBLE);
            }
            counter++;
        }
    }
    void restart(View view)
    {
        gameStatus = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        active=true;
        ((TextView)findViewById(R.id.textView2)).setText("Tap To Play As X");
        ((TextView)findViewById(R.id.textView2)).setVisibility(View.VISIBLE);

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        win="";
        counter =0;
        activeplayer = 0;
    }
    static boolean winner(){
        for(int[] Pos:winpos){
            if(gameStatus[Pos[0]]!=2&&gameStatus[Pos[0]]==gameStatus[Pos[1]]&&gameStatus[Pos[1]]==gameStatus[Pos[2]]){
                if(gameStatus[Pos[0]]==0){
                    win="X Won Tap To Reset";

                }
                else{
                    win="O Won Tap To Reset";
                }
                active=false;
                return true;
            }
        }

        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
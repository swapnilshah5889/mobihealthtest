package com.example.mobihealthtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_animate,btn2;
    boolean b_show = false;
    ImageView img_test;
    ConstraintLayout cc1;



    float start = 0f,stop = -90f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitObjects();
    }

    private void InitObjects() {

        img_test = findViewById(R.id.img_test);

        Animation topUp = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.top_up);

        img_test.startAnimation(topUp);
        btn_animate = findViewById(R.id.btn_animate);
        btn2 = findViewById(R.id.btn2);

        cc1 = findViewById(R.id.cc1);

        btn_animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!b_show) {
                    ShowAnimattion();
                    b_show = !b_show;
                }
                else{
                    ReverAnimation();
                    b_show = !b_show;
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator rotate = ObjectAnimator.ofFloat(img_test, "rotation", start, stop);
                //Toast.makeText(MainActivity.this, stop+"->"+start, Toast.LENGTH_SHORT).show();
                start-=90f;
                stop-=90f;/*
                start = start%360f;
                stop = stop%360f;*/
                rotate.setDuration(1000);
//        rotate.setRepeatCount(10);
                rotate.start();
            }
        });
    }

    private void ReverAnimation() {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_main);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(500);

        TransitionManager.beginDelayedTransition(cc1, transition);
        constraintSet.applyTo(cc1);

    }

    private void ShowAnimattion() {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_main_animated);
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(500);

        TransitionManager.beginDelayedTransition(cc1, transition);
        constraintSet.applyTo(cc1);

        /*RotateAnimation rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new AnticipateOvershootInterpolator());
        img_test.startAnimation(rotate);*/





    }


}

package com.example.mobihealthtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.transition.TransitionManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Recolor;

public class CooCoLogin extends AppCompatActivity {

    //Main container
    ConstraintLayout ll_container_coco_login;

    //Forgot Password
    TextView tv_forgot_pass_coco;
    int fp_height = 0;

    //Sign in/up button
    LinearLayout ll_arrow_image_coco;
    ConstraintLayout cl_signin_reg_button;
    Boolean b_is_reg = true;
    TextSwitcher tv_sign_in_coco;

    //sign in/register text
    TextView tv_login_reg_pretext_coco,tv_login_reg_coco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coo_co_login);


        InitObjects();
    }

    private void InitObjects() {

        ll_container_coco_login = findViewById(R.id.ll_container_coco_login);
        ll_arrow_image_coco = findViewById(R.id.ll_arrow_image_coco);
        cl_signin_reg_button = findViewById(R.id.cl_signin_reg_button);


        tv_forgot_pass_coco = findViewById(R.id.tv_forgot_pass_coco);
        fp_height = tv_forgot_pass_coco.getHeight();


        tv_login_reg_pretext_coco = findViewById(R.id.tv_login_reg_pretext_coco);

        tv_login_reg_coco = findViewById(R.id.tv_login_reg_coco);

        tv_sign_in_coco = findViewById(R.id.tv_sign_in_coco);
        tv_sign_in_coco.setInAnimation(this,android.R.anim.fade_in);
        tv_sign_in_coco.setOutAnimation(this,android.R.anim.fade_out);

        OnClickMethods();

    }

    private void OnClickMethods() {

        tv_login_reg_coco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransitionDrawable transition = (TransitionDrawable) cl_signin_reg_button.getBackground();

                TransitionDrawable transition_img = (TransitionDrawable) ll_arrow_image_coco.getBackground();

                if(!b_is_reg) {

                    TransitionManager.beginDelayedTransition(ll_container_coco_login,
                            new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
                    tv_login_reg_pretext_coco.setText("Already have an account? ");
                    tv_login_reg_coco.setText("Log In");
                    tv_sign_in_coco.setText("Sign Up");
                    transition.reverseTransition(300);
                    transition_img.reverseTransition(300);
                    //tv_forgot_pass_coco.setVisibility(View.VISIBLE);
                    tv_forgot_pass_coco.animate()
                            .translationY(fp_height)
                            .alpha(1.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tv_forgot_pass_coco.setVisibility(View.VISIBLE);
                                }
                            });
                    b_is_reg = !b_is_reg;
                }
                else{

                    TransitionManager.beginDelayedTransition(ll_container_coco_login,
                            new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
                    tv_login_reg_pretext_coco.setText("Create a new ");
                    tv_login_reg_coco.setText("account");
                    tv_sign_in_coco.setText("Sign In");
                    transition.startTransition(300);
                    transition_img.startTransition(300);
                    //tv_forgot_pass_coco.setVisibility(View.GONE);
                    //tv_forgot_pass_coco.animate().translationY(fp_height);

                    AnimateMainLayout();

                    tv_forgot_pass_coco.animate()
                            .translationY(tv_forgot_pass_coco.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tv_forgot_pass_coco.setVisibility(View.GONE);
                                }
                            });
                    b_is_reg = !b_is_reg;
                }

                //Toast.makeText(CooCoLogin.this, "t", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void AnimateMainLayout() {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_main_animated);
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(500);

    }
}

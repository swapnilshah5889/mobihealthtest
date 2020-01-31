package com.example.mobihealthtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Recolor;

import java.util.ArrayList;
import java.util.List;

public class CooCoLogin extends AppCompatActivity {

    //Main container
    ConstraintLayout ll_container_coco_login;

    LinearLayout ll_extra_layout;


    //ViewPager
    LinearLayout ll_viewpager_container;
    ViewPager vp_coco_login;
    TabLayout tb_coco_login;
    Boolean b_viewpager = false;
    CooCoLoginAdapger adapter;
    List<CooCoLoginModel> pages;
    Integer[] colors = {R.color.viewpager_background,R.color.viewpager_background2, R.color.viewpager_background3};
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    //Forgot Password
    TextView tv_forgot_pass_coco;
    int fp_height = 0;
    LinearLayout ll_forgot_password;


    //Input
    LinearLayout ll_mobile_number_coco,ll_full_name_coco,ll_password_coco,ll_cpassword_coco,ll_dob_coco;

    //Sign in/up button
    LinearLayout ll_arrow_image_coco;
    ConstraintLayout cl_signin_reg_button;
    Boolean b_is_reg = true;
    TextSwitcher tv_sign_in_coco;

    //sign in/register text
    TextView tv_login_reg_pretext_coco,tv_login_reg_coco;
    private int Animation_Duration = 700;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coo_co_login);


        InitObjects();
    }

    private void InitObjects() {

        ll_extra_layout = findViewById(R.id.ll_extra_layout);

        ll_mobile_number_coco = findViewById(R.id.ll_mobile_number_coco);
        ll_full_name_coco = findViewById(R.id.ll_full_name_coco);
        ll_password_coco = findViewById(R.id.ll_password_coco);
        ll_cpassword_coco = findViewById(R.id.ll_cpassword_coco);
        ll_dob_coco = findViewById(R.id.ll_dob_coco);
        ll_forgot_password = findViewById(R.id.ll_forgot_password);



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


        ll_viewpager_container = findViewById(R.id.ll_viewpager_container);
        vp_coco_login = findViewById(R.id.vp_coco_login);
        tb_coco_login = findViewById(R.id.tb_coco_login);
        SetViewPager();

        OnClickMethods();

    }

    private void SetViewPager() {

        pages = new ArrayList<>();

        pages.add(new CooCoLoginModel("CoCoo",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut",
                R.drawable.ic_logo3));

        pages.add(new CooCoLoginModel("CoCoo 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut",
                R.drawable.ic_logo));

        pages.add(new CooCoLoginModel("CoCoo 2",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut",
                R.drawable.ic_logo2));

        adapter = new CooCoLoginAdapger(this,pages);



        vp_coco_login.setAdapter(adapter);
        vp_coco_login.setOffscreenPageLimit(3);
        vp_coco_login.setCurrentItem(0);

        vp_coco_login.setPageMargin(20);

        /*vp_coco_login.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount() -1) && position < (colors.length - 1)) {

                    ll_extra_layout.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));

                } else {

                    ll_extra_layout.setBackgroundColor(colors[colors.length - 1]);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        tb_coco_login.setupWithViewPager(vp_coco_login);

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
                    tv_login_reg_pretext_coco.setText("Already have an account ?  ");
                    tv_login_reg_coco.setText("Log In  ");
                    tv_sign_in_coco.setText("Sign Up");
                    transition.reverseTransition(Animation_Duration);
                    transition_img.reverseTransition(Animation_Duration);
                    //tv_forgot_pass_coco.setVisibility(View.VISIBLE);
                    /*tv_forgot_pass_coco.animate()
                            .translationY(fp_height)
                            .alpha(1.0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tv_forgot_pass_coco.setVisibility(View.VISIBLE);
                                }
                            });*/

                    AnimateMainLayout();
                    FadeComponents(b_is_reg);
                    //AnimateViewPager();
                    b_is_reg = !b_is_reg;
                }
                else{


                    TransitionManager.beginDelayedTransition(ll_container_coco_login,
                            new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
                    tv_login_reg_pretext_coco.setText("Create a new  ");
                    tv_login_reg_coco.setText("account  ");
                    tv_sign_in_coco.setText("Sign In");
                    transition.startTransition(Animation_Duration);
                    transition_img.startTransition(Animation_Duration);
                    //tv_forgot_pass_coco.setVisibility(View.GONE);
                    //tv_forgot_pass_coco.animate().translationY(fp_height);

                    AnimateMainLayout();

                    /*tv_forgot_pass_coco.animate()
                            .translationY(tv_forgot_pass_coco.getHeight())
                            .alpha(0.0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tv_forgot_pass_coco.setVisibility(View.GONE);
                                }
                            });*/
                    //AnimateViewPager();

                    FadeComponents(b_is_reg);


                    b_is_reg = !b_is_reg;
                }

                //Toast.makeText(CooCoLogin.this, "t", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void FadeComponents(boolean isvisible) {

       /* transitions.FadeInOut2(ll_cpassword_coco,isvisible,this,Animation_Duration);
        transitions.FadeInOut2(ll_dob_coco,isvisible,this,Animation_Duration);
        transitions.FadeInOut2(ll_full_name_coco,isvisible,this,Animation_Duration);

        if(isvisible)
            transitions.FadeInOut2(ll_forgot_password,!isvisible,this,(Animation_Duration-200));
        else
            transitions.FadeInOut2(ll_forgot_password,!isvisible,this,(Animation_Duration-200));
*/
       if(!isvisible)
            transitions.FadeInOut2(ll_viewpager_container,!isvisible,CooCoLogin.this,300);
       else
           transitions.FadeInOut2(ll_viewpager_container,!isvisible,CooCoLogin.this,1300);
    }


    private void AnimateMainLayout() {


        ConstraintSet constraintSet = new ConstraintSet();
        if(b_is_reg){
            constraintSet.clone(this, R.layout.activity_coo_co_login_animated);
        }
        else{
            constraintSet.clone(this, R.layout.activity_coo_co_login);
        }
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new DecelerateInterpolator(1.0f));
        transition.setDuration(Animation_Duration);

        android.transition.TransitionManager.beginDelayedTransition(ll_container_coco_login, transition);
        constraintSet.applyTo(ll_container_coco_login);

        /*Transition transition = new Fade();
        transition.setDuration(300);
        transition.addTarget(R.id.ll_mobile_number_coco);
        ViewGroup parent = findViewById(R.id.ll_container_coco_login);
        TransitionManager.beginDelayedTransition(parent, transition);
        ll_mobile_number_coco.setVisibility(b_is_reg ? View.GONE : View.VISIBLE);*/

    }


}

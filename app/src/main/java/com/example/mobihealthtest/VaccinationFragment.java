package com.example.mobihealthtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class VaccinationFragment extends Fragment{

    RecyclerView rv_vaccines;
    Context context;
    View view;
    FragmentActivity parent;
    int previous_selected;
    ViewPager vp_cards;
    TabLayout tl_card_tabs;
    List<List<Integer>> AllCards;
    int card_position = 0;

    private Animator currentAnimator;
    private int shortAnimationDuration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_vaccination, container, false);
        context = container.getContext();
        InitializeObjects();
        return view;
    }


    public VaccinationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    private void InitializeObjects() {

        previous_selected = 0;
        parent = getActivity();
        //Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
        rv_vaccines = view.findViewById(R.id.rv_vaccines);
        vp_cards = view.findViewById(R.id.vp_cards);
        tl_card_tabs = view.findViewById(R.id.tl_card_tabs);
        tl_card_tabs.setupWithViewPager(vp_cards, true);
        vp_cards.setClipToPadding(false);
        SetTabRecyclerView();

        SetVaccineCards();
    }

    private void SetVaccineCards() {

        AllCards = new ArrayList<>();
        List<Integer> cards = new ArrayList<>();
        cards.add(R.drawable.one);
        cards.add(R.drawable.two);
        cards.add(R.drawable.three);
        AllCards.add(cards);
        cards = new ArrayList<>();
        cards.add(R.drawable.four);
        cards.add(R.drawable.five);
        cards.add(R.drawable.six);
        AllCards.add(cards);
        cards = new ArrayList<>();
        cards.add(R.drawable.seven);
        cards.add(R.drawable.eight);
        cards.add(R.drawable.nine);
        cards.add(R.drawable.ten);
        AllCards.add(cards);

        SetCardRecylerView(AllCards.get(0));

    }

    private void SetCardRecylerView(final List<Integer> cardList) {
        final CardAdapterMain mCardAdapterMain = new CardAdapterMain(context, cardList, new CardAdapterMain.OnPageClickListner() {
            @Override
            public void onPageClick(int position) {
                List<Integer> cardlist = AllCards.get(card_position);
                //cardList.get(position);
                /*Animation animation = AnimationUtils.loadAnimation(context,
                        R.anim.zoom_in);
                vp_cards.startAnimation(animation);*/

                CardAdapterMain adapter = (CardAdapterMain) vp_cards.getAdapter();
                CardView fragment = adapter.getCardViewAt(vp_cards.getCurrentItem());

                //View activeView = fragment.getView();
                zoomImageFromThumb(fragment, cardList.get(position));

            }
        });

        ShadowTransformer shadowTransformer = new ShadowTransformer(vp_cards,mCardAdapterMain);
        shadowTransformer.enableScaling(true);
        vp_cards.setPageMargin(60);
        vp_cards.setAdapter(mCardAdapterMain);
        vp_cards.setPageTransformer(false,shadowTransformer);
        vp_cards.setOffscreenPageLimit(3);

    }

    private void SetTabRecyclerView() {

        List<String> titles =new ArrayList<>();
        titles.add("0 Birth");
        titles.add("6 Weeks");
        titles.add("10 Weeks");
        titles.add("14 Weeks");
        titles.add("6 Months");
        titles.add("9 Months");
        titles.add("12 Months");
        titles.add("15 Months");
        titles.add("18 Months");
        titles.add("2 Years");
        titles.add("4 Years");
        titles.add("11 Years");
        titles.add("11 Years(2M)");
        titles.add("11 Years(6M)");
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv_vaccines);
        TabAdapter adapter = new TabAdapter(context, titles, new TabAdapter.OnItemClickListner() {
            @Override
            public void onTabClick(final int position) {
                //Toast.makeText(context, position+" clicked", Toast.LENGTH_SHORT).show();
                //rv_vaccines.scrollToPosition(position);
                //RecyclerView.LayoutManager llm = (LinearLayoutManager)rv_vaccines.getLayoutManager();
                int p = position%3;
                rv_vaccines.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rv_vaccines.smoothScrollToPosition(position);
                    }
                }, 500);
                SetCardRecylerView(AllCards.get(p));
                card_position = p;

                AnimateText();
            }
        });

        rv_vaccines.setAdapter(adapter);

    }

    private void AnimateText() {



    }






















    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) view.findViewById(
                R.id.img_expanded);
        expandedImageView.setImageResource(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        view.findViewById(R.id.frame_vaccination)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(300);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
            }
        });
        set.start();
        currentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnimator != null) {
                    currentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(300);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }
                });
                set.start();
                currentAnimator = set;
            }
        });
    }

}

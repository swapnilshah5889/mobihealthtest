package com.example.mobihealthtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CocooLoginVpAdapter extends PagerAdapter implements CardAdapter {

    Context context;
    List<CooCoLoginModel> pages;
    private ArrayList<CardView> mViews = new ArrayList<>();
    List<Integer> cards;
    private float mBaseElevation;
    private OnPageClickListner listner;

    public CocooLoginVpAdapter(Context context,  List<CooCoLoginModel> pages, OnPageClickListner listner) {
        this.context = context;
        this.pages = pages;
        this.listner = listner;
        addCards();
    }

    private void addCards() {
        mViews.clear();
        for (int i = 0; i < cards.size(); i++) {
            mViews.add(null);
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.card_layout, container, false);


        final ImageView img_card = view.findViewById(R.id.img_card);
        img_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onPageClick(position);
            }
        });
        img_card.setImageResource(cards.get(position));
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        container.addView(view);
        mViews.set(position, cardView);
        return view;
    }

    public interface OnPageClickListner{
        public void onPageClick(int position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }
}

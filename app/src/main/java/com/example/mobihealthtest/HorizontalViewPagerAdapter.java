package com.example.mobihealthtest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HorizontalViewPagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    CardsModel cardsModel;

    public HorizontalViewPagerAdapter(Context context,@NonNull FragmentManager fm, CardsModel cardsModel) {
        super(fm);
        this.context = context;
        this.cardsModel = cardsModel;
    }



    @Override
    public Fragment getItem(int position) {
        ChildFragment childFragment = new ChildFragment(context,cardsModel.getCards().get(position));
        Bundle bundle = new Bundle();

        bundle.putInt("card",cardsModel.getCards().get(position));
        childFragment.setArguments(bundle);
        return childFragment;
    }

    @Override
    public int getCount() {
        return cardsModel.getCards().size();
    }
}

package com.example.mobihealthtest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.Serializable;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    List<CardsModel> cardsList;

    public ViewPagerAdapter(Context context,FragmentManager fm,List<CardsModel> cardsList) {
        super(fm);
        this.cardsList = cardsList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return cardsList.size();
    }

    @Override
    public Fragment getItem(int position) {
        ContentFragment contentFragment = new ContentFragment(context);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cards",(Serializable) cardsList.get(position));
        contentFragment.setArguments(bundle);
        return contentFragment;
    }
}

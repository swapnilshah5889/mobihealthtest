package com.example.mobihealthtest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class ContentFragment extends Fragment {
    public HorizontalViewPager viewPager;
    public HorizontalViewPagerAdapter horizontalViewPagerAdapter;

    Context context;
    CardsModel cardsModelList;
    TabLayout tl_card_tabs_expanded;

    public ContentFragment() {
        // Required empty public constructor
    }

    public ContentFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);


        cardsModelList = (CardsModel)getArguments().getSerializable("cards");

        viewPager = (HorizontalViewPager) view.findViewById(R.id.vpHorizontal);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setPageMargin(30);
        horizontalViewPagerAdapter = new HorizontalViewPagerAdapter(context,getChildFragmentManager(),cardsModelList);
        viewPager.setAdapter(horizontalViewPagerAdapter);
        //Helper.log("Card Created : " + parentInd);
        viewPager.setCurrentItem(0);
        tl_card_tabs_expanded = (TabLayout) view.findViewById(R.id.tl_card_tabs_expanded);
        tl_card_tabs_expanded.setupWithViewPager(viewPager, true);
        return view;
    }
}

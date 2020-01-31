package com.example.mobihealthtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CooCoLoginAdapger extends PagerAdapter{

    Context context;
    List<CooCoLoginModel> pages;
    private ArrayList<LinearLayout> mViews = new ArrayList<>();
    private float mBaseElevation;

    public CooCoLoginAdapger(Context context,List<CooCoLoginModel> pages) {
        this.context = context;
        this.pages = pages;
        addCards();
    }

    private void addCards() {
        mViews.clear();
        for (int i = 0; i < pages.size(); i++) {
            mViews.add(null);
        }
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CooCoLoginModel page = pages.get(position);
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.fragment_cooco_login, container, false);
        LinearLayout ll_page_coco_login = view.findViewById(R.id.ll_page_coco_login);
        ImageView img_page_icon = (ImageView) view.findViewById(R.id.img_page_icon);
        TextView tv_page_description = (TextView) view.findViewById(R.id.tv_page_description);
        TextView tv_page_header = (TextView) view.findViewById(R.id.tv_page_header);

        tv_page_header.setText(page.getTitle());
        tv_page_description.setText(page.getMessage());
        Picasso.with(context).load(page.getImage()).into(img_page_icon);
        //img_card.setImageResource(card);

        container.addView(view);
        mViews.set(position, ll_page_coco_login);
        return view;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        pages.set(position, null);
    }
}

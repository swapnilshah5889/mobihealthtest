package com.example.mobihealthtest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ChildFragment extends Fragment {


    Context context;
    public int card;
    public ImageView img_card;

    public ChildFragment() {
        // Required empty public constructor
    }

    public ChildFragment(Context context, int card) {
        this.context = context;
        this.card = card;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        // Inflate the layout for this fragment
        img_card = (ImageView) view.findViewById(R.id.img_card);
        Bundle bundle = getArguments();

        Picasso.with(context).load(card).into(img_card);
        //img_card.setImageResource(card);
        return view;
    }

}

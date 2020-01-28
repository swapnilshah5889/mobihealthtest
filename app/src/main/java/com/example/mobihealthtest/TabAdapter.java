package com.example.mobihealthtest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter  extends RecyclerView.Adapter<TabAdapter.MyViewHolder> {

    Context context;
    List<String> titles;
    private int checkedPosition = 0;
    OnItemClickListner listner;

    public TabAdapter(Context context, List<String> titles,OnItemClickListner listner) {
        this.context = context;
        this.titles = titles;
        this.listner = listner;
    }

    public void setTitles(List<String> titles){
        this.titles = new ArrayList<>();
        this.titles = titles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccine_tablayout,parent , false);
        return new TabAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        //holder.setIsRecyclable(false);

        holder.bind(titles.get(position),listner);

    }


    public interface OnItemClickListner{
        public void onTabClick(int position);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        LinearLayout ll_tab_layout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            ll_tab_layout = view.findViewById(R.id.ll_tab_layout);
            tv_title = view.findViewById(R.id.tv_title);
        }

        void bind(final String title, final  OnItemClickListner listner){
            if(checkedPosition  == -1){
                ll_tab_layout.setBackgroundResource(R.drawable.tab_background);
                ll_tab_layout.setElevation(0f);
                ll_tab_layout.setTranslationZ(0f);
                tv_title.setTextColor(Color.parseColor("#ffffff"));
            }
            else{
                if(checkedPosition == getAdapterPosition()){
                    ll_tab_layout.setBackgroundResource(R.drawable.tab_background_selected);
                    ll_tab_layout.setElevation(20f);
                    ll_tab_layout.setTranslationZ(5f);
                    tv_title.setTextColor(Color.parseColor("#005177"));

                }
                else{
                    ll_tab_layout.setBackgroundResource(R.drawable.tab_background);
                    ll_tab_layout.setElevation(0f);
                    ll_tab_layout.setTranslationZ(0f);
                    tv_title.setTextColor(Color.parseColor("#ffffff"));

                }
            }

            tv_title.setText(title);

            ll_tab_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ll_tab_layout.setBackgroundResource(R.drawable.tab_background_selected);
                    ll_tab_layout.setElevation(20f);
                    ll_tab_layout.setTranslationZ(5f);
                    tv_title.setTextColor(Color.parseColor("#005177"));
                    if(checkedPosition != getAdapterPosition()){
                        notifyItemChanged(checkedPosition);
                        //notifyDataSetChanged();
                        checkedPosition = getAdapterPosition();
                        listner.onTabClick(checkedPosition);
                    }
                }
            });


        }
    }

    public String getTitleSelected(){
        if(checkedPosition != -1){
            return  titles.get(checkedPosition);
        }
        return  null;
    }


}

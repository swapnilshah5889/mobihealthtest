package com.example.mobihealthtest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainTabAdapter extends RecyclerView.Adapter<MainTabAdapter.MyViewHolder> {

    Context context;
    List<String> titles;
    private int checkedPosition = 0;
    OnItemClickListner listner;

    public MainTabAdapter(Context context, List<String> titles, OnItemClickListner listner) {
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
                .inflate(R.layout.maintab_layout,parent , false);
        return new MainTabAdapter.MyViewHolder(itemView);
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

        TextView tv_maintab;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tv_maintab = view.findViewById(R.id.tv_maintab);
        }

        void bind(final String title, final  OnItemClickListner listner){
            if(checkedPosition  == -1){

                tv_maintab.setTextColor(Color.parseColor("#005177"));
            }
            else{
                if(checkedPosition == getAdapterPosition()){

                    tv_maintab.setTextColor(Color.parseColor("#ffffff"));

                }
                else{

                    tv_maintab.setTextColor(Color.parseColor("#005177"));

                }
            }

            tv_maintab.setText(title);

            tv_maintab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    tv_maintab.setTextColor(Color.parseColor("#ffffff"));
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

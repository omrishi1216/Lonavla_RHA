package com.example.lonavlarha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private ArrayList<MyModel> dataSet;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textViewName;
            TextView textViewVersion;
            ImageView imageViewIcon;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.imageViewIcon = itemView.findViewById(R.id.image1);
            }
        }

        public MyAdapter(ArrayList<MyModel> data) {
            this.dataSet = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.upper_row, parent, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

            TextView textViewName = holder.textViewName;
            TextView textViewVersion = holder.textViewVersion;
            ImageView imageView = holder.imageViewIcon;


            imageView.setImageResource(dataSet.get(listPosition).getImage());
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

    }


package com.example.lonavlarha;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Three_Fragments extends Two_Fragments {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        ImageView textView = v.findViewById(R.id.image3);
        textView.setImageResource(R.drawable.rharmy_img);
        return v;
    }
}

package com.example.lonavlarha;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Two_Fragments extends One_Fragments {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        ImageView textView = v.findViewById(R.id.image2);
        textView.setImageResource(R.drawable.rha_img4);
        return v;
    }
}

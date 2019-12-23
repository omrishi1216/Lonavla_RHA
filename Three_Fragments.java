package com.example.lonavlarha;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Three_Fragments extends One_Fragments {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        TextView textView = v.findViewById(R.id.image3);
        textView.setText("First Fragment");
        return v;
    }
}

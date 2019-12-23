package com.example.lonavlarha;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class One_Fragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        ImageView textView = v.findViewById(R.id.image1);
        textView.setImageResource(R.drawable.rha_img);
        return v;
    }
}

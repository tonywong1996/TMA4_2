package com.example.tma4_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class AboutFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvId = view.findViewById(R.id.tvId);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_rotate);
        tvName.startAnimation(animation);
        tvId.startAnimation(animation);


        return view;
    }
}
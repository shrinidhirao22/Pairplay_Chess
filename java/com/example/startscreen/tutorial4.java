package com.example.startscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class tutorial4 extends Fragment {

    protected View view;
    protected ImageButton move_previous, move_next;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fourth_frag, container, false);
        move_previous = view.findViewById(R.id.imageButton1);
        move_next = view.findViewById(R.id.imageButton2);
        move_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorial3 fragment = new tutorial3();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.commit();
            }
        });
        move_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorial5 fragment = new tutorial5();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.commit();
            }
        });
        return view;
    }
}
package com.example.chelseafc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chelseafc.R;

public class PhotosFragment extends Fragment {

    ViewFlipper viewFlipperImg, viewFlipperTxt;
    View view;
    Animation in,out;
    Button btnNext, btnPrevious;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photos, container, false);
        init();
        setViewFlipperImg();
        setViewFlipperTxt();
        setListener();

        return view;
    }

    private void setViewFlipperImg() {
        viewFlipperImg = view.findViewById(R.id.viewFlipperImg);
        in = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        viewFlipperImg.setInAnimation(in);
        viewFlipperImg.setOutAnimation(out);
        viewFlipperImg.setFlipInterval(5000);
        viewFlipperImg.setAutoStart(true);
    }

    private void setViewFlipperTxt() {
        viewFlipperTxt = view.findViewById(R.id.viewFlipperTxt);
        viewFlipperTxt.setFlipInterval(5000);
        viewFlipperTxt.setAutoStart(true);
    }

    private void init(){
        btnNext = view.findViewById(R.id.btnNext);
        btnPrevious = view.findViewById(R.id.btnPrevious);
    }

    private void setListener(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewFlipperImg.isAutoStart() && viewFlipperTxt.isAutoStart()){
                    viewFlipperImg.stopFlipping();
                    viewFlipperImg.showNext();
                    viewFlipperImg.startFlipping();
                    viewFlipperImg.setAutoStart(true);
                    viewFlipperTxt.stopFlipping();
                    viewFlipperTxt.showNext();
                    viewFlipperTxt.startFlipping();
                    viewFlipperTxt.setAutoStart(true);
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewFlipperImg.isAutoStart() && viewFlipperTxt.isAutoStart()){
                    viewFlipperImg.stopFlipping();
                    viewFlipperImg.showPrevious();
                    viewFlipperImg.startFlipping();
                    viewFlipperImg.setAutoStart(true);
                    viewFlipperTxt.stopFlipping();
                    viewFlipperTxt.showPrevious();
                    viewFlipperTxt.startFlipping();
                    viewFlipperTxt.setAutoStart(true);
                }
            }
        });
    }
}

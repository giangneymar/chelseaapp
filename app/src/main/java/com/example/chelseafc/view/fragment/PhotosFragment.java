package com.example.chelseafc.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.chelseafc.R;
import com.example.chelseafc.databinding.FragmentPhotosBinding;
import com.example.chelseafc.model.Photo;
import com.example.chelseafc.view.adapter.PhotoAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment {

    private FragmentPhotosBinding binding;
    private List<Photo> photos;
    private PhotoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (photos == null) {
            photos = new ArrayList<>();
        }
        getListPhoto();
        adapter = new PhotoAdapter(getContext(),getListPhoto());
        binding.viewpager.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.viewpager);
        adapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());

    }

    private List<Photo> getListPhoto() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("photos");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Photo photo = snapshot.getValue(Photo.class);
                if (photo != null) {
                    photos.add(photo);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return photos;
    }

}

package com.example.chelseafc.Fragment;

import static com.example.chelseafc.MainActivity.FRAGMENT_CHANGE_PASSWORD;
import static com.example.chelseafc.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.chelseafc.MainActivity;
import com.example.chelseafc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

public class MyProfileFragment extends Fragment {

    private View view;
    private ImageView imgAvatar;
    private EditText edtNickname, edtEmail;
    private Button btnUpdateProfile;
    private ProgressBar progressbar;
    private Uri uri;
    private MainActivity mainActivity;
    private TextView txtChangePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        init();
        mainActivity = (MainActivity) getActivity();
        setUserInformation();
        setListener();

        return view;
    }

    private void init() {
        imgAvatar = view.findViewById(R.id.imgAvatar);
        edtNickname = view.findViewById(R.id.edtNickname);
        edtEmail = view.findViewById(R.id.edtEmail);
        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        progressbar = view.findViewById(R.id.progressbar);
        txtChangePassword = view.findViewById(R.id.txtChangePassword);
    }

    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        edtNickname.setText(user.getDisplayName());
        edtEmail.setText(user.getEmail());
        Picasso.get().load(user.getPhotoUrl()).error(R.drawable.avatar_default).into(imgAvatar);
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    private void setListener() {
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading(true);
                String nickName = edtNickname.getText().toString();
                String email = edtEmail.getText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(nickName)
                        .setPhotoUri(uri)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    loading(false);
                                    showToast("Cập Nhật Thành Công");
                                    mainActivity.getUserInformation();
                                }
                            }
                        });

                user.updateEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    loading(false);
                                    showToast("Cập Nhật Thành Công");
                                    mainActivity.getUserInformation();
                                }
                            }
                        });

            }
        });

        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainActivity.currentFragment != FRAGMENT_CHANGE_PASSWORD) {
                    mainActivity.replaceFragment(new ChangePasswordFragment());
                    mainActivity.currentFragment = FRAGMENT_CHANGE_PASSWORD;
                }
            }
        });
    }

    public void setBitmapImageView(Bitmap bitmapImageView){
        imgAvatar.setImageBitmap(bitmapImageView);
    }

    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        }
        else {
            String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permissions, MY_REQUEST_CODE);
        }
    }


    private void loading(Boolean isLoading) {
        if (isLoading) {
            btnUpdateProfile.setVisibility(View.INVISIBLE);
            progressbar.setVisibility(View.VISIBLE);
        } else {
            progressbar.setVisibility(View.INVISIBLE);
            btnUpdateProfile.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

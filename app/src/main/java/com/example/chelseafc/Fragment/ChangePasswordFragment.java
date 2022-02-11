package com.example.chelseafc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chelseafc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordFragment extends Fragment {

    private EditText edtNewPassword, edtRePassword;
    private Button btnUpdatePassword, btnLogin;
    private ProgressBar progressBar;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

        init();
        setListener();

        return view;
    }

    private void init() {
        edtNewPassword = view.findViewById(R.id.edtNewPassword);
        edtRePassword = view.findViewById(R.id.edtRePassword);
        btnUpdatePassword = view.findViewById(R.id.btnUpdatePassword);
        btnLogin = view.findViewById(R.id.btnLogIn);
        progressBar = view.findViewById(R.id.progressbar);
    }

    private void setListener() {
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()) {
                    changePassword();
                }
            }
        });
    }

    private void changePassword() {
        loading(true);
        String newPassword = edtNewPassword.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            loading(false);
                            showToast("Cập Nhật Mật Khẩu Thành Công");
                        }
                    }
                });

    }

    // CO THE LAM LAI
    private void reAuthenticate() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        changePassword();
                    }
                });
    }

    private Boolean isValidation() {
        String newPassword = edtNewPassword.getText().toString().trim();
        String rePassword = edtRePassword.getText().toString().trim();
        if (!newPassword.equals(rePassword)) {
            showToast("Nhập Lại Mật Khẩu Không Khớp");
            return false;
        } else if (newPassword.isEmpty() || rePassword.isEmpty()) {
            showToast("Vui Lòng Không Để Trống");
            return false;
        } else if (newPassword.length() < 6) {
            showToast("Mật Khẩu Phải Có Ít Nhất 6 Kí Tự");
            return false;
        } else {
            return true;
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            btnUpdatePassword.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            btnUpdatePassword.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

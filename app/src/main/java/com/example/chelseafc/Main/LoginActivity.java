package com.example.chelseafc.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.chelseafc.MainActivity;
import com.example.chelseafc.R;
import com.example.chelseafc.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private SharedPreferences preferences;
    private static final String PREFS_NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        onClick();
        setListener();
        getPreferencesData();

    }

    private void getPreferencesData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(sharedPreferences.contains("pref_email")){
            String pref_email = sharedPreferences.getString("pref_email","not found");
            binding.edtEmail.setText(pref_email.toString());
        }
        if(sharedPreferences.contains("pref_password")){
            String pref_password = sharedPreferences.getString("pref_password","not found");
            binding.edtPassword.setText(pref_password.toString());
        }
        if(sharedPreferences.contains("pref_check")){
            Boolean isPrefCheck = sharedPreferences.getBoolean("pref_check",false);
            binding.cbRememberPassword.setChecked(isPrefCheck);
        }
    }

    private void setListener() {
        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()) {
                    login();
                }
            }
        });
    }

    private void onClick() {
        binding.txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        binding.txtCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.cbRememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxRemember();
            }
        });
    }

    private void login() {
        loading(true);
        String email = binding.edtEmail.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loading(false);
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void checkBoxRemember(){
        if(binding.cbRememberPassword.isChecked()){
            Boolean boolIsChecked = binding.cbRememberPassword.isChecked();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("pref_email", binding.edtEmail.getText().toString().trim());
            editor.putString("pref_password",binding.edtPassword.getText().toString().trim());
            editor.putBoolean("pref_check", boolIsChecked);
            editor.apply();
            showToast("Nhớ Mật Khẩu");
        }
        else {
            preferences.edit().clear().apply();
        }
    }

    private Boolean isValidation() {
        String email = binding.edtEmail.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            showToast("Vui Lòng Nhập");
            return false;
        } else {
            return true;
        }

    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.btnLogIn.setVisibility(View.INVISIBLE);
            binding.progressbar.setVisibility(View.VISIBLE);
        } else {
            binding.progressbar.setVisibility(View.INVISIBLE);
            binding.btnLogIn.setVisibility(View.VISIBLE);
        }
    }
}
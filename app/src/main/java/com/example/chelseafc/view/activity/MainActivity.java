package com.example.chelseafc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.chelseafc.R;
import com.example.chelseafc.databinding.ActivityMainBinding;
import com.example.chelseafc.view.fragment.AboutFragment;
import com.example.chelseafc.view.fragment.DevFragment;
import com.example.chelseafc.view.fragment.FirstTeamFragment;
import com.example.chelseafc.view.fragment.LegendFragment;
import com.example.chelseafc.view.fragment.PhotosFragment;
import com.example.chelseafc.view.fragment.PrizeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private TextView txtNickName, txtEmail;
    private ImageView imgAvatar;

    private static final int FRAGMENT_PHOTOS = 0;
    private static final int FRAGMENT_FIRST_TEAM = 1;
    private static final int FRAGMENT_LEGEND = 2;
    private static final int FRAGMENT_PRIZE = 3;
    private static final int FRAGMENT_ABOUT = 4;
    private static final int FRAGMENT_DEV = 5;
    public int currentFragment = FRAGMENT_PHOTOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setToolbar();
        setNav();
        getUserInformation();

    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setNav() {
        binding.navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new PhotosFragment());
        binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(true);
    }

    private void setCheck() {
        binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
        binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
        binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
        binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
        binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
        binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_photos) {
            if (currentFragment != FRAGMENT_PHOTOS) {
                replaceFragment(new PhotosFragment());
                currentFragment = FRAGMENT_PHOTOS;
                setCheck();
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(true);
            }
        } else if (id == R.id.nav_first_team) {
            if (currentFragment != FRAGMENT_FIRST_TEAM) {
                replaceFragment(new FirstTeamFragment());
                currentFragment = FRAGMENT_FIRST_TEAM;
                setCheck();
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(true);
            }
        } else if (id == R.id.nav_legend) {
            if (currentFragment != FRAGMENT_LEGEND) {
                replaceFragment(new LegendFragment());
                currentFragment = FRAGMENT_LEGEND;
                setCheck();
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(true);
            }
        } else if (id == R.id.nav_prize) {
            if (currentFragment != FRAGMENT_PRIZE) {
                replaceFragment(new PrizeFragment());
                currentFragment = FRAGMENT_PRIZE;
                setCheck();
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(true);
            }
        } else if (id == R.id.nav_about) {
            if (currentFragment != FRAGMENT_ABOUT) {
                replaceFragment(new AboutFragment());
                currentFragment = FRAGMENT_ABOUT;
                setCheck();
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(true);
            }
        } else if (id == R.id.nav_dev) {
            if (currentFragment != FRAGMENT_DEV) {
                replaceFragment(new DevFragment());
                currentFragment = FRAGMENT_DEV;
                setCheck();
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(true);
            }
        } else if (id == R.id.nav_signout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    public void getUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        String nick = user.getDisplayName();
        String email = user.getEmail();

        if (nick == null) {
            txtNickName.setVisibility(View.GONE);
        } else {
            txtNickName.setVisibility(View.VISIBLE);
            txtNickName.setText(nick);
        }

        txtEmail.setText(email);
        Glide.with(getApplicationContext())
                .load(user.getPhotoUrl())
                .error(R.drawable.avatar_default)
                .into(imgAvatar);
    }

    private void init() {
        imgAvatar = binding.navigationView.getHeaderView(0).findViewById(R.id.imgAvatar);
        txtNickName = binding.navigationView.getHeaderView(0).findViewById(R.id.txtNickname);
        txtEmail = binding.navigationView.getHeaderView(0).findViewById(R.id.txtEmail);
    }
}
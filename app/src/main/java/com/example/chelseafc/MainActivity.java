package com.example.chelseafc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.chelseafc.Fragment.AboutFragment;
import com.example.chelseafc.Fragment.DevFragment;
import com.example.chelseafc.Fragment.FirstTeamFragment;
import com.example.chelseafc.Fragment.LegendFragment;
import com.example.chelseafc.Fragment.MyProfileFragment;
import com.example.chelseafc.Fragment.PhotosFragment;
import com.example.chelseafc.Fragment.PrizeFragment;
import com.example.chelseafc.Main.LoginActivity;
import com.example.chelseafc.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    private static final int FRAGMENT_PROFILE = 6;
    public static final int FRAGMENT_CHANGE_PASSWORD = 7;
    public int currentFragment = FRAGMENT_PHOTOS;

    public static final int MY_REQUEST_CODE = 123;

    final private MyProfileFragment myProfileFragment = new MyProfileFragment();

    final private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent == null) {
                            return;
                        }
                        Uri uri = intent.getData();
                        myProfileFragment.setUri(uri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            myProfileFragment.setBitmapImageView(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

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
        actionBar.setTitle("");

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_photos) {
            if (currentFragment != FRAGMENT_PHOTOS) {
                replaceFragment(new PhotosFragment());
                currentFragment = FRAGMENT_PHOTOS;
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(false);
            }
        } else if (id == R.id.nav_first_team) {
            if (currentFragment != FRAGMENT_FIRST_TEAM) {
                replaceFragment(new FirstTeamFragment());
                currentFragment = FRAGMENT_FIRST_TEAM;
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(false);
            }
        } else if (id == R.id.nav_legend) {
            if (currentFragment != FRAGMENT_LEGEND) {
                replaceFragment(new LegendFragment());
                currentFragment = FRAGMENT_LEGEND;
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(false);
            }
        } else if (id == R.id.nav_prize) {
            if (currentFragment != FRAGMENT_PRIZE) {
                replaceFragment(new PrizeFragment());
                currentFragment = FRAGMENT_PRIZE;
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(false);
            }
        } else if (id == R.id.nav_about) {
            if (currentFragment != FRAGMENT_ABOUT) {
                replaceFragment(new AboutFragment());
                currentFragment = FRAGMENT_ABOUT;
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(false);
            }
        } else if (id == R.id.nav_dev) {
            if (currentFragment != FRAGMENT_DEV) {
                replaceFragment(new DevFragment());
                currentFragment = FRAGMENT_DEV;
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(false);
            }
        } else if (id == R.id.nav_profile) {
            if (currentFragment != FRAGMENT_PROFILE) {
                replaceFragment(myProfileFragment);
                currentFragment = FRAGMENT_PROFILE;
                binding.navigationView.getMenu().findItem(R.id.nav_dev).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_first_team).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_photos).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_prize).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_legend).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
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
        Uri photoUrl = user.getPhotoUrl();

        if (nick == null) {
            txtNickName.setVisibility(View.GONE);
        } else {
            txtNickName.setVisibility(View.VISIBLE);
            txtNickName.setText(nick);
        }

        txtEmail.setText(email);
        Picasso.get().load(user.getPhotoUrl()).error(R.drawable.avatar_default).into(imgAvatar);
    }

    private void init() {
        imgAvatar = binding.navigationView.getHeaderView(0).findViewById(R.id.imgAvatar);
        txtNickName = binding.navigationView.getHeaderView(0).findViewById(R.id.txtNickname);
        txtEmail = binding.navigationView.getHeaderView(0).findViewById(R.id.txtEmail);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(Intent.createChooser(intent, "a"));
    }
}
package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    //Below Three Lines for ViewPager2 & Tabs
    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private TabsAccessorAdapter adapter;
    //FirebaseLogin
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar((mToolbar));
        getSupportActionBar().setTitle("ChatApp");


        tabLayout = findViewById(R.id.main_tabs);
        pager2 = findViewById(R.id.main_tabs_pager);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new TabsAccessorAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (currentUser == null) {
            onPause();
            SendUserToLoginActivity();
        }
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        //loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_logout_option) {
            mAuth.signOut();
            SendUserToLoginActivity();
        }

        if (item.getItemId() == R.id.main_settings_option) {

        }

        if (item.getItemId() == R.id.main_find_friends_option) {

        }

        return true;
    }
}
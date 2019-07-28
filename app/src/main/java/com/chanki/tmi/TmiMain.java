package com.chanki.tmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class TmiMain extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;
    Fragment listFragment;
    Fragment uploadBoardFragment;
    Fragment myInfoFragment;


    static
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    switchFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    switchFragment(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmi_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = getIntent();
        listFragment = new ListFragment();

        String idParam = intent.getStringExtra("userId");
        String majorParam = intent.getStringExtra("userMajor");
        String nameParam = intent.getStringExtra("name");


        Bundle listBundle = new Bundle();
        listBundle.putString("id", intent.getStringExtra("name"));
        listBundle.putString("major",intent.getStringExtra("userMajor"));
        listFragment.setArguments(listBundle);

        uploadBoardFragment = new uploadBoardFragment();
        Bundle upload = new Bundle();
        upload.putString("id", idParam);
        upload.putString("name",nameParam);
        upload.putString("major",majorParam);
        uploadBoardFragment.setArguments(upload);

        myInfoFragment = new myInfoFragment();

        Bundle infoBundle = new Bundle();
        infoBundle.putString("id", intent.getStringExtra("name"));
        infoBundle.putString("major",intent.getStringExtra("userMajor"));
        infoBundle.putString("name", intent.getStringExtra("name"));
        myInfoFragment.setArguments(infoBundle);

        switchFragment(0);
    }

    public void switchFragment(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (n) {
            case 0:
                ft.replace(R.id.mainFrame, listFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.mainFrame,uploadBoardFragment);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.mainFrame,myInfoFragment);
                ft.commit();
                break;

        }



    }

}

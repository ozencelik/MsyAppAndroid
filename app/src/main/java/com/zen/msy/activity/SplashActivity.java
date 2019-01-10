package com.zen.msy.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zen.msy.R;

/**
 * Created by Ozenc Celik on 11/11/2018
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "Splash Activity";

    private Thread timerThread;
    private ImageView mImageView;

    /*
    private static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser mUser;*/
    private boolean isUserExist = false;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        mImageView = findViewById(R.id.msy);

        //mAuth = FirebaseAuth.getInstance();
        //mUser = mAuth.getCurrentUser();
        /*
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = mAuth.getCurrentUser();

                if(mUser != null) isUserExist = true;
                else isUserExist = false;

            }
        };*/

        timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {


                    //Onboarding Activity
                    if(isOnboardingViewed()){

                        //Video Activity
                        if(isUserExist){
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            SplashActivity.this.finish();
                            return;
                        }else{
                            startActivity(new Intent(SplashActivity.this, VideoActivity.class));
                            SplashActivity.this.finish();
                            return;
                        }
                    }
                    else {
                        startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class)
                                .putExtra("isUserExist", isUserExist));

                        finish();
                    }

                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        timerThread.start();

       //mAuth.addAuthStateListener(mAuthStateListener);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        /*
        if(mUser != null) isUserExist = true;
        else isUserExist = false;*/
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        //finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        //mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    public boolean isOnboardingViewed(){

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int isOnboardingViewed = preferences.getInt("isOnboardingViewed", -1);

        return ((isOnboardingViewed == -1) || (isOnboardingViewed == 0)) ? false : true;
    }
}

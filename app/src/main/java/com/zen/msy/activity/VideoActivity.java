package com.zen.msy.activity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

import com.zen.msy.R;

/**
 * Created by Ozenc Celik on 11/11/2018
 */

public class VideoActivity extends AppCompatActivity {

    private static final String TAG = "Video Activity";

    private VideoView mVideoView;
    private Uri mUri;

    private Button mLoginButton, mSignupButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //hide action bar
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_video);

        //play video
        mVideoView = findViewById(R.id.videoView);
        mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);

        mLoginButton = findViewById(R.id.login_button);
        mSignupButton = findViewById(R.id.register_button);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        mVideoView.setVideoURI(mUri);

        mVideoView.start();
        mVideoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(VideoActivity.this, LoginActivity.class));
                finish();
            }
        });
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(VideoActivity.this, SignupActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        mVideoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        mVideoView.suspend();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        mVideoView.stopPlayback();
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

}

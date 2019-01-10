package com.zen.msy.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zen.msy.R;
import com.zen.msy.adapter.OnBoardingSliderAdapter;

/**
 * Created by Ozenc Celik on 11/11/2018
 */

public class OnBoardingActivity extends AppCompatActivity {

    public static ViewPager mSliderViewPager;
    private OnBoardingSliderAdapter mSliderAdapter;
    private LinearLayout mDotsLayout;

    private Button mPrevButton, mNextButton;
    public static int mCurrentPage;
    private TextView[] mDots;

    private RelativeLayout mOnboardingLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mSliderViewPager = findViewById(R.id.slider_view_pager);
        mDotsLayout = findViewById(R.id.dots_layout);

        mSliderAdapter = new OnBoardingSliderAdapter(this);

        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);

        mDots = new TextView[3];

        mOnboardingLayout = findViewById(R.id.onboarding_layout);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onStart() {
        super.onStart();

        /* BU YAPIYI SADECE BUTON İLE GEÇİŞ YAPMAK İSTEDİĞİNDE KULLANABİLİRSİN.
        mSliderViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });*/
        mSliderViewPager.setAdapter(mSliderAdapter);
        mSliderViewPager.setCurrentItem(0);

        for (int i=0; i<mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.aluminum));

            mDotsLayout.addView(mDots[i]);
        }
        addDotsIndicator(0);
        mSliderViewPager.addOnPageChangeListener(mViewListener);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentPage == mDots.length-1){

                    if((boolean)getIntent().getSerializableExtra("isUserExist")){
                        startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
                        OnBoardingActivity.this.finish();
                        return;
                    }else{
                        startActivity(new Intent(OnBoardingActivity.this, VideoActivity.class));
                        OnBoardingActivity.this.finish();
                        return;
                    }
                }
                mSliderViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position){
        for (int i=0; i<mDots.length; i++) {
            mDots[i].setTextColor(getResources().getColor(R.color.aluminum));
        }
        if(mDots.length > 0) mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    ViewPager.OnPageChangeListener mViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            switch (position){
                case 0:
                    //mOnboardingLayout.setBackgroundColor(getResources().getColor(R.color.oil));
                    break;

                case 1:
                    //mOnboardingLayout.setBackgroundColor(getResources().getColor(R.color.monsoon));
                    break;

                case 2:
                    //mOnboardingLayout.setBackgroundColor(getResources().getColor(R.color.base));
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if(position == 0){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(false);
                mPrevButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("");

            }else if(position == mDots.length - 1){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Finish");
                mPrevButton.setText("Back");
            }else {
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}


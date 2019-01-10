package com.zen.msy.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zen.msy.R;
import com.zen.msy.activity.OnBoardingActivity;

/**
 * Created by Ozenc Celik on 10/01/2019
 */

public class OnBoardingSliderAdapter extends PagerAdapter {

    private static final String TAG = "OnBoardingSliderAdapter";

    public static OnBoardingActivity mOnActivity = new OnBoardingActivity();
    private Context mContext;
    public static LinearLayout[] mLin = new LinearLayout[3];

    public OnBoardingSliderAdapter(Context context){
        this.mContext = context;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        int mLayoutId = -1;

        switch (position) {
            case 0:
                mLayoutId = R.layout.onboarding1;
                view = inflater.inflate(mLayoutId, container, false);

                break;
            case 1:
                mLayoutId = R.layout.onboarding2;
                view = inflater.inflate(mLayoutId, container, false);

                break;
            case 2:
                mLayoutId = R.layout.onboarding3;
                view = inflater.inflate(mLayoutId, container, false);

                break;

        }
        container.addView(view, 0);

        return view;
    }


    public void addListeners(){

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 3;//TOTAL_TYPES;
    }

}


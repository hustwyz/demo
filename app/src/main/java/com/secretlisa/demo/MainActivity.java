package com.secretlisa.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private TextView tv_toutiao, tv_baike, tv_zixun, tv_jingying;
    private ImageView iv_choose1, iv_choose2, iv_choose3, iv_choose4;
    private ViewPager viewPager;
    private List<Fragment> contentFragments;
    private List<Boolean> imageViewIsChoseList;
    private List<ImageView> imageViewList;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // ----------------------------------------------------------------------------------------
        tv_toutiao = (TextView) findViewById(R.id.tv_toutiao);
        tv_baike = (TextView) findViewById(R.id.tv_baike);
        tv_zixun = (TextView) findViewById(R.id.tv_zixun);
        tv_jingying = (TextView) findViewById(R.id.tv_jingying);

        iv_choose1 = (ImageView) findViewById(R.id.iv_choose1);
        iv_choose2 = (ImageView) findViewById(R.id.iv_choose2);
        iv_choose3 = (ImageView) findViewById(R.id.iv_choose3);
        iv_choose4 = (ImageView) findViewById(R.id.iv_choose4);


        imageViewIsChoseList = new ArrayList<Boolean>();
        imageViewList = new ArrayList<ImageView>();
        imageViewIsChoseList.add(true);
        imageViewIsChoseList.add(false);
        imageViewIsChoseList.add(false);
        imageViewIsChoseList.add(false);

        imageViewList.add(iv_choose1);
        imageViewList.add(iv_choose2);
        imageViewList.add(iv_choose3);
        imageViewList.add(iv_choose4);

        tv_toutiao.setOnClickListener(this);
        tv_baike.setOnClickListener(this);
        tv_zixun.setOnClickListener(this);
        tv_jingying.setOnClickListener(this);
        addFragment();
    }
//-----------------------------------------------------------------------------
    // 向ViewPager里面添加Fragment
    public void addFragment() {
        viewPager = (ViewPager) findViewById(R.id.index_pager);
        contentFragments = new ArrayList<Fragment>();

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();

        contentFragments.add(fragment1);
        contentFragments.add(fragment2);
        contentFragments.add(fragment3);
        contentFragments.add(fragment4);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), contentFragments));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < imageViewIsChoseList.size(); i++) {
                    if (imageViewIsChoseList.get(i) == true) {
                        imageViewList.get(i).setBackgroundColor(Color.WHITE);
                        imageViewIsChoseList.set(i, false);
                    }
                }
                imageViewIsChoseList.set(arg0, true);
                imageViewList.get(arg0).setBackgroundColor(
                        Color.argb(255, 0, 170, 0));
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
//---------------------------------------------------------------------------------------
    private class MyAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> contentFragments;
        public MyAdapter(FragmentManager fm, List<Fragment> contentFragments) {
            super(fm);
            this.contentFragments = contentFragments;
            // TODO Auto-generated constructor stub
        }
        public int getCount() {
            // TODO Auto-generated method stub
            return contentFragments.size();
        }
        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return contentFragments.get(arg0);
        }
    }
//---------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toutiao:
                int itemPostion0 = 0;
                setCurrentView(itemPostion0);
                break;
            case R.id.tv_baike:
                int itemPostion1 = 1;
                setCurrentView(itemPostion1);
                break;
            case R.id.tv_zixun:
                int itemPostion2 = 2;
                setCurrentView(itemPostion2);
                break;
            case R.id.tv_jingying:
                int itemPostion3 = 3;
                setCurrentView(itemPostion3);
                break;


        }
    }
//----------------------------------------------------------------------------------
    // 设置当前viewpager
    private void setCurrentView(int itemPostion) {
        if (viewPager.getCurrentItem() != itemPostion) {
            viewPager.setCurrentItem(itemPostion);
            for (int i = 0; i < imageViewIsChoseList.size(); i++) {
                if (imageViewIsChoseList.get(i) == true) {
                    imageViewList.get(i).setBackgroundColor(Color.WHITE);
                    imageViewIsChoseList.set(i, false);
                }
            }
            imageViewIsChoseList.set(itemPostion, true);
            imageViewList.get(itemPostion).setBackgroundColor(
                    Color.argb(255, 0, 170, 0));
        }
    }
//---------------------------------------------------------------------------------------
}

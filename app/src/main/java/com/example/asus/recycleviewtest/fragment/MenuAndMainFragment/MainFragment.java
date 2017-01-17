package com.example.asus.recycleviewtest.fragment.MenuAndMainFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.recycleviewtest.R;
import com.example.asus.recycleviewtest.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by asus on 2017/1/1.
 */

public class MainFragment extends BaseFragment {


    @Bind(R.id.rb_main_news)
    RadioButton mRbMainNews;
    @Bind(R.id.rb_main_chat)
    RadioButton mRbMainChat;
    @Bind(R.id.ib_main_popup)
    ImageButton mibMainPopup;
    @Bind(R.id.rb_main_explore)
    RadioButton mRbMainExplore;
    @Bind(R.id.rb_main_me)
    RadioButton mRbMainMe;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    @Bind(R.id.fl_drawlayout_container)
    FrameLayout mFlMainContainer;
    private FragmentManager mFragmentManager;
 /*   private NewsFragment mNewsFragment;
    private ChatFragment mChatFragment;
    private ExploreFragment mExploreFragment;
    private MeFragment mMeFragment;*/
/*
   RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
       @Override
       public void onCheckedChanged(RadioGroup radioGroup, int i) {
           FragmentTransaction transaction  = mFragmentManager.beginTransaction();
           switch (i){

           }
       }
   };*/


    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mRgMain.check(R.id.rb_main_news);
        mFragmentManager = getChildFragmentManager();
        //默认替换第一页的fragment
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

    }

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_drawerlayout_main;
    }

    @Override
    protected void initData() {

    }
}

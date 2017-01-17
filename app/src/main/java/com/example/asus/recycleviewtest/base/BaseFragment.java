package com.example.asus.recycleviewtest.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by asus on 2017/1/1.
 */

public abstract class BaseFragment  extends Fragment{

    private FragmentActivity mActivity;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        mView = inflater.inflate(getLayoutResId(), null);
        /**
         * 绑定返回的view
         */
        ButterKnife.bind(this, mView);
        initView();
        initListener();
        return mView;
    }

    /**
     * 初始化监听
     */
    protected abstract void initListener();
    /**
     * 初始化一些工具类和资源
     */
    protected abstract void initView();

    /**
     * 获得布局id
     */
    protected abstract int getLayoutResId();

    /**
     * 指示fragment是否第一次显示出来
     */
    private boolean firstShow =true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser为true则说明fragment是可见的,否则是不可见的
        if(isVisibleToUser&& firstShow){
            //如果fragment是可见的，并且是第一次显示，则调用initDate来加载数据
            firstShow=false;

            //setUserVisibleHint这个方法运行的时候，onCreateView还没执行
            //为了让onCreateView先执行完，所以这里迟一点再执行initData
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    initData();
                }
            };//注意没有show，这并不是一个子线程而是线程对象
              new Handler().postDelayed(runnable,30);//延时30毫秒之后再执行加载方法
        }
    }

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

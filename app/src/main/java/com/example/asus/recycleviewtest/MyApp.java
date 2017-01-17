package com.example.asus.recycleviewtest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by asus on 2017/1/1.
 */

public class MyApp extends Application {


    private static Context context;

    private static ArrayList<Activity> mActivitys;
    public static ArrayList<Activity> getActivitys(){
        return mActivitys;
    }

    /**
     * 获取Aplication类型的上下文
     * */

    public   static Context getContext(){
        return context;
    }
    /**
     * 当myApp创建的时候这个方法会被调用
     * */
    @Override
    public void onCreate() {
        context = this;
        mActivitys  = new ArrayList<Activity>();
    }

    //夜间模式,现在还缺少了一个baseApplcation
    public static boolean getNightModeSwitch() {
        //return getPreferences().getBoolean(KEY_NIGHT_MODE_SWITCH, false);
        return false;
    }
}

package com.example.asus.recycleviewtest.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.asus.recycleviewtest.MyApp;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        /**
         *绑定view
         */
        ButterKnife.bind(this);
        /**
         *初始化一些其他的view和一些工具类
         */
        initView();
        /**
         *初始化数据
         */
        initData();
        /**
         *初始化监听
         */
        initListener();

        MyApp.getActivitys().add(this);
    }

    /**
     *设置contentView
     */
    private  void setContentView(){
        Object viewOrLayoutId = getLayoutOrView();
        if(viewOrLayoutId==null){//如果为空则抛出异常
            throw new IllegalArgumentException("viewOrLayoutId参数不能为空");
        }
        if(viewOrLayoutId instanceof Integer){
            //如果是整数，则说明布局是id
            int layoutId = (int) viewOrLayoutId;
            setContentView(layoutId);
        }else{
            setContentView((View) viewOrLayoutId);
        }
    }

    /**
     *初始化布局，布局可以是id也可以是view
     */
    protected abstract Object getLayoutOrView();

    /**
     *初始化监听
     */
    protected abstract void initListener();

    /**
     *初始化数据
     */
    protected abstract void initData() ;

    /**
     *初始化一些其他的view和一些工具类
     */
    protected abstract void initView() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getActivitys().remove(this);
    }

    /**
     * 弹吐司
     * */
    public void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}

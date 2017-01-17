package com.example.asus.recycleviewtest.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.recycleviewtest.R;
import com.example.asus.recycleviewtest.base.BaseActivity;
import com.example.asus.recycleviewtest.fragment.fragmentTest.Fragment1;
import com.example.asus.recycleviewtest.fragment.fragmentTest.Fragment2;
import com.example.asus.recycleviewtest.fragment.fragmentTest.Fragment3;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

  /*  @Bind(R.id.fl_main_container)
    FrameLayout mFlMainContainer;*/
 /*   @Bind(R.id.fl_menu_container)
    FrameLayout mFlMenuContainer;*/
    @Bind(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @Bind(R.id.toolbar)
    Toolbar mToolBar;


    private FragmentManager mFragmentManager;
    private ArrayList<TabItem> mTableItemList;

    //加载布局
    @Override
    protected Object getLayoutOrView() {
        return R.layout.activity_main;
    }

    //加载监听器
    @Override
    protected void initListener() {

    }

    //加载数据
    @Override
    protected void initData() {

    }

    //加载view
    @Override
    protected void initView() {
        initToolBar();

       if(Build.VERSION.SDK_INT >=21){ //沉浸式其实只有在5.0以上的系统才支持
         /*  //实现沉浸式
           View decorView = getWindow().getDecorView();//拿到页面的根布局
           int option = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//代表全屏
           decorView.setSystemUiVisibility(option);
           //getWindow().setStatusBarColor(Color.GREEN);//设置状态栏的颜色为透明
           getWindow().setStatusBarColor(Color.TRANSPARENT);
           getWindow().setNavigationBarColor(Color.TRANSPARENT);*/
           getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

       }

        //mFragmentManager = getSupportFragmentManager();

        initTabData();

        initTabHost();

    }

    //初始化主页选项卡视图
    private void initTabHost() {
        //实例化fragmentTabHost对象
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < mTableItemList.size(); i++) {
            TabItem tabItem = mTableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView());
            fragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            //默认选中第一个tab
            if (i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                //重置Tab样式
                for (int i = 0; i < mTableItemList.size(); i++) {
                    TabItem tabitem = mTableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        tabitem.setChecked(true);
                    } else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });


    }

    //初始化tab的数据
    private void initTabData() {
        mTableItemList = new ArrayList<>();
        //添加tab

        mTableItemList.add(new TabItem(
                R.drawable.btn_opt_text_to_tools_normal
                , R.drawable.btn_opt_text_to_tools_pressed,
                R.string.n1,
                Fragment1.class));
        mTableItemList.add(new TabItem(
                R.drawable.review_toolbar_normal
                , R.drawable.review_toolbar_pressed,
                R.string.n2,
                Fragment2.class));
        mTableItemList.add(new TabItem(
                R.drawable.btn_opt_tools_to_text_normal
                , R.drawable.btn_opt_tools_to_text_pressed,
                R.string.n3,
                Fragment3.class));


    }

    //初始化toolbar
    private void initToolBar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.hide();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.scan:
                toastMessage("我是扫一扫");
                break;
            default:
                break;
        }
        return true;
    }

    long exitTime = 0;//记录back的值
    //两次back键退出


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    //真正的退出方法
    private void exit() {
        if((System.currentTimeMillis()-exitTime)>2000){
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else{
            finish();
            System.exit(0);
        }
    }

    class TabItem {
        //正常情况下显示的图片
        private int imageNormal;
        //选中情况下选中的图片
        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;

        //tab对应的fragment
        public Class<? extends Fragment> fragmentClass;


        public View view;
        public ImageView imageView;
        public TextView textView;

        public TabItem(int imageNormal, int imagePress, int title, Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return title;
        }

        public String getTitleString() {
            if (title == 0) {
                return "";
            }
            if (TextUtils.isEmpty(titleString)) {
                titleString = getString(title);
            }
            return titleString;
        }

        public View getView() {
            if (this.view == null) {
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
                this.imageView = (ImageView) this.view.findViewById(R.id.tab_iv_image);
                this.textView = (TextView) this.view.findViewById(R.id.tab_tv_text);
                if (this.title == 0) {
                    this.textView.setVisibility(View.GONE);
                } else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }

        //切换tab的方法
        public void setChecked(boolean isChecked) {
            if (imageView != null) {
                if (isChecked) {
                    imageView.setImageResource(imagePress);
                } else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if (textView != null && title != 0) {
                if (isChecked) {
                    textView.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        }

    }
}

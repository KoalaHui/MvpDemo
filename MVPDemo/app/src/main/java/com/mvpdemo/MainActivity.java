package com.mvpdemo;

import com.mvpdemo.base.BaseActivity;
import com.mvpdemo.view.MainTab;

public class MainActivity extends BaseActivity{
    private MainTab mMainTab;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        mMainTab = findView(R.id.id_main_tab);
    }

    @Override
    public void initData() {
        super.initData();
    }
}

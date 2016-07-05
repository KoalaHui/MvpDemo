package com.mvpdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @创建者 陆辉
 * @创建时间 2016/7/522:46
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public abstract class BaseActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //每个继承基类的Activity都可以通过get方法提供布局
        setContentView(getContentView());
        initView();
        initData();
    }

    public abstract int getContentView();


    public void initView() {

    }

    public void initData() {

    }

    public <K extends View> K findView(int id) {
        return (K) findViewById(id);
    }


}

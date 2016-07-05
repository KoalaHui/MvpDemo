package com.mvpdemo.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @创建者 陆辉
 * @创建时间 2016/7/523:24
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public abstract class BaseFragment extends Fragment {
    public LayoutInflater inflater;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentView(), container, false);
    }

    public abstract int getContentView();

}

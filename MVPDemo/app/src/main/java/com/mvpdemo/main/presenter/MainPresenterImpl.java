package com.mvpdemo.main.presenter;

import com.mvpdemo.R;
import com.mvpdemo.main.view.MainView;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/1912:18
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MainPresenterImpl implements MainPresenter {

    private final MainView mMainView;

    public MainPresenterImpl(MainView mainView){
        mMainView = mainView;
  }

    @Override
    public void swichNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mMainView.switch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_about:
                mMainView.switch2About();
                break;
            default:
                mMainView.switch2News();
                break;
        }
    }
}

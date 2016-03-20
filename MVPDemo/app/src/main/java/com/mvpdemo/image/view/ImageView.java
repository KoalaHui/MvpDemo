package com.mvpdemo.image.view;

import com.mvpdemo.beans.ImageBean;

import java.util.List;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/209:37
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}

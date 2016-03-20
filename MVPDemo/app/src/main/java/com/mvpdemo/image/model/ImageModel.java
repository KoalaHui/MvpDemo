package com.mvpdemo.image.model;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/2011:15
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface ImageModel {
    void loadImageList(ImageModelImpl.OnLoadImageListListener listener);
}

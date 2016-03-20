package com.mvpdemo.image.model;

import com.mvpdemo.beans.ImageBean;
import com.mvpdemo.commons.Urls;
import com.mvpdemo.image.ImageJsonUtils;
import com.mvpdemo.utils.OkHttpUtils;

import java.util.List;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/2011:16
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ImageModelImpl implements ImageModel {


    @Override
    public void loadImageList(final ImageModelImpl.OnLoadImageListListener listener) {
        String url = Urls.IMAGES_URL;
        OkHttpUtils.ResultCallback loadNewsCallback  = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean>  imageBeanList = ImageJsonUtils.readJsonImageBeans(response);
                listener.onSuccess(imageBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load image list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }


    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> list);
        void onFailure(String msg, Exception e);
    }
}

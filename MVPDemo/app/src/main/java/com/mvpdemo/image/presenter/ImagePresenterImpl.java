package com.mvpdemo.image.presenter;

import com.mvpdemo.beans.ImageBean;
import com.mvpdemo.image.model.ImageModel;
import com.mvpdemo.image.model.ImageModelImpl;
import com.mvpdemo.image.view.ImageView;

import java.util.List;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/2011:11
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {

    private ImageView  mImageView;
    private ImageModel mImageModel;

    public ImagePresenterImpl(ImageView imageView) {
        mImageView = imageView;
        mImageModel = new ImageModelImpl();
    }


    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImages(list);
        mImageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showLoadFailMsg();

    }
}

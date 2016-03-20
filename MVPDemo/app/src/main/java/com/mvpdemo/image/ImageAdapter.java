package com.mvpdemo.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mvpdemo.R;
import com.mvpdemo.beans.ImageBean;
import com.mvpdemo.utils.ImageLoaderUtils;
import com.mvpdemo.utils.ToolsUtil;

import java.util.List;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/2010:05
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolde>{
    private Context         mContext;
    private List<ImageBean> mData;
    private int             mMaxWidth;
    private int             mMaxHeight;

    public ImageAdapter(Context context) {
        mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    //提供方法给外界传入数据
    public void setmDate(List<ImageBean> date) {
        mData = date;
        this.notifyDataSetChanged();
    }

    //为item提供视图
    @Override
    public ImageAdapter.ItemViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        ItemViewHolde vh = new ItemViewHolde(v);
        return vh;
    }

    //将视图和数据进行绑定
    @Override
    public void onBindViewHolder(ImageAdapter.ItemViewHolde holder, int position) {

        //先获取到每一项的数据
        ImageBean imageBean = mData.get(position);
        if (imageBean == null) {
            return;
        }
        holder.mTextView.setText(imageBean.getTitle());
        //获取到的图片宽度和屏幕的宽度比
        float scale = (float)imageBean.getWidth() / (float) mMaxWidth;
        //按照宽度比来设置高度
        int height = (int)(imageBean.getHeight() / scale);
        //如果图片的高度大于屏幕的宽度则将图片高度设置为屏幕高度
        if(height > mMaxHeight) {
            height = mMaxHeight;
        }

        holder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext, holder.mImageView, imageBean.getThumburl());
    }

    public class ItemViewHolde extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView mImageView;
        private final TextView mTextView;

        public ItemViewHolde(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.ivImage);
            mTextView = (TextView) itemView.findViewById(R.id.tvTitle);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

        }
    }

    //显示数据的条数
    @Override
    public int getItemCount() {
        if(mData == null) {
            return 0;
        }
        return mData.size();
    }

}

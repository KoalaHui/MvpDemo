package com.mvpdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mvpdemo.utils.ToolsUtil;

public class TipTextView extends TextView {

    private int mWidth , mHeight ;

    private int mOffset ;
    private int mOffsetT ;

    private int DEFAULT_OFFSET = 10 ;

    private int mCircleSize ;

    private int DEFAULT_SIZE = 4 ;

    private boolean mShow ;
    private boolean mTr ;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;

    public TipTextView(Context context) {
        this(context, null);

    }

    public TipTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mOffset = ToolsUtil.dip2px(getContext(),DEFAULT_OFFSET) ;
        mOffsetT =  ToolsUtil.dip2px(getContext(),14) ;
        mCircleSize =  ToolsUtil.dip2px(getContext(), DEFAULT_SIZE) ;
        mPaint.setColor(Color.RED);
//        showTips();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight() ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mShow){
            return;
        }
        float cx = mWidth / 2.0f + mOffset ;
        float cy = mHeight / 2.0f - mOffsetT ;
        if (mTr){
            cy = mCircleSize *1.0f ;
            cx = mWidth - mCircleSize *1.0f ;
        }
        canvas.drawCircle(cx,cy,mCircleSize,mPaint);
    }

    public void showTips(){
        mShow = true ;
        invalidate();
    }

    public void setSize(int size){
        mCircleSize = size ;
        invalidate();
    }
    public void setOffset(int left,int top){
        mOffset = left ;
        mOffsetT = top ;
        invalidate();
    }
    public void showTopRight(boolean tr){
        mShow = true ;
        mTr = tr ;
        invalidate();
    }
    public void hideTips(){
        mShow = false ;
        invalidate();
    }
}

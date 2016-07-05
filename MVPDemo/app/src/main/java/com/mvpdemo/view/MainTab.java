package com.mvpdemo.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mvpdemo.R;
import com.mvpdemo.base.BaseFragmentHelper;


/**
 * Created by moon.zhong on 2015/3/12.
 */
public class MainTab extends LinearLayout {

    private final int DEFAULT_TEXT_SIZE = 12;

    private final int DEFAULT_TEXT_COLOR = 0XFF000000;

    private final int DEFAULT_PADDING = 5;

    private final int DEFAULT_DRAWABLE_PADDING = 0;

    private final int DEFAULT_TOP_LINE_COLOR = 0x44000000;

    private static final int[] ATTRS = {
            android.R.attr.textSize,
            android.R.attr.textColor
    };

    private ViewPager                      mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    private Paint mTabLinePaint;

    private OnTabItemListener mItemListener;

    private int mCurrentItem = -1;

    private ColorStateList mTextColor;

    private int mTextSize;

    private int mPadding;

    private int mDrawablePadding;

    private int mTopLineColor;

    private BaseFragmentHelper mFragmentHelper;

    private int mTabCount;

    private BaseFragmentHelper.OnFragmentFetchListener mFragmentFetchListener;

    private TipTextView[] mTab;


    public MainTab(Context context) {
        this(context, null);
    }

    public MainTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray sysTypedArray = context.obtainStyledAttributes(attrs, ATTRS);
        mTextSize = (int) sysTypedArray.getDimension(0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
        mTextColor = sysTypedArray.getColorStateList(1);
        if (mTextColor == null)
            mTextColor = ColorStateList.valueOf(DEFAULT_TEXT_COLOR);
        sysTypedArray.recycle();

        TypedArray styleTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ZMainTab);
        int N = styleTypedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = sysTypedArray.getIndex(i);
            switch (attr) {
                case R.styleable.ZMainTab_tab_top_line_color:
                    mTopLineColor = styleTypedArray.getColor(attr, DEFAULT_TOP_LINE_COLOR);
                    break;
                case R.styleable.ZMainTab_tab_padding:
                    mPadding = (int) styleTypedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_PADDING, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ZMainTab_tab_drawable_padding:
                    mDrawablePadding = (int) styleTypedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_DRAWABLE_PADDING, getResources().getDisplayMetrics()));
                    break;
            }
        }
        styleTypedArray.recycle();
        initView();
        setWillNotDraw(false);
    }

    private void initView() {
        mTabLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTabLinePaint.setColor(mTopLineColor);
        mTabLinePaint.setStrokeWidth(0.5f * getResources().getDisplayMetrics().density);
        setGravity(Gravity.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, getWidth(), 0, mTabLinePaint);

    }

    public void showTips(boolean show, int position) {
        if (show)
            mTab[position].showTips();
        else
            mTab[position].hideTips();
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            throw new RuntimeException("viewPager不能为空！");
        }
        removeAllViews();
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new InternalViewPagerChange());
        final PagerAdapter adapter = mViewPager.getAdapter();
        mItemListener = (OnTabItemListener) adapter;
        mTabCount = adapter.getCount();
        populateTabLayout();
        notifyStatue(mViewPager.getCurrentItem());
        invalidate();
    }

    public void setOnPagerChangeListener(ViewPager.OnPageChangeListener listener) {
        mListener = listener;
    }

    public void setOnFragmentFetchListener(BaseFragmentHelper.OnFragmentFetchListener listener) {
        mFragmentFetchListener = listener;
    }

    public void setFragmentHelper(BaseFragmentHelper helper) {
        mFragmentHelper = helper;
        removeAllViews();
        mFragmentHelper.setOnFragmentFetchListener(new InternalFragmentFetch());
        mItemListener = (OnTabItemListener) helper;
        mTabCount = mFragmentHelper.getCount();
        mTab = new TipTextView[mTabCount];
        populateTabLayout();
        notifyStatue(0);
        invalidate();
    }

    private void populateTabLayout() {

        final OnClickListener tabOnClickListener = new TabOnClickListener();

        for (int i = 0; i < mTabCount; i++) {
            TipTextView textView = createDefaultTabView(getContext());
            textView.setOnClickListener(tabOnClickListener);
            textView.setText(mItemListener.getTabText(getContext(), i));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, mItemListener.getTabRes(i), 0, 0);
//            Log.v("zgy","=========mDrawablePadding======="+mDrawablePadding) ;
            textView.setCompoundDrawablePadding(mDrawablePadding);
//            textView.setBackgroundResource(R.drawable.actionbar_back_bg);
            mTab[i] = textView;
            addView(textView);
        }
    }


    private TipTextView createDefaultTabView(Context context) {
        TipTextView textView = new TipTextView(context);
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, mPadding, 0, 0);
        textView.setTextColor(mTextColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        return textView;
    }

    //    public void drawPoint(int position,boolean draw){
//        ((TRevealLayout)mTab[position]).setDrawPoint(draw);
//    }
    private TextView createRevealTabView(Context context) {
        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, mPadding, 0, 0);
        return textView;
    }

    private class InternalFragmentFetch implements BaseFragmentHelper.OnFragmentFetchListener {
        @Override
        public void onFragmentSelected(int position) {
            notifyStatue(position);
            if (mFragmentFetchListener != null) {
                mFragmentFetchListener.onFragmentSelected(position);
            }
        }
    }

    private class InternalViewPagerChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (mListener != null) {
                mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            notifyStatue(position);
            if (mListener != null) {
                mListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mListener != null) {
                mListener.onPageScrollStateChanged(state);
            }
        }
    }

    public void notifyStatue(int position) {
        if (position == mCurrentItem) {
            return;
        }
        View lastSelectItem = getChildAt(mCurrentItem);
        if (lastSelectItem != null)
            lastSelectItem.setSelected(false);
        View selectItem = getChildAt(position);
        selectItem.setSelected(true);
        mCurrentItem = position;
    }

    private class TabOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            int index = indexOfChild(v);
            if (mViewPager != null) {
                if (mViewPager.getCurrentItem() == index) {
                    return;
                }
                mViewPager.setCurrentItem(index, false);
            } else if (mFragmentHelper != null) {
                if (mFragmentHelper.getCurrentItem() == index) {
                    return;
                }
                mFragmentHelper.setCurrentItem(index);
            }

        }
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.position = mCurrentItem;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        mFragmentHelper.setCurrentItem(ss.position);
    }


    public static class SavedState extends BaseSavedState {
        int position;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(position);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private SavedState(Parcel in) {
            super(in);
            position = in.readInt();
        }
    }

    public interface OnTabItemListener {

        int getTabRes(int position);

        CharSequence getTabText(Context context, int position);

    }
}

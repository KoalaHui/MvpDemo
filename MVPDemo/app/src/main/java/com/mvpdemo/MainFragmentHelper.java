package com.mvpdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import com.mvpdemo.base.BaseFragmentHelper;
import com.mvpdemo.image.view.PictrueFragment;
import com.mvpdemo.movie.widget.MovieFragment;
import com.mvpdemo.music.widget.MusicFragment;
import com.mvpdemo.my.widget.MyFragment;
import com.mvpdemo.news.widget.NewsFragment;
import com.mvpdemo.view.MainTab;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongguangyan on 2015/8/21.
 */
public class MainFragmentHelper extends BaseFragmentHelper implements MainTab.OnTabItemListener{

    private Map<Integer, Fragment> mFragments;

    public static final int FRAGMENT_FOOD_BOOK = 0;
    public static final int FRAGMENT_LIKE = 1;
    public static final int FRAGMENT_MARKET = 2;
    public static final int FRAGMENT_FOOD_CLASS = 3;
    public static final int FRAGMENT_OWNER = 4;

    private int[] resId = {R.drawable.tab_food_book, R.drawable.tab_shequ,R.drawable.tab_market, R.drawable.tab_food_class, R.drawable.tab_owner};

    public MainFragmentHelper(FragmentManager mFragmentManager, int mContainerId) {
        super(mFragmentManager, mContainerId);
        mFragments = new HashMap<>() ;
//        setCurrentItem(0);
    }

    @Override
    public <T extends Fragment> T getFragment(int position) {
        T fragment = (T) mFragments.get(position);
        if (fragment == null){
            switch (position){
                case FRAGMENT_FOOD_BOOK:
                    fragment = (T) new NewsFragment();
                    break;
                case FRAGMENT_LIKE:
                    fragment =  (T) new PictrueFragment();
                    break;
                case FRAGMENT_MARKET:
                    fragment = (T) new MovieFragment();
                    break;
                case FRAGMENT_FOOD_CLASS:
                    fragment = (T) new MusicFragment();
                    break;
                case FRAGMENT_OWNER:
                    fragment = (T) new MyFragment();

                    break;
            }
            mFragments.put(position,fragment) ;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return resId.length;
    }

    @Override
    public int getTabRes(int position) {
        return resId[position];
    }

    @Override
    public CharSequence getTabText(Context context, int position) {
        return context.getResources().getStringArray(R.array.main_tab_title)[position];
    }
}

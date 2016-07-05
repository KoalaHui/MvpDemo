package com.mvpdemo.base;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by zhongguangyan on 2015/8/21.
 */
public abstract class BaseFragmentHelper {

    private FragmentManager         mFragmentManager ;
    private int                     mContainerId ;
    private int                     mCurrentItem ;
    private OnFragmentFetchListener mFragmentFetchListener ;
    private OnFragmentInitListener  mOnFragmentInitListener ;
    private FragmentTransaction     mFragmentTransaction ;

    public BaseFragmentHelper(FragmentManager mFragmentManager, int mContainerId) {
        this.mFragmentManager = mFragmentManager;
        this.mContainerId = mContainerId;
        if (mFragmentTransaction == null)
            mFragmentTransaction = this.mFragmentManager.beginTransaction() ;
//        mFragmentTransaction.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out);
    }

    private void hideFragment(){
        Fragment fragment = getFragment(mCurrentItem) ;
        if (fragment.isAdded() &&!fragment.isHidden()){
            if (mFragmentTransaction == null)
                mFragmentTransaction = this.mFragmentManager.beginTransaction() ;
//            mFragmentTransaction.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out);
            mFragmentTransaction.hide(fragment).commit() ;
            mFragmentTransaction = null ;
        }
    }

    private void fetchFragment(){
        Fragment fragment = getFragment(mCurrentItem) ;
        if (!fragment.isAdded()){
            if (mFragmentTransaction == null)
                mFragmentTransaction = this.mFragmentManager.beginTransaction() ;
//            mFragmentTransaction.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out);
            mFragmentTransaction.add(mContainerId,fragment).commit() ;
            mFragmentTransaction = null ;
            if (mOnFragmentInitListener != null){
                mOnFragmentInitListener.onFragmentAdd(mCurrentItem);
            }
        }else {
            if (mFragmentTransaction == null)
                mFragmentTransaction = this.mFragmentManager.beginTransaction() ;
//            mFragmentTransaction.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out);
            mFragmentTransaction.show(fragment).commit() ;
            mFragmentTransaction = null ;
        }
        if (mFragmentFetchListener != null){
            mFragmentFetchListener.onFragmentSelected(mCurrentItem);
        }
    }

    public abstract <T extends Fragment> T getFragment(int position) ;

    public int getCurrentItem(){
        return mCurrentItem ;
    }


    public void setCurrentItem(int position){

        hideFragment() ;
        mCurrentItem = position ;
        fetchFragment();
    }

    public abstract int getCount();

    public void setOnFragmentFetchListener(OnFragmentFetchListener listener){
        mFragmentFetchListener = listener ;
    }

    public void setOnFragmentInitListener(OnFragmentInitListener listener){
        mOnFragmentInitListener = listener ;
    }

    public interface OnFragmentFetchListener{

        public void onFragmentSelected(int position) ;

    }
    public interface OnFragmentInitListener{

        public void onFragmentAdd(int position) ;
    }
}

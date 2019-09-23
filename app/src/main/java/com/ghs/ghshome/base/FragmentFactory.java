package com.ghs.ghshome.base;

import android.util.SparseArray;

import com.ghs.ghshome.models.mine.mineWorkOrders.AllOrderFragment;
import com.ghs.ghshome.models.mine.mineWorkOrders.CompletedOrderFragment;
import com.ghs.ghshome.models.mine.mineWorkOrders.FollowingOrderFragment;
import com.ghs.ghshome.models.mine.mineWorkOrders.ToFollowOrderFragment;

/**
 * Author:wang_sir
 * Time:2018/6/20 21:10
 * Description:This is FragmentFactory
 */
public class FragmentFactory {



    /**
     * 我的工单中的四个fragment
     * @return
     */
    public static SparseArray<android.support.v4.app.Fragment> getFragmentsOfMineOrder(){
        SparseArray<android.support.v4.app.Fragment> mFragments =  new SparseArray<>();
        mFragments.put(0, new AllOrderFragment());
        mFragments.put(1, new ToFollowOrderFragment());
        mFragments.put(2, new FollowingOrderFragment());
        mFragments.put(3, new CompletedOrderFragment());
        return mFragments;
    }



}

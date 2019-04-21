package com.mj.cab10.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mj.cab10.fragment.PastOrdersFragment;
import com.mj.cab10.fragment.ScheduleOrdersFragment;

public class YourTripsPagerAdapter extends FragmentStatePagerAdapter {
    public YourTripsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment frag;

        if (i == 0) {
            frag = new ScheduleOrdersFragment();
        } else
            frag = new PastOrdersFragment();

        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

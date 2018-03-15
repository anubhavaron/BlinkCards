package com.addy1397.blinkcards;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by adity on 12-02-2018.
 */

class SectionsPagerAdapter extends FragmentPagerAdapter{
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position)
        {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                CardFragment cardFragment = new CardFragment();
                return cardFragment;
            case 2:
                DetailFragment detailFragment = new DetailFragment();
                return detailFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

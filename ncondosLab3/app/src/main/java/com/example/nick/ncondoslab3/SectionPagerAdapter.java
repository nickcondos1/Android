package com.example.nick.ncondoslab3;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SectionPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<Manufacturer> manufacturers;

    public SectionPagerAdapter(FragmentManager manager, ArrayList<Manufacturer> manu)
    {
        super(manager);
        manufacturers = manu;
    }

    @Override
    public Fragment getItem(int i)
    {
        if (i == 0)
            return ListFragment.newInstance(manufacturers);
        else
            return new DetailFragment();

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }


}

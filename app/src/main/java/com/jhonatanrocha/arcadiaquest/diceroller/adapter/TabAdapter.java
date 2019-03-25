package com.jhonatanrocha.arcadiaquest.diceroller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jhonatanrocha.arcadiaquest.diceroller.fragment.BattleFragment;
import com.jhonatanrocha.arcadiaquest.diceroller.fragment.RespawnFragment;

/**
 * Created by jhol on 13/03/2019.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private String[] tituloAbas = {"BATALHA", "RESPAWN"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0 :
                fragment = new BattleFragment();
                break;
            case 1 :
                fragment = new RespawnFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return this.tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tituloAbas[position];
    }


}

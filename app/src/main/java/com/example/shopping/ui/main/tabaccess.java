package com.example.shopping.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shopping.ui.main.user.HomeUserFragment;
import com.example.shopping.ui.main.user.ShoesFragment;
import com.example.shopping.ui.main.user.backupFragment;

public class tabaccess extends FragmentPagerAdapter {
    public tabaccess(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                HomeUserFragment homeFragment=new HomeUserFragment();
                return homeFragment;

            case 1:
                backupFragment backupFragment=new backupFragment();
                return backupFragment;
            case 2:

            ShoesFragment shoesFragment=new ShoesFragment();
            return shoesFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";

            case 1:
                return "Backup";
            case 2:
                return "Shoes";

            default:
                return null;

        }
    }
}

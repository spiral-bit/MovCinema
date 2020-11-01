package spiral.bit.dev.movcinema.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import spiral.bit.dev.movcinema.fragments.NowPlayingFragment;
import spiral.bit.dev.movcinema.fragments.PopularFragment;
import spiral.bit.dev.movcinema.fragments.TopRatingFragment;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new PopularFragment();
            case 1:
                return new TopRatingFragment();
            case 2:
                return new NowPlayingFragment();
            default:
                return new PopularFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Популярные";
            case 1:
                return "Топ-рейтинг";
            case 2:
                return "Недавние";
            default:
                return "Популярные";
        }
    }
}
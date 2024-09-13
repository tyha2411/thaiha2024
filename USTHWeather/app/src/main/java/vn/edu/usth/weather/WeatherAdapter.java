package vn.edu.usth.weather;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class WeatherAdapter extends FragmentPagerAdapter {

    public WeatherAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WeatherAndForecastFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
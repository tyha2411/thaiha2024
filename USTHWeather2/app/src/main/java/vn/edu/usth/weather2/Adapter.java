package vn.edu.usth.weather2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vn.edu.usth.weather2.WeatherAndForecastFragment;
import vn.edu.usth.weather2.WeatherAndForecastFragment2;
import vn.edu.usth.weather2.WeatherAndForecastFragment3;

public class Adapter extends FragmentPagerAdapter {
    public Adapter(FragmentManager fa) {
        super(fa);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WeatherAndForecastFragment();
            case 1:
                return new WeatherAndForecastFragment2();
            case 2:
                return new WeatherAndForecastFragment3();
            default:
                return new WeatherAndForecastFragment();
        }
    }

    @Override
    public int getCount() {
        return 3; // Number of pages for a ViewPager
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Hanoi, Vietnam";
                break;
            case 1:
                title = "Paris, France";
                break;
            case 2:
                title = "Madrid, Espanol";
                break;
        }
        return title;
    }
}
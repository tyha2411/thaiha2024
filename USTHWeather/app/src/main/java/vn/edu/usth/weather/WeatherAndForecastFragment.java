package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.weather.ForecastFragment;
import vn.edu.usth.weather.R;
import vn.edu.usth.weather.WeatherFragment;


public class WeatherAndForecastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.weather_fragment_container, new WeatherFragment());
        transaction.replace(R.id.forecast_fragment_container, new ForecastFragment());
        transaction.commit();

        return view;
    }
}
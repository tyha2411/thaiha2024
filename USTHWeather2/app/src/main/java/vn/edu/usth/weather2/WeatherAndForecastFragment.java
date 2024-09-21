package vn.edu.usth.weather2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeatherAndForecastFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Fragment weather = new WeatherFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.weather_container, weather).commit();

        Fragment forecast = new ForecastFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.forecast_container, forecast).commit();
    }
}
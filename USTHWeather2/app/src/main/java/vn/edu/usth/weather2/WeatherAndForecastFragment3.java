package vn.edu.usth.weather2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherAndForecastFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherAndForecastFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherAndForecastFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherAndForecastFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherAndForecastFragment3 newInstance(String param1, String param2) {
        WeatherAndForecastFragment3 fragment = new WeatherAndForecastFragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WeatherFragment3 weatherFragment = new WeatherFragment3();
        getChildFragmentManager().beginTransaction().replace(R.id.weather_container, weatherFragment).commit();
        // Create and add ForecastFragment_2
        ForecastFragment3 forecastFragment = new ForecastFragment3();
        getChildFragmentManager().beginTransaction().replace(R.id.forecast_container, forecastFragment).commit();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_and_forecast3, container, false);
    }
}
package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentBasicInfo extends Fragment {

    TextView cityInfo;
    TextView latInfo;
    TextView longInfo;
    TextView temperatureInfo;
    TextView pressureInfo;
    TextView descriptionInfo;

    ForecastDataModel forecastDataModel;

    public FragmentBasicInfo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);

        cityInfo = view.findViewById(R.id.cityInfo);
        latInfo = view.findViewById(R.id.latInfo);
        longInfo = view.findViewById(R.id.longInfo);
        temperatureInfo = view.findViewById(R.id.temperatureInfo);
        pressureInfo = view.findViewById(R.id.pressureInfo);
        descriptionInfo = view.findViewById(R.id.descriptionInfo);
        update();
        return view;
    }

    public void update() {

        try {
            boolean isTablet =  ((SpecificForecastActivity) getActivity()).tabletSize;
            ForecastDataModel forecastDataModel = ((SpecificForecastActivity) getActivity()).forecastDataModel;
            String dataFormat = ((SpecificForecastActivity) getActivity()).dataFormat;
            cityInfo.setText(forecastDataModel.location.city + ", " + forecastDataModel.location.region + ", " + forecastDataModel.location.country);
            latInfo.setText(forecastDataModel.location.latitude + "°");
            longInfo.setText(forecastDataModel.location.longitude + "°");
            temperatureInfo.setText(forecastDataModel.current_observation.condition.temperature + (dataFormat.isEmpty() ? " F" : " C"));
            pressureInfo.setText(forecastDataModel.current_observation.atmosphere.pressure + (dataFormat.isEmpty() ? " inchHg" : " mbar"));
            descriptionInfo.setText(forecastDataModel.current_observation.condition.text);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

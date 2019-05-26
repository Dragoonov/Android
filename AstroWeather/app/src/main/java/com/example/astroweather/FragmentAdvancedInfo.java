package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentAdvancedInfo extends Fragment {

    TextView windStrengthInfo;
    TextView windDirectionInfo;
    TextView humidityInfo;
    TextView visibilityInfo;

    public FragmentAdvancedInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advanced_info, container, false);

        windStrengthInfo = view.findViewById(R.id.windStrengthInfo);
        windDirectionInfo = view.findViewById(R.id.windDirectionInfo);
        humidityInfo = view.findViewById(R.id.humidityInfo);
        visibilityInfo = view.findViewById(R.id.visibilityInfo);
        update();
        return view;
    }

    public void update()
    {
        try {
            ForecastDataModel forecastDataModel = ((SpecificForecastActivity) getActivity()).forecastDataModel;
            String dataFormat =  ((SpecificForecastActivity) getActivity()).dataFormat;
            windStrengthInfo.setText(forecastDataModel.current_observation.wind.speed+(dataFormat.isEmpty()?" m/h":" km/h"));
            windDirectionInfo.setText(forecastDataModel.current_observation.wind.direction+"°");
            humidityInfo.setText(forecastDataModel.current_observation.atmosphere.humidity+"%");
            visibilityInfo.setText(forecastDataModel.current_observation.atmosphere.visibility+(dataFormat.isEmpty()?" m":" km"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

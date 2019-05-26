package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentFutureInfo extends Fragment {

    TextView day1Info;
    TextView day1LowInfo;
    TextView day1HighInfo;
    TextView day1DescriptionInfo;

    TextView day2Info;
    TextView day2LowInfo;
    TextView day2HighInfo;
    TextView day2DescriptionInfo;

    TextView day3Info;
    TextView day3LowInfo;
    TextView day3HighInfo;
    TextView day3DescriptionInfo;

    TextView day4Info;
    TextView day4LowInfo;
    TextView day4HighInfo;
    TextView day4DescriptionInfo;

    TextView day5Info;
    TextView day5LowInfo;
    TextView day5HighInfo;
    TextView day5DescriptionInfo;

    TextView day6Info;
    TextView day6LowInfo;
    TextView day6HighInfo;
    TextView day6DescriptionInfo;

    TextView day7Info;
    TextView day7LowInfo;
    TextView day7HighInfo;
    TextView day7DescriptionInfo;

    public FragmentFutureInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_future_info, container, false);

        day1Info = view.findViewById(R.id.day1Info);
        day1LowInfo = view.findViewById(R.id.day1LowInfo);
        day1HighInfo = view.findViewById(R.id.day1HighInfo);
        day1DescriptionInfo = view.findViewById(R.id.day1DescriptionInfo);

        day2Info = view.findViewById(R.id.day2Info);
        day2LowInfo = view.findViewById(R.id.day2LowInfo);
        day2HighInfo = view.findViewById(R.id.day2HighInfo);
        day2DescriptionInfo = view.findViewById(R.id.day2DescriptionInfo);

        day3Info = view.findViewById(R.id.day3Info);
        day3LowInfo = view.findViewById(R.id.day3LowInfo);
        day3HighInfo = view.findViewById(R.id.day3HighInfo);
        day3DescriptionInfo = view.findViewById(R.id.day3DescriptionInfo);

        day4Info = view.findViewById(R.id.day4Info);
        day4LowInfo = view.findViewById(R.id.day4LowInfo);
        day4HighInfo = view.findViewById(R.id.day4HighInfo);
        day4DescriptionInfo = view.findViewById(R.id.day4DescriptionInfo);

        day5Info = view.findViewById(R.id.day5Info);
        day5LowInfo = view.findViewById(R.id.day5LowInfo);
        day5HighInfo = view.findViewById(R.id.day5HighInfo);
        day5DescriptionInfo = view.findViewById(R.id.day5DescriptionInfo);

        day6Info = view.findViewById(R.id.day6Info);
        day6LowInfo = view.findViewById(R.id.day6LowInfo);
        day6HighInfo = view.findViewById(R.id.day6HighInfo);
        day6DescriptionInfo = view.findViewById(R.id.day6DescriptionInfo);

        day7Info = view.findViewById(R.id.day7Info);
        day7LowInfo = view.findViewById(R.id.day7LowInfo);
        day7HighInfo = view.findViewById(R.id.day7HighInfo);
        day7DescriptionInfo = view.findViewById(R.id.day7DescriptionInfo);

        update();
        return view;
    }

    public void update(){

        try{
        ForecastDataModel forecastDataModel = ((SpecificForecastActivity)getActivity()).forecastDataModel;
        String dataFormat =  ((SpecificForecastActivity) getActivity()).dataFormat;
        day1Info.setText(forecastDataModel.forecasts[0].day);
        day1LowInfo.setText(forecastDataModel.forecasts[0].low+(dataFormat.isEmpty()?" F":" C"));
        day1HighInfo.setText(forecastDataModel.forecasts[0].high+(dataFormat.isEmpty()?" F":" C"));
        day1DescriptionInfo.setText(forecastDataModel.forecasts[0].text);

        day2Info.setText(forecastDataModel.forecasts[1].day);
        day2LowInfo.setText(forecastDataModel.forecasts[1].low+(dataFormat.isEmpty()?" F":" C"));
        day2HighInfo.setText(forecastDataModel.forecasts[1].high+(dataFormat.isEmpty()?" F":" C"));
        day2DescriptionInfo.setText(forecastDataModel.forecasts[1].text);

        day3Info.setText(forecastDataModel.forecasts[2].day);
        day3LowInfo.setText(forecastDataModel.forecasts[2].low+(dataFormat.isEmpty()?" F":" C"));
        day3HighInfo.setText(forecastDataModel.forecasts[2].high+(dataFormat.isEmpty()?" F":" C"));
        day3DescriptionInfo.setText(forecastDataModel.forecasts[2].text);

        day4Info.setText(forecastDataModel.forecasts[3].day);
        day4LowInfo.setText(forecastDataModel.forecasts[3].low+(dataFormat.isEmpty()?" F":" C"));
        day4HighInfo.setText(forecastDataModel.forecasts[3].high+(dataFormat.isEmpty()?" F":" C"));
        day4DescriptionInfo.setText(forecastDataModel.forecasts[3].text);

        day5Info.setText(forecastDataModel.forecasts[4].day);
        day5LowInfo.setText(forecastDataModel.forecasts[4].low+(dataFormat.isEmpty()?" F":" C"));
        day5HighInfo.setText(forecastDataModel.forecasts[4].high+(dataFormat.isEmpty()?" F":" C"));
        day5DescriptionInfo.setText(forecastDataModel.forecasts[4].text);

        day6Info.setText(forecastDataModel.forecasts[5].day);
        day6LowInfo.setText(forecastDataModel.forecasts[5].low+(dataFormat.isEmpty()?" F":" C"));
        day6HighInfo.setText(forecastDataModel.forecasts[5].high+(dataFormat.isEmpty()?" F":" C"));
        day6DescriptionInfo.setText(forecastDataModel.forecasts[5].text);

        day7Info.setText(forecastDataModel.forecasts[6].day);
        day7LowInfo.setText(forecastDataModel.forecasts[6].low+(dataFormat.isEmpty()?" F":" C"));
        day7HighInfo.setText(forecastDataModel.forecasts[6].high+(dataFormat.isEmpty()?" F":" C"));
        day7DescriptionInfo.setText(forecastDataModel.forecasts[6].text);
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}

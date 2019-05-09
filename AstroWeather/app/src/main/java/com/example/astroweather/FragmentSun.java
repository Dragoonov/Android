package com.example.astroweather;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {//@link FragmentSun.OnFragmentInteractionListener} interface
/* to handle interaction events.
 * Use the {//@link FragmentSun#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSun extends Fragment {

    TextView sunrise;
    TextView sunset;
    TextView azimuthRise;
    TextView azimuthSet;
    TextView twilightMorning;
    TextView twilightEvening;

    public FragmentSun() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_sun, container, false);
        view.setBackgroundColor(Color.WHITE);
        DataProcessor.sunInflater = inflater;
        DataProcessor.sunContainer = container;
        sunrise = view.findViewById(R.id.sunrise);
        sunset = view.findViewById(R.id.sunset);
        azimuthRise = view.findViewById(R.id.azimuthRise);
        azimuthSet = view.findViewById(R.id.azimuthSet);
        twilightMorning =view.findViewById(R.id.twilightMorning);
        twilightEvening = view.findViewById(R.id.twilightEvening);
        update();
        return view;
    }

    void update()
    {
        sunrise.setText(DataProcessor.getSunrise());
        sunset.setText(DataProcessor.getSunset());
        azimuthRise.setText(DataProcessor.getAzimuthRise());
        azimuthSet.setText(DataProcessor.getAzimuthSet());
        twilightMorning.setText(DataProcessor.getTwilightMorning());
        twilightEvening.setText(DataProcessor.getTwilightEvening());
    }

}

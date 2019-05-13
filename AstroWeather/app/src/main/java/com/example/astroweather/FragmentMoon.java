package com.example.astroweather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {//@link FragmentMoon.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {//@link FragmentMoon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMoon extends Fragment {

    TextView moonrise;
    TextView age;
    TextView moonset;
    TextView newmoon;
    TextView illumination;
    TextView fullmoon;

    public FragmentMoon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moon, container, false);
        view.setBackgroundColor(Color.BLACK);
        moonrise = view.findViewById(R.id.moonrise);
        age = view.findViewById(R.id.age);
        moonset = view.findViewById(R.id.moonset);
        newmoon = view.findViewById(R.id.newmoon);
        illumination = view.findViewById(R.id.illumination);
        fullmoon = view.findViewById(R.id.fullmoon);
        moonrise.setText(DataProcessor.getMoonrise());
        age.setText(DataProcessor.getAge());
        moonset.setText(DataProcessor.getMoonset());
        newmoon.setText(DataProcessor.getNewMoon());
        illumination.setText(DataProcessor.getIllumination());
        fullmoon.setText(DataProcessor.getFullMoon());
        update();
        return view;
    }

    void update()
    {
        moonrise.setText(DataProcessor.getMoonrise());
        age.setText(DataProcessor.getAge());
        moonset.setText(DataProcessor.getMoonset());
        newmoon.setText(DataProcessor.getNewMoon());
        illumination.setText(DataProcessor.getIllumination());
        fullmoon.setText(DataProcessor.getFullMoon());
    }

}

package com.example.astroweather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SpecificForecastActivity extends AppCompatActivity {


    boolean tabletSize;
    String localizationData;
    ForecastDataModel forecastDataModel;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    Button refresh;
    String dataFormat = "";
    Button imperialSystem;
    Button metricSystem;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean showErrorToast = true;

    FragmentBasicInfo fragmentBasicInfo = new FragmentBasicInfo();
    FragmentAdvancedInfo fragmentAdvancedInfo = new FragmentAdvancedInfo();
    FragmentFutureInfo fragmentFutureInfo = new FragmentFutureInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_forecast);
        getSupportActionBar().hide();
        localizationData = getIntent().getStringExtra("localizationInfo");
        showErrorToast = getIntent().getBooleanExtra("fromButton",false);
        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData(true);
            }
        });

        imperialSystem = findViewById(R.id.imperialSystem);
        imperialSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataFormat = "";
                refreshData(false);
            }
        });

        metricSystem = findViewById(R.id.metricSystem);
        metricSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataFormat="&u=c";
                refreshData(false);
            }
        });

        tabletSize = getResources().getBoolean(R.bool.isTablet);

        if (tabletSize && savedInstanceState==null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.basicContent,fragmentBasicInfo,"fragmentBasicInfo");
            fragmentTransaction.add(R.id.advancedContent,fragmentAdvancedInfo,"fragmentAdvancedInfo");
            fragmentTransaction.add(R.id.futureContent,fragmentFutureInfo,"fragmentFutureInfo");
            fragmentTransaction.commit();

        }
        else if (tabletSize && savedInstanceState!=null)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.basicContent,fragmentBasicInfo,"fragmentBasicInfo");
            fragmentTransaction.replace(R.id.advancedContent,fragmentAdvancedInfo,"fragmentAdvancedInfo");
            fragmentTransaction.replace(R.id.futureContent,fragmentFutureInfo,"fragmentFutureInfo");
            fragmentTransaction.commit();
        }
        else {
            pager = (ViewPager) findViewById(R.id.forecastContainer);
            pagerAdapter = new ListPagerAdapter(getSupportFragmentManager());
            pager.setAdapter(pagerAdapter);
        }

        refreshData(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data",dataFormat);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        dataFormat=savedInstanceState.getString("data");
        refreshData(false);
        showErrorToast=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void updateFragments()
    {
        fragmentBasicInfo.update();
        fragmentAdvancedInfo.update();
        fragmentFutureInfo.update();
    }

    public void refreshData(final boolean fromButton)
    {
        ExampleRequest request = new ExampleRequest(Request.Method.GET, null, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                SharedPreferences sharedPreferences = getSharedPreferences(localizationData, MODE_PRIVATE);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                Gson gson = gsonBuilder.create();
                String jsonString = response.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(localizationData, jsonString);
                editor.commit();
                forecastDataModel = gson.fromJson(jsonString, ForecastDataModel.class);
                updateFragments();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(fromButton)
                    Toast.makeText(getApplicationContext(), "Nie udalo sie polaczyc z baza. Pobieranie z pamieci lokalnej", Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences(localizationData, MODE_PRIVATE);
                String jsonString = sharedPreferences.getString(localizationData,"");
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                Gson gson = gsonBuilder.create();
                forecastDataModel = gson.fromJson(jsonString, ForecastDataModel.class);
                updateFragments();
            }
        });
        ExampleRequestManager requestManager = ExampleRequestManager.getInstance(this);
        request.setCity(localizationData);
        request.setDataFormat(dataFormat);
        requestManager.addToRequestQueue(request);
    }


    class ListPagerAdapter extends PagerAdapter {

        FragmentManager fragmentManager;
        Fragment[] fragments;

        ListPagerAdapter(FragmentManager fm) {
            fragmentManager = fm;
            fragments = new Fragment[3];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            assert(0 <= position && position < fragments.length);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.remove(fragments[position]);
            trans.commit();
            fragments[position] = null;
        }

        @Override
        public Fragment instantiateItem(ViewGroup container, int position){
            Fragment fragment = getItem(position);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.add(container.getId(),fragment,"fragment:"+position);
            trans.commit();
            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object fragment) {
            return ((Fragment) fragment).getView() == view;
        }

        public Fragment getItem(int position){
            assert(0 <= position && position < fragments.length);
            if(fragments[position] == null){
                if(position==0)
                    fragments[position] = (fragmentBasicInfo = new FragmentBasicInfo()); //make your fragment here
                else if(position==1)
                    fragments[position] = (fragmentAdvancedInfo = new FragmentAdvancedInfo());
                else if (position==2)
                    fragments[position] = (fragmentFutureInfo = new FragmentFutureInfo());
            }
            return fragments[position];
        }
    }

}

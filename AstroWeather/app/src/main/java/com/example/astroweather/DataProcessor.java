package com.example.astroweather;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class DataProcessor {

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


    static float widthGeo= 51.9194f;
    static float heightGeo= 19.1451f;
    static int timeRefresh = 5;
    static LayoutInflater sunInflater = null;
    static LayoutInflater moonInflater = null;
    static ViewGroup sunContainer = null;
    static ViewGroup moonContainer = null;
    static AstroCalculator.Location location = new AstroCalculator.Location(widthGeo,heightGeo);
    static AstroDateTime dateTime = new AstroDateTime(
            Integer.parseInt(new SimpleDateFormat("yyyy").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("MM").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("dd").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("HH").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("mm").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("ss").format(System.currentTimeMillis())),
            1,true);


    static AstroCalculator calculator = new AstroCalculator(dateTime,location);
    static AstroCalculator.SunInfo sunInfo = calculator.getSunInfo();
    static AstroCalculator.MoonInfo moonInfo = calculator.getMoonInfo();

   /* static DataProcessor getInstance()
    {
        return d;
    }*/

    static void refresh()
    {
        dateTime = new AstroDateTime(
                Integer.parseInt(new SimpleDateFormat("yyyy").format(System.currentTimeMillis())),
                Integer.parseInt(new SimpleDateFormat("MM").format(System.currentTimeMillis())),
                Integer.parseInt(new SimpleDateFormat("dd").format(System.currentTimeMillis())),
                Integer.parseInt(new SimpleDateFormat("HH").format(System.currentTimeMillis())),
                Integer.parseInt(new SimpleDateFormat("mm").format(System.currentTimeMillis())),
                Integer.parseInt(new SimpleDateFormat("ss").format(System.currentTimeMillis())),
                1,true);
        calculator.setDateTime(dateTime);
        sunInfo = calculator.getSunInfo();
        moonInfo = calculator.getMoonInfo();

    }
    static void update(float width, float height, int timeRefresh)
    {
        widthGeo = width;
        heightGeo = height;
        location.setLatitude(width);
        location.setLongitude(height);
        calculator.setLocation(location);
        DataProcessor.timeRefresh = timeRefresh;
        sunInfo = calculator.getSunInfo();
        moonInfo = calculator.getMoonInfo();
    }

    public static String getSunrise()
    {
        return sunInfo.getSunrise().toString();
    }

    public static String getAzimuthRise()
    {
        return Double.toString(sunInfo.getAzimuthRise());
    }

    public static String getSunset()
    {
        return sunInfo.getSunset().toString();
    }

    public static String getAzimuthSet()
    {
        return Double.toString(sunInfo.getAzimuthSet());
    }

    public static String getTwilightMorning()
    {
        return sunInfo.getTwilightMorning().toString();
    }

    public static String getTwilightEvening()
    {
        return sunInfo.getTwilightEvening().toString();
    }

    public static String getMoonset()
    {
        return moonInfo.getMoonset().toString();
    }

    public static String getMoonrise()
    {
        return moonInfo.getMoonrise().toString();
    }
    public static String getFullMoon()
    {
        return moonInfo.getNextFullMoon().toString();
    }

    public static String getNewMoon()
    {
        return moonInfo.getNextNewMoon().toString();
    }

    public static String getIllumination()
    {
        return Double.toString(moonInfo.getIllumination());
    }

    public static String getAge()
    {
        return Double.toString(moonInfo.getAge());
    }

}

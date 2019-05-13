package com.example.astroweather;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import java.text.SimpleDateFormat;


class DataProcessor {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    static float widthGeo= 51.9194f;
    static float heightGeo= 19.1451f;
    static int timeRefresh = 1;

    private static AstroCalculator.Location location = new AstroCalculator.Location(widthGeo,heightGeo);
    static AstroDateTime dateTime = new AstroDateTime(
            Integer.parseInt(new SimpleDateFormat("yyyy").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("MM").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("dd").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("HH").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("mm").format(System.currentTimeMillis())),
            Integer.parseInt(new SimpleDateFormat("ss").format(System.currentTimeMillis())),
            1,true);


    private static AstroCalculator calculator = new AstroCalculator(dateTime,location);
    private static AstroCalculator.SunInfo sunInfo = calculator.getSunInfo();
    private static AstroCalculator.MoonInfo moonInfo = calculator.getMoonInfo();

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
        calculator = new AstroCalculator(dateTime,location);
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

    static String getSunrise()
    {
        return sunInfo.getSunrise().toString();
    }

    static String getAzimuthRise()
    {
        return Double.toString(sunInfo.getAzimuthRise());
    }

    static String getSunset()
    {
        return sunInfo.getSunset().toString();
    }

    static String getAzimuthSet()
    {
        return Double.toString(sunInfo.getAzimuthSet());
    }

    static String getTwilightMorning()
    {
        return sunInfo.getTwilightMorning().toString();
    }

    static String getTwilightEvening()
    {
        return sunInfo.getTwilightEvening().toString();
    }

    static String getMoonset()
    {
        return moonInfo.getMoonset().toString();
    }

    static String getMoonrise()
    {
        return moonInfo.getMoonrise().toString();
    }
    static String getFullMoon()
    {
        return moonInfo.getNextFullMoon().toString();
    }

    static String getNewMoon()
    {
        return moonInfo.getNextNewMoon().toString();
    }

    static String getIllumination()
    {
        return (moonInfo.getIllumination()*100)+"%";
    }

    static String getAge()
    {
        return Double.toString(moonInfo.getAge());
    }

}

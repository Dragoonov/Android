package com.example.kalkulator;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    private List<String> values;


    private double powerValue=0;
    private boolean isPower = false;
    Processor()
    {
        values = new ArrayList<>();
    }

    public boolean isPower() {
        return isPower;
    }

    public void setPower(boolean power) {
        isPower = power;
    }

    public double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(double powerValue) {
        this.powerValue = powerValue;
    }

    public void addValue(String value)
    {
        values.add(value);
    }

    public void clear()
    {
        values.clear();
    }

    public double compute()
    {
        while(values.size()!=1) {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).equals("*")) {
                    values.set(i - 1, Double.toString(Double.valueOf(values.get(i - 1)) * Double.valueOf(values.get(i + 1))));
                    values.remove(i+1);
                    values.remove(i);
                }
                else if (values.get(i).equals("/")) {
                    values.set(i - 1, Double.toString(Double.valueOf(values.get(i - 1)) / Double.valueOf(values.get(i + 1))));
                    values.remove(i+1);
                    values.remove(i);
                }

            }
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).equals("+")) {
                    values.set(i - 1, Double.toString(Double.valueOf(values.get(i - 1)) + Double.valueOf(values.get(i + 1))));
                    values.remove(i+1);
                    values.remove(i);
                }
                else if (values.get(i).equals("-")) {
                    values.set(i - 1, Double.toString(Double.valueOf(values.get(i - 1)) - Double.valueOf(values.get(i + 1))));
                    values.remove(i+1);
                    values.remove(i);
                }

            }
        }
        double result = Double.valueOf(values.get(0));
        clear();
        return result;
    }

}

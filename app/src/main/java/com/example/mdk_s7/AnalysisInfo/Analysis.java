package com.example.mdk_s7.AnalysisInfo;

public class Analysis {
    public String Title, Time, Cost;

    public Analysis(String t, String tm, String c) {
        Title = t;
        Time = tm;
        Cost = c;
    }

    public int getPrice() {
        return Integer.valueOf(Cost.substring(0, Cost.length() - 2));
    }
    public String getCurrency() {
        return Cost.substring(Cost.length() - 1);
    }
}

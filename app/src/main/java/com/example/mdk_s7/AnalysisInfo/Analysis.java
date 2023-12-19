package com.example.mdk_s7.AnalysisInfo;

public class Analysis {
    public String Title, Time, Cost, Description, Preparing, Material;
    public int num = 1;

    public Analysis(String t, String tm, String c, String d, String p, String m) {
        Title = t;
        Time = tm;
        Cost = c;
        Description = d;
        Preparing = p;
        Material = m;
    }

    public int getPrice() {
        return Integer.valueOf(Cost.substring(0, Cost.length() - 2));
    }
    public String getCurrency() {
        return Cost.substring(Cost.length() - 1);
    }
}

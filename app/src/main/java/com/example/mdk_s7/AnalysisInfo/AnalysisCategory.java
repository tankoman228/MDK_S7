package com.example.mdk_s7.AnalysisInfo;

public class AnalysisCategory {

    public String Name = "UNDEFINED TYPE";
    public AnalysisCategory(String name) {
        Name = name;
    }

    public static AnalysisCategory[] basicAnalysisCategories = new AnalysisCategory[] {
            new AnalysisCategory("Популярные"),
            new AnalysisCategory("Covid"),
            new AnalysisCategory("Комплексные")
    };
}

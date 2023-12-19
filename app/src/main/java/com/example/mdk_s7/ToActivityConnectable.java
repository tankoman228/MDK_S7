package com.example.mdk_s7;

//Нужен для общения AdapterAnalysis с FragmentAnalyse
public interface ToActivityConnectable {
    void sendMeMessage(Object obj);
    void onClickItem(Object obj);
}

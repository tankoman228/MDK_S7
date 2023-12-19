package com.example.mdk_s7;

//Нужен для общения адаптеров с активностями
public interface ToActivityConnectable {
    void sendMeMessage(Object obj);
    void onClickItem(Object obj);
}

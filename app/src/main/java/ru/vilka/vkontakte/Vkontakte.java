package ru.vilka.vkontakte;

import android.app.Application;
import android.content.Context;

import com.vk.sdk.VKSdk;

public class Vkontakte extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        VKSdk.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}

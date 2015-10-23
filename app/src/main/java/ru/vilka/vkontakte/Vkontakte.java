package ru.vilka.vkontakte;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import ru.vilka.vkontakte.activity.LoginActivity;

public class Vkontakte extends Application {

    private static Context context;

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                startActivity(new Intent(Vkontakte.this, LoginActivity.class));
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}

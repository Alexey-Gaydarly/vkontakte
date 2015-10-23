package ru.vilka.vkontakte.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import ru.vilka.vkontakte.R;
import ru.vilka.vkontakte.Vkontakte;

public class MainActivity extends AppCompatActivity {

    VKAccessToken vkAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vkAccessToken = VKAccessToken.tokenFromSharedPreferences(Vkontakte.getContext(), "token");

        if(!VKSdk.wakeUpSession(Vkontakte.getContext())) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

    }
}

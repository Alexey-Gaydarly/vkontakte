package ru.vilka.vkontakte.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import ru.vilka.vkontakte.R;
import ru.vilka.vkontakte.Vkontakte;
import ru.vilka.vkontakte.fragment.FriendFragment;


public class MainActivity extends AppCompatActivity {

    VKAccessToken vkAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FriendFragment friendFragment = new FriendFragment();

        if(!VKSdk.wakeUpSession(Vkontakte.getContext())) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, friendFragment)
                    .commit();


    }
}

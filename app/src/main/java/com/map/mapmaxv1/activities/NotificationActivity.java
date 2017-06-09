package com.map.mapmaxv1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.map.mapmaxv1.R;

/**
 * Created by User on 07.05.2017.
 */

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setTitle("Уведомления");

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Подписки");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Отзывы");
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
    }
}

package com.mj.cab10;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mj.cab10.adapter.YourTripsPagerAdapter;

public class YourTripsActivity extends AppCompatActivity {

    ViewPager pagerYourTrips;
    TabLayout tabs;
    YourTripsPagerAdapter adapter;

    ImageView imgYourTripsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        findViews();

    }

    private void findViews() {
        pagerYourTrips = findViewById(R.id.pagerYourTrips);
        tabs = findViewById(R.id.tabs);
        imgYourTripsBack = findViewById(R.id.imgYourTripsBack);

        adapter = new YourTripsPagerAdapter(getSupportFragmentManager());

        pagerYourTrips.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pagerYourTrips));

        pagerYourTrips.setAdapter(adapter);

        imgYourTripsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

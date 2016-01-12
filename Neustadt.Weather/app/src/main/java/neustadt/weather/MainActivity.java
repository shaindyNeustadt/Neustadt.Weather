package neustadt.weather;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        String[] locations = new String[2];
        locations[0] ="08817";
        locations[1] ="08701";
        LocationsPagerAdapter pagerAdapter = new LocationsPagerAdapter(locations, this);
        viewPager.setAdapter(pagerAdapter);
        }
}


package neustadt.weather;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ArrayList<String> locations = new ArrayList<String>();
        locations.add("08817");
       // locations.add("08701");
        LocationsPagerAdapter pagerAdapter = new LocationsPagerAdapter(locations, this);
        viewPager.setAdapter(pagerAdapter);
    }
}


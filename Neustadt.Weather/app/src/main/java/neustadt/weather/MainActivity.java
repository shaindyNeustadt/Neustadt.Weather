package neustadt.weather;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SharedPreferences preferences;
    private ArrayList<String> locations;
    LocationsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        locations = new ArrayList<>();
        preferences = this.getSharedPreferences("DEFAULT", MODE_PRIVATE);
        pagerAdapter = new LocationsPagerAdapter(locations, this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        StringBuilder builder = new StringBuilder();
        for (String l : locations) {
            builder.append(l + " ");
        }
        editor.putString("LOCATIONS", builder.toString());
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        String temp1 = preferences.getString("LOCATIONS", "08817");
        String[] temp2 = temp1.split(" ");
        for (String s : temp2) {
            locations.add(s);
            pagerAdapter.notifyDataSetChanged();
        }
    }
}


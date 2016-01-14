package neustadt.weather;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationsPagerAdapter extends PagerAdapter {
    private ArrayList<String> locations;
    private Context context;
    private TextView time;
    private TextView city;
    private WeatherService service1;
    private CurrentWeatherService service2;
    private ImageView background;
    private EditText zipcode;
    private Button button;

    public LocationsPagerAdapter(ArrayList<String> locations, Context context) {
        this.locations = locations;
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service1 = retrofit.create(WeatherService.class);
        service2 = retrofit.create(CurrentWeatherService.class);
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.locations_pager_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(layoutManager);

        background = (ImageView) view.findViewById(R.id.background);
        Picasso.with(context).load("http://lorempixel.com/600/827/city").memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE).into(background);

        button = (Button) view.findViewById(R.id.button);
        button.setText("+");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialogInflater = LayoutInflater.from(context);
                View dialogView = dialogInflater.inflate(R.layout.zipcode_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Enter Zipcode:");
                builder.setView(dialogView);
                zipcode = (EditText) dialogView.findViewById(R.id.zipcode);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        locations.add(String.valueOf(zipcode.getText()));
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        city = (TextView) view.findViewById(R.id.city);
        city.setText(locations.get(position));
        time = (android.widget.TextClock) view.findViewById(R.id.time);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Date today = new Date();
        time.setText(sdf.format(today).toString());

        HashMap<String, String> map = new HashMap<>();
        map.put("zip", locations.get(position));
        map.put("appid", "bfe3377fe84c32565da01db51fc8f33c");
        map.put("units", "imperial");

        final ArrayList<Object> list = new ArrayList<>();

        Call<CurrentWeather> callCurrent = service2.currentWeatherInfo(map);
        callCurrent.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                list.add(0, currentWeather);
                city.setText(currentWeather.getName());
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        map.put("cnt", "16");
        Call<WeatherList> call = service1.listWeather(map);
        call.enqueue(new Callback<WeatherList>() {

            @Override
            public void onResponse(Response<WeatherList> response) {
                WeatherList weatherList = response.body();
                ListItem[] listItems = weatherList.getList();
                for (ListItem item : listItems) {
                    list.add(item);
                }
                DisplayWeatherAdapter adapter = new DisplayWeatherAdapter(list, context);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
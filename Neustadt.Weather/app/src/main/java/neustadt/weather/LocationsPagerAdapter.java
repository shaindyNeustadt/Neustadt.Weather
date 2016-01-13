package neustadt.weather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationsPagerAdapter extends PagerAdapter {
    private List<String> locations;
    private Context context;
    private ListItem[] listItems;
    private TextView time;
    private TextView zip;
    private WeatherService service;
    private ImageView background;
    //lorempixel.com/400/800/city/

    public LocationsPagerAdapter(List<String> locations, Context context) {
        this.locations = locations;
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherService.class);
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

        background = (ImageView)view.findViewById(R.id.background);
        Picasso.with(context).load("http://lorempixel.com/600/820/nature").into(background);

        zip = (TextView) view.findViewById(R.id.zip);
        zip.setText(locations.get(position));
        time = (android.widget.TextClock) view.findViewById(R.id.time);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Date today = new Date();
        time.setText(sdf.format(today).toString());

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("zip", locations.get(position));
        map.put("appid", "bfe3377fe84c32565da01db51fc8f33c");
        map.put("units", "imperial");
        map.put("cnt", "16");

        Call<WeatherList> call = service.listWeather(map);
        call.enqueue(new Callback<WeatherList>() {

            @Override
            public void onResponse(Response<WeatherList> response) {
                WeatherList weatherList = response.body();
                listItems = weatherList.getList();
                List<ListItem> list = new ArrayList<ListItem>();
                list.add(listItems[0]);
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

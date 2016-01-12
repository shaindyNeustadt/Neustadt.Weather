package neustadt.weather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationsPagerAdapter extends PagerAdapter {
    private String[] locations;
    private Context context;
    private RecyclerView recyclerView;
    private ListItem[] listItems;
    private TextView time;
    private EditText zip;

    public LocationsPagerAdapter(String[] locations, Context context) {
        this.locations = locations;
        this.context = context;
    }

    @Override
    public int getCount() {
        return locations.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.locations_pager_item, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
      LinearLayoutManager layoutManager = new LinearLayoutManager(context);
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       recyclerView.setLayoutManager(layoutManager);
        zip = (EditText) view.findViewById(R.id.zip);
        zip.setText("08817");
        //  String currState = zip.getText().toString();

        time = (android.widget.TextClock) view.findViewById(R.id.time);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Date today = new Date();
        time.setText(sdf.format(today).toString());

        String currZip = "08817";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherList> call = service.listWeather(currZip);
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

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, context);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        container.addView(view);
        return view;
       // return container.getContext();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

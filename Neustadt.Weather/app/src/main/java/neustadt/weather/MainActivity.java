package neustadt.weather;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WList[] wList;
    private EditText state;
    private TextView time;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

       // state = (EditText) findViewById(R.id.state);
      //  String currState = state.getText().toString();
        time = (EditText) findViewById(R.id.time);
        //  time.setText(new TextClock(MainActivity.this).getFormat12Hour());
        time.setText("CURRENT TIME:");
       // image = (ImageView) findViewById(R.id.image);
       // Picasso.with(this).load("http://1.bp.blogspot.com/_OcFy7516YV4/SfM8BdpqXeI/AAAAAAAAA5w/P0UZJHKYKYg/s1600/owners.ocean.view").into(image);

/*
    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id = "@+id/image"
        />


  */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherList> call = service.listWeather();
        call.enqueue(new Callback<WeatherList>() {


            @Override
            public void onResponse(Response<WeatherList> response) {
                WeatherList weatherList = response.body();
                wList = weatherList.getList();
                List<WList> list = new ArrayList<WList>();
                list.add(wList[0]);
                for(WList w: wList){
                    list.add(w);
                }


                RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, MainActivity.this);
                recyclerView.setAdapter(adapter);


                }

            @Override
            public void onFailure(Throwable t) {
            }
        });


    }


}


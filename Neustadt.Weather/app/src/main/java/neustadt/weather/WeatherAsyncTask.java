package neustadt.weather;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAsyncTask extends AsyncTask<Void, Void, ListItem[]> {
private ListItem[] currentWeather;
private RecyclerView recyclerView;

public WeatherAsyncTask(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        }

@Override
protected ListItem[] doInBackground(Void... params) {
        try {
            URL url = new URL(
                    "http://api.openweathermap.org/data/2.5/forecast/daily?q="
                          //  + city.getText()
                            + "edison"
                            + ",us&appid=bfe3377fe84c32565da01db51fc8f33c&units=imperial&cnt=16");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));

            Gson gson = new GsonBuilder().create();
            WeatherList wList = gson.fromJson(reader, WeatherList.class);
            currentWeather = wList.getList();
        } catch (IOException e) {
        e.printStackTrace();
        }
        return currentWeather;
        }

protected void onPostExecute(ListItem[] currentWeather){
        super.onPostExecute(currentWeather);
      // WeatherAdapter adapter = new WeatherAdapter(currentWeather);
      //  recyclerView.setAdapter(adapter);
        }
        }



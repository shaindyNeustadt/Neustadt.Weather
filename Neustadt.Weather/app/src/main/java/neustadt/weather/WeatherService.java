package neustadt.weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherService {

    @GET("/data/2.5/forecast/daily?")
    Call<WeatherList> listWeather(@QueryMap Map<String, String> zip);
}

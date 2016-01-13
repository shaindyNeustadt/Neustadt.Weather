package neustadt.weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface CurrentWeatherService {

    @GET("/data/2.5/weather?")
    Call<CurrentWeather> currentWeatherInfo(@QueryMap Map<String, String> zip);
}

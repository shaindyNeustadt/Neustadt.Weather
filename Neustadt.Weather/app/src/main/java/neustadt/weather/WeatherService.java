package neustadt.weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("/data/2.5/forecast/daily?q=edison,us&appid=bfe3377fe84c32565da01db51fc8f33c&units=imperial&cnt=16")
    Call<WeatherList> listWeather();
}

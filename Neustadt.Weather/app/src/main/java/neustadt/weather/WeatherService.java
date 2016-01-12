package neustadt.weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
//import retrofit.http.Path;

public interface WeatherService {

   @GET("/data/2.5/forecast/daily?zip={zip},us&appid=bfe3377fe84c32565da01db51fc8f33c&units=imperial&cnt=16")

   Call<WeatherList> listWeather(@Path("zip") String zip);
}

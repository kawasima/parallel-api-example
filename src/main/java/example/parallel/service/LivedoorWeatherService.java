package example.parallel.service;

import example.parallel.model.WeatherForecasts;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * @author kawasima
 */
public interface LivedoorWeatherService {
    @GET("/forecast/rss/area/{areaCd}.xml")
    Call<WeatherForecasts> list(@Path("areaCd") String areaCd);

    @GET("/forecast/rss/area/{areaCd}.xml")
    Observable<WeatherForecasts> listRx(@Path("areaCd") String areaCd);
}

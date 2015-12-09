package example.parallel;

import example.parallel.model.BookmarkEntries;
import example.parallel.model.WeatherForecasts;
import example.parallel.service.HatenaHotentryService;
import example.parallel.service.LivedoorWeatherService;
import org.junit.Test;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;

import java.io.IOException;

/**
 * @author kawasima
 */
public class ApiTest {
    @Test
    public void hatebu() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://b.hatena.ne.jp")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        HatenaHotentryService hatenaHotentryService = retrofit.create(HatenaHotentryService.class);
        Response<BookmarkEntries> response = hatenaHotentryService.list().execute();
        System.out.println(response.body());
    }

    @Test
    public void livedoorWeather() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://weather.livedoor.com")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        LivedoorWeatherService livedoorWeather= retrofit.create(LivedoorWeatherService.class);
        Response<WeatherForecasts> response = livedoorWeather.list("130010").execute();
        System.out.println(response.body());
    }

}

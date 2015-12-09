package example.parallel.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.parallel.service.HatenaHotentryService;
import example.parallel.service.LivedoorWeatherService;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author kawasima
 */
@WebServlet
public class ApiBaseServlet extends HttpServlet {
    protected HatenaHotentryService hatenaHotentryService;
    protected LivedoorWeatherService livedoorWeatherService;
    private ObjectMapper objectMapper;


    @Override
    public void init(ServletConfig config) {
        objectMapper = new ObjectMapper();

        hatenaHotentryService  = new Retrofit.Builder()
                .baseUrl("http://b.hatena.ne.jp")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(HatenaHotentryService.class);

        livedoorWeatherService = new Retrofit.Builder()
                .baseUrl("http://weather.livedoor.com")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(LivedoorWeatherService.class);
    }

    protected String toJson(Object... objs) throws IOException {
        try {
            return objectMapper.writeValueAsString(objs);
        } catch (JsonProcessingException e) {
            throw new IOException(e);
        }
    }
}

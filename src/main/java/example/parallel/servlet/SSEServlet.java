package example.parallel.servlet;

import example.parallel.model.BookmarkEntries;
import example.parallel.model.OkMessage;
import example.parallel.model.WeatherForecasts;
import example.parallel.service.SlowApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.Executors;

/**
 * http://stackoverflow.com/questions/10878243/sse-and-servlet-3-0
 *
 * @author kawasima
 */
@WebServlet(urlPatterns = "/sse", asyncSupported = true)
public class SSEServlet extends ApiBaseServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ApiBaseServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        final AsyncContext ac = request.startAsync();
        ac.setTimeout(60 * 1000);

        Executors.newSingleThreadExecutor().submit((Runnable) () -> {
            try {
                OkMessage okMessage = new SlowApiService().get().get();
                Writer writer = ac.getResponse().getWriter();
                writer.write("data: " + toJson(okMessage) + "\n\n");
                writer.flush();

            } catch (Exception e) {
                LOG.error("Slow api error", e);
            }
        });

        hatenaHotentryService.list().enqueue(new Callback<BookmarkEntries>() {
            @Override
            public void onResponse(Response<BookmarkEntries> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    try {
                        Writer writer = ac.getResponse().getWriter();
                        writer.write("data: " + toJson(response.body()) + "\n\n");
                        writer.flush();
                    } catch (IOException e) {
                        LOG.error("Hatena Bookmark access error", e);
                    }
                } else {
                    LOG.error("Hatena Bookmark access error", response.message());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Hatena Bookmark access error", throwable);
            }
        });

        livedoorWeatherService.list("130010").enqueue(new Callback<WeatherForecasts>() {
            @Override
            public void onResponse(Response<WeatherForecasts> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    try {
                        Writer writer = ac.getResponse().getWriter();
                        writer.write("data: " + toJson(response.body()) + "\n\n");
                        writer.flush();
                    } catch (IOException e) {
                        LOG.error("Livedoor weather access error", e);
                    }
                } else {
                    LOG.error("Livedoor weather access error", response.message());
                }

            }

            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Livedoor weather access error", throwable);

            }
        });
    }
}

package example.parallel.servlet;

import example.parallel.model.BookmarkEntries;
import example.parallel.model.OkMessage;
import example.parallel.model.WeatherForecasts;
import example.parallel.service.SlowApiService;
import retrofit.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kawasima
 */
@WebServlet(urlPatterns = "/sequentialsync")
public class SequentialSyncServlet extends ApiBaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            OkMessage okMessage = new SlowApiService().get().get();
            BookmarkEntries bookmarkEntries = getBookmarkEntries();
            WeatherForecasts weatherForecasts = getWeatherForecasts();

            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(toJson(bookmarkEntries, weatherForecasts, okMessage));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    protected BookmarkEntries getBookmarkEntries() throws IOException {
        Response<BookmarkEntries> response = hatenaHotentryService.list().execute();
        if (response.isSuccess()) {
            return response.body();
        } else {
            throw new IOException(response.message());
        }
    }

    protected WeatherForecasts getWeatherForecasts() throws IOException {
        Response<WeatherForecasts> response = livedoorWeatherService.list("130010").execute();
        if (response.isSuccess()) {
            return response.body();
        } else {
            throw new IOException(response.message());
        }
    }


}

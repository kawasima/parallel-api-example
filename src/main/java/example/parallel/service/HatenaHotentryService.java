package example.parallel.service;

import example.parallel.model.BookmarkEntries;
import retrofit.Call;
import retrofit.http.GET;
import rx.Observable;

/**
 * @author kawasima
 */
public interface HatenaHotentryService {
    @GET("/hotentry?mode=rss")
    Call<BookmarkEntries> list();

    @GET("/hotentry?mode=rss")
    Observable<BookmarkEntries> listRx();
}

package example.parallel.service;

import example.parallel.model.OkMessage;
import rx.Observable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author kawasima
 */
public class SlowApiService {
    private ExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public Future<OkMessage> get() {
        return service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }, new OkMessage());
    }

    public Observable<OkMessage> getRx() {
        return Observable.from(get());
    }
}

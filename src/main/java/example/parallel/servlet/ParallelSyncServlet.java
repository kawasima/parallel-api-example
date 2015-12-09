package example.parallel.servlet;

import example.parallel.model.ResponseRoot;
import example.parallel.service.SlowApiService;
import rx.Observable;
import rx.Subscriber;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kawasima
 */
@WebServlet(urlPatterns = "/parallelsync")
public class ParallelSyncServlet extends ApiBaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Observable<? extends ResponseRoot>> responseObservables = new ArrayList<>();
        responseObservables.add(hatenaHotentryService.listRx());
        responseObservables.add(livedoorWeatherService.listRx("130010"));
        responseObservables.add(new SlowApiService().getRx());

        List<ResponseRoot> responseRoots = new ArrayList<>();

        Observable.merge(responseObservables).subscribe(responseRoot -> {
            responseRoots.add(responseRoot);
        });
        response.getWriter().write(toJson(responseRoots.toArray()));
    }
}

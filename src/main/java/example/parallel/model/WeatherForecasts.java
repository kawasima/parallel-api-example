package example.parallel.model;

import lombok.Data;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author kawasima
 */
@Root(name = "rss", strict = false)
@Data
public class WeatherForecasts implements ResponseRoot {
    @ElementList(name ="item", inline = true)
    @Path("channel")
    private List<WeatherForecast> forecasts;
}

package example.parallel.model;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author kawasima
 */
@Root(name = "item", strict = false)
@Data
public class WeatherForecast {
    @Element(name = "title")
    private String tile;
    @Element(name = "description")
    private String description;
}

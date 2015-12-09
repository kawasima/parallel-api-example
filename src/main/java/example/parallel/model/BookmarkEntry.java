package example.parallel.model;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author kawasima
 */
@Data
@Root(name = "item", strict = false)
public class BookmarkEntry {
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "description", required = false)
    private String description;
}

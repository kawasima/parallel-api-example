package example.parallel.model;

import lombok.Data;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author kawasima
 */
@Namespace(prefix = "rdf")
@Root(name = "RDF", strict=false)
@Data
public class BookmarkEntries implements ResponseRoot {
    @ElementList(name = "item", inline = true)
    private List<BookmarkEntry> entries;
}

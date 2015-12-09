package example.parallel.model;

import lombok.Data;

/**
 * @author kawasima
 */
@Data
public class OkMessage implements ResponseRoot {
    private String message = "OK";
}

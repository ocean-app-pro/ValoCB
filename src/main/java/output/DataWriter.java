package output;

import java.math.BigDecimal;
import java.util.Map;

public interface DataWriter {

    void write(Map<String, BigDecimal> data, String path, String[] header);
}

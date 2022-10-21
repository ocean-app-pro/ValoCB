package output;

import com.opencsv.CSVWriter;
import lombok.extern.log4j.Log4j2;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Log4j2
public class ToCsvWriter implements DataWriter {

    @Override
    public void write(Map<String, BigDecimal> data, String path, String[] header) {
        try {
            FileWriter outputfile = new FileWriter(path);
            CSVWriter writer = new CSVWriter(outputfile);
            log.info("Starting to write to {}", path);
            writer.writeNext(header, false);
            data.forEach((x,y) -> {
                String [] tmp = {x, y.toString()};
                writer.writeNext(tmp, false);
            });
            writer.close();
            log.info("Finished writing {} lines to {}", data.size(), path);

        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

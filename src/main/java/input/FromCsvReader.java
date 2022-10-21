package input;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
public class FromCsvReader implements DataReader {
    private static final Pattern numericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public List<List<String>> getData(String path, int expectedSize, int numberIndex){
        String absPath = Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getPath();
        List<List<String>> records = new ArrayList<>();

        try(CSVReader reader = new CSVReaderBuilder(new FileReader(absPath)).build()) {
            String[] values ;
            while ((values = reader.readNext()) != null) {
                List<String> line = Arrays.asList(values);
                    records.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records.stream()
                .filter(x -> isParsableRow(x, expectedSize, numberIndex))
                .collect(Collectors.toList());
    }


    private boolean isParsableRow(List<String> row, int expectedSize, int numberIndex) {
        return (row.size() == expectedSize) && isFilled(row) && isNumericValue(row, numberIndex)
                && isPositive(row, numberIndex);
    }
    private boolean isFilled(List<String> row) {
        for (var s : row) {
            if(s.isBlank()){
                log.warn("Could not convert entry to proper values, skipping row : {}", row);

                return false;
            }
        }
        return true;
    }
    private boolean isNumericValue(List<String> row, int index) {
        if(!numericPattern.matcher(row.get(index)).matches()) {
            log.warn("Could not convert {} to number, skipping row : {}", row.get(index), row);
            return false;
        }
        return true;
    }
    private boolean isPositive(List<String> row, int index) {
        if(new BigDecimal(row.get(index)).signum() <= 0) {
            log.warn("Could not convert {} to positive value, skipping row : {}", row.get(index), row);
            return false;
        }
        return true;
    }
}
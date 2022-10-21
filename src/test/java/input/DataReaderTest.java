package input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DataReaderTest {

    @Test
    public void test_file_read_correctly(){
        DataReader dataReader = new FromCsvReader();
        List<List<String>> result = dataReader.getData("TestInput.csv", 3, 2);
        Assertions.assertEquals(1, result.size());
    }
}

package output;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataWriterTest {

    static DataWriter dataWriter;
    @BeforeAll
    public static void init() {
        dataWriter = new ToCsvWriter();
    }

    @Test
    public void is_able_to_write_writer() throws IOException {
        Map<String, BigDecimal> data = new HashMap<>();
        String path = "src/test/resources/Writer-test.csv";
        String[] header = {"key", "value"};
        data.put("A", BigDecimal.ZERO);
        data.put("B", BigDecimal.TEN);
        dataWriter.write(data, path,header);
        Assertions.assertTrue(new File(path).exists());
        String s = Files.readString(Paths.get(path));
        System.out.println(s);
        List<String> res = Arrays.asList(s.split("\n"));
        Assertions.assertEquals(3, res.size());
        Assertions.assertEquals(res.get(1).split(",")[0], "A");
        Assertions.assertEquals(res.get(2).split(",")[1], "10");

    }
}

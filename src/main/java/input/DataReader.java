package input;

import java.util.List;

public interface DataReader {
    List<List<String>> getData(String path,  int expectedSize, int numberIndex);
}

package org.example.junitcleancode.Interface;

import java.io.IOException;
import java.util.List;

public interface CSVReader {
    List<List<String>> readEntireCSVFile() throws IOException;
    String[] splitLine(String line);
}

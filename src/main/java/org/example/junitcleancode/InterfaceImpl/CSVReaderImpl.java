package org.example.junitcleancode.InterfaceImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.example.junitcleancode.Interface.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CSVReaderImpl implements CSVReader {
    private String path;

    public CSVReaderImpl(String path) {
        this.path = path;
    }

    public static List<List<String>> getContentsOfCSVFile(String path) throws IOException {
        return new CSVReaderImpl(path).readEntireCSVFile();
    }

    @Override
    public List<List<String>> readEntireCSVFile() throws IOException {
        List<List<String>> csvLines = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            csvLines.add(List.of(splitLine(line)));
        }

        return csvLines;
    }

    @Override
    public String[] splitLine(String line) {
        return line.replace(" ", "").split(",");
    }
}

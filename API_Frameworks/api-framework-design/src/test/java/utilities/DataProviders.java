
package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataProviders {

    // JSON DataProvider
    @DataProvider(name = "jsonDataProvider")
    public Object[][] jsonDataProvider() throws IOException {

        Path filePath = Path.of(".\\src\\test\\resources\\test-data\\loginData.json");

        if (!Files.exists(filePath)) {
            throw new IOException("JSON file not found: " + filePath);
        }

        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, Object>> dataList = mapper.readValue(
                filePath.toFile(),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        if (dataList.isEmpty()) {
            throw new IllegalArgumentException("JSON file contains no test data");
        }

        Object[][] dataArray = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }

        return dataArray;
    }


    // CSV DataProvider
    @DataProvider(name = "csvDataProvider")
    public Object[][] csvDataProvider()
            throws IOException, CsvValidationException {

        Path filePath = Path.of(
                System.getProperty("user.dir"),
                "test-data",
                "product.csv"
        );

        if (!Files.exists(filePath)) {
            throw new IOException("CSV file not found: " + filePath);
        }

        List<Map<String, String>> dataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(
                Files.newBufferedReader(filePath))) {

            // Read column headers
            String[] headers = reader.readNext();

            if (headers == null || headers.length == 0) {
                throw new IllegalArgumentException("CSV file has no headers");
            }

            Set<String> uniqueHeaders = new HashSet<>();

            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim();

                if (headers[i].isEmpty() ||
                        !uniqueHeaders.add(headers[i])) {
                    throw new IllegalArgumentException(
                            "Empty or duplicate CSV header: " + headers[i]);
                }
            }

            // Read data rows
            String[] row;

            while ((row = reader.readNext()) != null) {

                // Skip completely empty rows
                boolean emptyRow = true;

                for (String value : row) {
                    if (value != null && !value.isBlank()) {
                        emptyRow = false;
                        break;
                    }
                }

                if (emptyRow) {
                    continue;
                }

                if (row.length != headers.length) {
                    throw new IllegalArgumentException(
                            "CSV column count mismatch. Expected: "
                            + headers.length + ", Actual: " + row.length);
                }

                Map<String, String> rowData = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    rowData.put(headers[i], row[i]);
                }

                dataList.add(rowData);
            }
        }

        if (dataList.isEmpty()) {
            throw new IllegalArgumentException("CSV file contains no test data");
        }

        // Convert List<Map<String, String>> to Object[][]
        Object[][] dataArray = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }

        return dataArray;
    }
}

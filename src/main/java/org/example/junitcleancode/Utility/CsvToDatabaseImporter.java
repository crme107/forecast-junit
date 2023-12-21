package org.example.junitcleancode.Utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.junitcleancode.InterfaceImpl.CSVReaderImpl.getContentsOfCSVFile;

public class CsvToDatabaseImporter {
    private static final String CSV_FILE_PATH = "src/main/resources/weather.csv";

    private static final String SELECT_FORECAST_ID = "SELECT id FROM FORECAST WHERE forecast_date = ?";

    private static final String DELETE_FROM_FORECAST = "DELETE FROM FORECAST";
    private static final String DELETE_FROM_WEATHER = "DELETE FROM WEATHER";

    private static final String INSERT_INTO_FORECAST = "INSERT INTO FORECAST (forecast_date) "
            + "VALUES (?)";

    private static final String INSERT_INTO_WEATHER = "INSERT INTO WEATHER"
            + "(time, is_raining, wind_speed, temperature, clouds_percentage, visibility, pressure, forecast_fk) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static void importCsvFileIntoDatabase() throws IOException {
        List<List<String>> csvValues = getContentsOfCSVFile(CSV_FILE_PATH);

        csvValues.forEach(row -> {
            insertCsvIntoForecast(row);
            insertCsvIntoWeather(row);
        });
    }

    private static void insertCsvIntoForecast(List<String> row) {
        try (Connection connection = ConnectionManager.createConnection()) {
            String forecastDate = row.get(0);

            PreparedStatement forecastIdStatement = connection.prepareStatement(SELECT_FORECAST_ID);
            forecastIdStatement.setString(1, forecastDate);
            ResultSet forecastExists = forecastIdStatement.executeQuery();

            if (!forecastExists.next()) {
                PreparedStatement forecastStatement = connection.prepareStatement(INSERT_INTO_FORECAST);
                forecastStatement.setString(1, forecastDate);
                forecastStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertCsvIntoWeather(List<String> row) {
        try (Connection connection = ConnectionManager.createConnection()) {
            String forecastDate = row.get(0);
            String weatherTime = row.get(1);
            boolean isRaining = Boolean.parseBoolean(row.get(2));
            int windSpeed = Integer.parseInt(row.get(3));
            int temperature = Integer.parseInt(row.get(4));
            int cloudsPercentage = Integer.parseInt(row.get(5));
            int visibility = Integer.parseInt(row.get(6));
            int pressure = Integer.parseInt(row.get(7));

            PreparedStatement weatherStatement = connection.prepareStatement(INSERT_INTO_WEATHER);
            weatherStatement.setString(1, weatherTime);
            weatherStatement.setBoolean(2, isRaining);
            weatherStatement.setInt(3, windSpeed);
            weatherStatement.setInt(4, temperature);
            weatherStatement.setInt(5, cloudsPercentage);
            weatherStatement.setInt(6, visibility);
            weatherStatement.setInt(7, pressure);

            PreparedStatement forecastIdStatement = connection.prepareStatement(SELECT_FORECAST_ID);
            forecastIdStatement.setString(1, forecastDate);
            ResultSet forecastResultSet = forecastIdStatement.executeQuery();

            int forecastId = -1;
            while (forecastResultSet.next()) {
                forecastId = forecastResultSet.getInt("id");
            }

            if (forecastId != -1) {
                weatherStatement.setInt(8, forecastId);
            }

            weatherStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resetDatabase() {
        try (Connection connection = ConnectionManager.createConnection()) {
            PreparedStatement weatherStatement = connection.prepareStatement(DELETE_FROM_WEATHER);
            weatherStatement.executeUpdate();

            PreparedStatement forecastStatement = connection.prepareStatement(DELETE_FROM_FORECAST);
            forecastStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

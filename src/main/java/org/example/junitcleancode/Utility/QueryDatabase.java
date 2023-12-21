package org.example.junitcleancode.Utility;

import org.example.junitcleancode.Entity.Forecast;
import org.example.junitcleancode.Entity.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class QueryDatabase {
    private static final String QUERY_FORECAST = "select * from FORECAST";

    private static final String QUERY_WEATHER =
            "select time, is_raining, wind_speed, temperature, clouds_percentage, visibility, pressure from WEATHER";

    private static final String QUERY_WEATHER_FOR_ONE_FORECAST =
            "select w.time, w.is_raining, w.wind_speed, "
                    + "w.temperature, w.clouds_percentage, w.visibility, w.pressure "
                    + "from FORECAST f join WEATHER w on w.forecast_fk = f.id "
                    + "where f.forecast_date = ?";

    private static List<Weather> getWeatherFromResultSet(ResultSet weatherResultSet) throws SQLException {
        List<Weather> weatherList = new ArrayList<>();

        while (weatherResultSet.next()) {
            String time = weatherResultSet.getString("time");
            String[] timeSplit = time.split(":");
            int hour = Integer.parseInt(timeSplit[0]);
            int minute = Integer.parseInt(timeSplit[1]);
            LocalTime weatherTime = LocalTime.of(hour, minute);

            boolean isRaining = weatherResultSet.getBoolean("is_raining");
            int windSpeed = weatherResultSet.getInt("wind_speed");
            int temperature = weatherResultSet.getInt("temperature");
            int cloudsPercentage = weatherResultSet.getInt("clouds_percentage");
            int visibility = weatherResultSet.getInt("visibility");
            int pressure = weatherResultSet.getInt("pressure");

            Weather weather = new Weather(weatherTime, isRaining, windSpeed, temperature, cloudsPercentage, visibility, pressure);
            weatherList.add(weather);
        }

        return weatherList;
    }

    public static List<Weather> queryAllWeather() {
        try (Connection connection = ConnectionManager.createConnection()) {
            PreparedStatement weatherStatement = connection.prepareStatement(QUERY_WEATHER);
            ResultSet resultSet = weatherStatement.executeQuery();
            return getWeatherFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Forecast> queryCompleteForecastFromDatabase() {
        try (Connection connection = ConnectionManager.createConnection()) {
            List<Forecast> forecastList = new ArrayList<>();
            PreparedStatement queryAll = connection.prepareStatement(QUERY_FORECAST);
            ResultSet queryResultSet = queryAll.executeQuery();

            while (queryResultSet.next()) {
                String date = queryResultSet.getString("forecast_date");
                String[] dateSplit = date.split("/");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                int day = Integer.parseInt(dateSplit[2]);
                LocalDate forecastDate = LocalDate.of(year, month, day);

                PreparedStatement queryWeatherForForecast = connection.prepareStatement(QUERY_WEATHER_FOR_ONE_FORECAST);
                queryWeatherForForecast.setString(1, date);
                ResultSet weatherResultSet = queryWeatherForForecast.executeQuery();

                Forecast forecast = new Forecast();
                forecast.setDate(forecastDate);
                forecast.setWeatherList(getWeatherFromResultSet(weatherResultSet));

                forecastList.add(forecast);
            }

            return forecastList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
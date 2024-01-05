package org.example.junitcleancode;

import org.example.junitcleancode.Entity.Forecast;
import org.example.junitcleancode.Entity.Weather;
import org.example.junitcleancode.Utility.WeatherRanker;

import java.io.IOException;
import java.util.List;

import static org.example.junitcleancode.Utility.CsvToDatabaseImporter.importCsvFileIntoDatabase;
import static org.example.junitcleancode.Utility.CsvToDatabaseImporter.resetDatabase;
import static org.example.junitcleancode.Utility.QueryDatabase.queryCompleteForecastFromDatabase;
import static org.example.junitcleancode.Utility.QueryDatabase.queryAllWeather;

public class Main {
    public static void main(String[] args) throws IOException {
        resetDatabase();
        importCsvFileIntoDatabase();

        Forecast forecast = new Forecast();
        System.out.println(forecast);

        printForecast();
        printWeatherLeaderboard();
    }

    private static void printForecast() {
        List<Forecast> forecastList = queryCompleteForecastFromDatabase();
        System.out.println("[ Entire available forecast ]\n");
        forecastList.forEach(Forecast::printNiceForecast);
    }

    private static void printWeatherLeaderboard() {
        List<Weather> weatherList = queryAllWeather();
        WeatherRanker.rankWeather(weatherList);
    }
}
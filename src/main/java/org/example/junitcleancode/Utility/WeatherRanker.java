package org.example.junitcleancode.Utility;

import org.example.junitcleancode.Entity.Weather;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeatherRanker {
    public static void rankWeather(List<Weather> weatherList) {
        System.out.println("[ Weather rankings ]\n");

        weatherList.sort(Comparator.comparing(Weather::calculateWeatherRating));
        Collections.reverse(weatherList);

        printWeatherLeaderboard(weatherList);
    }

    public static void printWeatherLeaderboard(List<Weather> weatherList) {
        weatherList.forEach(weather ->
                System.out.println("Rank " + (weatherList.indexOf(weather) + 1) + '\n' + weather)
        );
    }
}

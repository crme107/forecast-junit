package org.example.junitcleancode.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Forecast {
    private static final String DATE_FORMAT = "uuuu/MM/dd";

    private LocalDate date;
    private List<Weather> weatherList = new ArrayList<>();

    public Forecast(LocalDate date, List<Weather> weatherList) {
        this.weatherList = weatherList;

        if (isValidDate(date)) {
            this.date = date;
        } else {
            this.date = LocalDate.now();
        }
    }

    public boolean isValidDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        int futureYearLimit = currentDate.getYear() + 1;

        boolean isNotBeforeCurrentDate = !date.isBefore(currentDate);
        boolean isWithinFutureYearLimit = date.getYear() < futureYearLimit;

        return isNotBeforeCurrentDate && isWithinFutureYearLimit;
    }

    public String formatDate(String format) {
        return getDate().format(DateTimeFormatter.ofPattern(format));
    }

    public void printNiceForecast() {
        StringBuilder stringBuilder = new StringBuilder("\t[ " + formatDate(DATE_FORMAT) + " ]\n");
        List<Weather> sortedWeather = weatherList;
        sortedWeather.sort(Comparator.comparing(Weather::getTime));

        for (Weather weather : sortedWeather) {
            stringBuilder.append(weather.toString());
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }
}

package org.example.junitcleancode.Entity;

import org.example.junitcleancode.InterfaceImpl.ComfortLevelCalculatorImpl;
import org.example.junitcleancode.InterfaceImpl.WeatherValidatorImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.example.junitcleancode.Utility.Range.valueInRange;

@Getter
@Setter
@NoArgsConstructor
public class Weather {
    private static final String _12_HOUR_TIME_FORMAT = "h:mm a";

    private LocalTime time;
    private boolean isRaining;
    private int windSpeed;
    private int temperature;
    private int cloudsPercentage;
    private long visibility;
    private int pressure;

    private WeatherValidatorImpl validator = new WeatherValidatorImpl();
    private ComfortLevelCalculatorImpl comfortCalculator = new ComfortLevelCalculatorImpl();

    public Weather(LocalTime time, boolean isRaining, int windSpeed, int temperature, int cloudsPercentage, long visibility, int pressure) {
        this.time = time;
        this.isRaining = isRaining;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.cloudsPercentage = cloudsPercentage;
        this.visibility = visibility;
        this.pressure = pressure;
    }

    public boolean isValidWeather() {
        boolean isWindValid = validator.isValidWindSpeed(windSpeed);
        boolean isPressureValid = validator.isValidPressure(pressure);
        boolean areCloudsValid = validator.isValidCloudsPercentage(cloudsPercentage);
        boolean isVisibilityValid = validator.isValidVisibility(visibility);
        boolean isTemperatureValid = validator.isValidTemperature(temperature);

        return isWindValid && areCloudsValid && isPressureValid && isVisibilityValid && isTemperatureValid;
    }

    public boolean isGoodForWalking() {
        final int TEMPERATURE_BASELINE = 15;
        final int TEMPERATURE_THRESHOLD = 30;
        final int WIND_SPEED_BASELINE = 0;
        final int WIND_SPEED_THRESHOLD = 3;
        final int CLOUD_PERCENTAGE_BASELINE = 0;
        final int CLOUD_PERCENTAGE_THRESHOLD = 50;

        if (!isRaining) {
            boolean temperatureIsInRange = valueInRange(temperature, TEMPERATURE_BASELINE, TEMPERATURE_THRESHOLD);
            boolean cloudPercentageIsInRange = valueInRange(cloudsPercentage, CLOUD_PERCENTAGE_BASELINE, CLOUD_PERCENTAGE_THRESHOLD);
            boolean windSpeedIsInRange = valueInRange(windSpeed, WIND_SPEED_BASELINE, WIND_SPEED_THRESHOLD);

            return temperatureIsInRange && cloudPercentageIsInRange && windSpeedIsInRange;
        } else {
            return false;
        }
    }

    public boolean isGoodForSwimming() {
        final int TEMPERATURE_BASELINE = 25;
        final int TEMPERATURE_THRESHOLD = 30;

        final int WIND_SPEED_BASELINE = 0;
        final int WIND_SPEED_THRESHOLD = 4;

        final int CLOUDS_PERCENTAGE_BASELINE = 0;
        final int CLOUDS_PERCENTAGE_THRESHOLD = 30;

        if (!isRaining && isValidWeather()) {
            boolean isWindSpeedGood = valueInRange(windSpeed, WIND_SPEED_BASELINE, WIND_SPEED_THRESHOLD);
            boolean areCloudsGood = valueInRange(cloudsPercentage, CLOUDS_PERCENTAGE_BASELINE, CLOUDS_PERCENTAGE_THRESHOLD);
            boolean isTemperatureGood = valueInRange(temperature, TEMPERATURE_BASELINE, TEMPERATURE_THRESHOLD);

            return isWindSpeedGood && areCloudsGood && isTemperatureGood;
        } else {
            return false;
        }
    }

    public double calculateWeatherRating() {
        if (isValidWeather()) {
            double rainContribution = isRaining ? 0 : 50;
            double windContribution = windSpeed * comfortCalculator.calculateWindComfortLevel(windSpeed);
            double temperatureContribution = temperature * comfortCalculator.calculateTemperatureComfortLevel(temperature);
            double cloudContribution = cloudsPercentage * comfortCalculator.calculateCloudsComfortLevel(cloudsPercentage);
            double comboContribution = comfortCalculator.calculateWeatherComboComfortLevel(isRaining, cloudsPercentage, windSpeed);

            return rainContribution + windContribution + temperatureContribution + cloudContribution * comboContribution;
        } else {
            return 0;
        }
    }

    public String convertTo12HourTimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(_12_HOUR_TIME_FORMAT);
        return time.format(formatter);
    }

    public String getTimePeriodOfDay() {
        int hour = time.getHour();

        return switch (hour) {
            case 0 -> "Midnight";
            case 1 -> "Early Overnight";
            case 2, 3 -> "Overnight";
            case 4, 5 -> "Late Overnight";
            case 6, 7 -> "Early Morning";
            case 8, 9 -> "Morning";
            case 10, 11 -> "Late Morning";
            case 12 -> "Noon";
            case 13 -> "Early Afternoon";
            case 14, 15 -> "Afternoon";
            case 16, 17 -> "Late Afternoon";
            case 18 -> "Early Evening";
            case 19, 20, 21 -> "Evening";
            case 22, 23 -> "Late Evening";
            default -> throw new IllegalStateException("Unexpected value: " + time.getHour());
        };
    }

    @Override
    public String toString() {
        return "[ " + time + " ] " + getTimePeriodOfDay()
                + "\nScore: " + calculateWeatherRating() + " points"
                + "\nTemperature: " + temperature + " Celsius"
                + "\nRaining: " + (isRaining ? "yes" : "no")
                + "\nClouds: " + cloudsPercentage + " %"
                + "\nWind: " + windSpeed + " m/s"
                + "\nVisibility: " + visibility + " m"
                + "\nPressure: " + pressure + " mBar"
                + '\n';
    }
}

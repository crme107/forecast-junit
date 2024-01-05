package org.example.junitcleancode.InterfaceImpl;

import org.example.junitcleancode.Interface.WeatherValidator;
import org.example.junitcleancode.Utility.Range;

public class WeatherValidatorImpl implements WeatherValidator {
    public boolean isValidWindSpeed(int windSpeed) {
        final int windSpeedBaseline = 0;
        final int windSpeedThreshold = 10;

        return Range.valueInRange(windSpeed, windSpeedBaseline, windSpeedThreshold);
    }

    public boolean isValidTemperature(int temperature) {
        final int temperatureBaseline = -15;
        final int temperatureThreshold = 50;

        return Range.valueInRange(temperature, temperatureBaseline, temperatureThreshold);
    }

    public boolean isValidCloudsPercentage(int cloudsPercentage) {
        final int cloudsPercentageBaseline = 0;
        final int cloudsPercentageThreshold = 100;

        return Range.valueInRange(cloudsPercentage, cloudsPercentageBaseline, cloudsPercentageThreshold);
    }

    public boolean isValidPressure(int pressure) {
        final int pressureBaseline = 990;
        final int pressureThreshold = 1030;

        return Range.valueInRange(pressure, pressureBaseline, pressureThreshold);
    }

    public boolean isValidVisibility(long visibility) {
        final int visibilityBaseline = 0;
        final int visibilityThreshold = 50000;

        return Range.valueInRange((int) visibility, visibilityBaseline, visibilityThreshold);
    }
}
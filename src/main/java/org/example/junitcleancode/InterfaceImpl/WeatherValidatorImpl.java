package org.example.junitcleancode.InterfaceImpl;

import org.example.junitcleancode.Interface.WeatherValidator;
import org.example.junitcleancode.Utility.Range;

public class WeatherValidatorImpl implements WeatherValidator {
    public boolean isValidWindSpeed(int windSpeed) {
        final int WIND_SPEED_BASELINE = 0;
        final int WIND_SPEED_THRESHOLD = 10;

        return Range.valueInRange(windSpeed, WIND_SPEED_BASELINE, WIND_SPEED_THRESHOLD);
    }

    public boolean isValidTemperature(int temperature) {
        final int TEMPERATURE_BASELINE = -15;
        final int TEMPERATURE_THRESHOLD = 50;

        return Range.valueInRange(temperature, TEMPERATURE_BASELINE, TEMPERATURE_THRESHOLD);
    }

    public boolean isValidCloudsPercentage(int cloudsPercentage) {
        final int CLOUDS_PERCENTAGE_BASELINE = 0;
        final int CLOUDS_PERCENTAGE_THRESHOLD = 100;

        return Range.valueInRange(cloudsPercentage, CLOUDS_PERCENTAGE_BASELINE, CLOUDS_PERCENTAGE_THRESHOLD);
    }

    public boolean isValidPressure(int pressure) {
        final int PRESSURE_BASELINE = 990;
        final int PRESSURE_THRESHOLD = 1030;

        return Range.valueInRange(pressure, PRESSURE_BASELINE, PRESSURE_THRESHOLD);
    }

    public boolean isValidVisibility(long visibility) {
        final int VISIBILITY_BASELINE = 0;
        final int VISIBILITY_THRESHOLD = 50000;

        return Range.valueInRange((int) visibility, VISIBILITY_BASELINE, VISIBILITY_THRESHOLD);
    }
}

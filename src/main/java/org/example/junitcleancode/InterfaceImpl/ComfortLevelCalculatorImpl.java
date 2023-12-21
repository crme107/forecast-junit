package org.example.junitcleancode.InterfaceImpl;

import org.example.junitcleancode.Interface.ComfortLevelCalculator;

import static org.example.junitcleancode.Utility.Range.valueInRange;

public class ComfortLevelCalculatorImpl implements ComfortLevelCalculator {
    @Override
    public int calculateWindComfortLevel(int windSpeed) {
        final int WIND_BASELINE = 0;
        final int CALM_BREEZE_THRESHOLD = 4;
        final int LIGHT_BREEZE_THRESHOLD = 7;
        final int MODERATE_BREEZE_THRESHOLD = 9;
        final int STRONG_BREEZE_THRESHOLD = 11;

        if (valueInRange(windSpeed, WIND_BASELINE, CALM_BREEZE_THRESHOLD)) {
            return 4;
        } else if (windSpeed < LIGHT_BREEZE_THRESHOLD) {
            return 3;
        } else if (windSpeed < MODERATE_BREEZE_THRESHOLD) {
            return 2;
        } else if (windSpeed < STRONG_BREEZE_THRESHOLD) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int calculateTemperatureComfortLevel(int temperature) {
        final int PERFECT_TEMPERATURE = 25;
        final int COMFORTABLE_TEMPERATURE_BASELINE = 15;
        final int COMFORTABLE_TEMPERATURE_THRESHOLD = 35;
        final int CHILLY_TEMPERATURE_BASELINE = 5;
        final int CHILLY_TEMPERATURE_THRESHOLD = 14;

        if (temperature == PERFECT_TEMPERATURE) {
            return 4;
        } else if (valueInRange(temperature, COMFORTABLE_TEMPERATURE_BASELINE, COMFORTABLE_TEMPERATURE_THRESHOLD)) {
            return 3;
        } else if (valueInRange(temperature, CHILLY_TEMPERATURE_BASELINE, CHILLY_TEMPERATURE_THRESHOLD)) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public int calculateCloudsComfortLevel(int cloudPercentage) {
        final int CLOUDS_BASELINE = 0;
        final int LOW_CLOUDS_THRESHOLD = 25;
        final int MEDIUM_CLOUDS_THRESHOLD = 50;
        final int HIGH_CLOUDS_THRESHOLD = 75;

        if (valueInRange(cloudPercentage, CLOUDS_BASELINE, LOW_CLOUDS_THRESHOLD)) {
            return 3;
        } else if (cloudPercentage <= MEDIUM_CLOUDS_THRESHOLD) {
            return 2;
        } else if (cloudPercentage <= HIGH_CLOUDS_THRESHOLD) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int calculateWeatherComboComfortLevel(boolean isRaining, int cloudPercentage, int windSpeed) {
        final int NO_CLOUDS = 0;
        final int NO_WIND = 0;

        if (isRaining) {
            return 1;
        } else if (cloudPercentage == NO_CLOUDS && windSpeed != NO_WIND) {
            return 2;
        } else if (cloudPercentage == NO_CLOUDS) {
            return 3;
        } else {
            return 0;
        }
    }
}

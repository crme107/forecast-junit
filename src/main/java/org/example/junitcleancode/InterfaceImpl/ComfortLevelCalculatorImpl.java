package org.example.junitcleancode.InterfaceImpl;

import org.example.junitcleancode.Interface.ComfortLevelCalculator;

import static org.example.junitcleancode.Utility.Range.valueInRange;

public class ComfortLevelCalculatorImpl implements ComfortLevelCalculator {
    @Override
    public int calculateWindComfortLevel(int windSpeed) {
        final int windBaseline = 0;
        final int calmBreezeThreshold = 4;
        final int lightBreezeThreshold = 7;
        final int moderateBreezeThreshold = 9;
        final int strongBreezeThreshold = 11;

        if (valueInRange(windSpeed, windBaseline, calmBreezeThreshold)) {
            return 4;
        } else if (windSpeed < lightBreezeThreshold) {
            return 3;
        } else if (windSpeed < moderateBreezeThreshold) {
            return 2;
        } else if (windSpeed < strongBreezeThreshold) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int calculateTemperatureComfortLevel(int temperature) {
        final int perfectTemperature = 25;
        final int comfortableTemperatureBaseline = 15;
        final int comfortableTemperatureThreshold = 35;
        final int chillyTemperatureBaseline = 5;
        final int chillyTemperatureThreshold = 14;

        if (temperature == perfectTemperature) {
            return 4;
        } else if (valueInRange(temperature, comfortableTemperatureBaseline, comfortableTemperatureThreshold)) {
            return 3;
        } else if (valueInRange(temperature, chillyTemperatureBaseline, chillyTemperatureThreshold)) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public int calculateCloudsComfortLevel(int cloudPercentage) {
        final int cloudsBaseline = 0;
        final int lowCloudsThreshold = 25;
        final int mediumCloudsThreshold = 50;
        final int highCloudsThreshold = 75;

        if (valueInRange(cloudPercentage, cloudsBaseline, lowCloudsThreshold)) {
            return 3;
        } else if (cloudPercentage <= mediumCloudsThreshold) {
            return 2;
        } else if (cloudPercentage <= highCloudsThreshold) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int calculateWeatherComboComfortLevel(boolean isRaining, int cloudPercentage, int windSpeed) {
        final int noClouds = 0;
        final int noWind = 0;

        if (isRaining) {
            return 1;
        } else if (cloudPercentage == noClouds && windSpeed != noWind) {
            return 2;
        } else if (cloudPercentage == noClouds) {
            return 3;
        } else {
            return 0;
        }
    }
}

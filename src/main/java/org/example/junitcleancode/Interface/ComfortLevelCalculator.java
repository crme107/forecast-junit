package org.example.junitcleancode.Interface;

public interface ComfortLevelCalculator {
    int calculateWindComfortLevel(int windSpeed);
    int calculateTemperatureComfortLevel(int temperature);
    int calculateCloudsComfortLevel(int cloudPercentage);
    int calculateWeatherComboComfortLevel(boolean isRaining, int cloudPercentage, int windSpeed);
}

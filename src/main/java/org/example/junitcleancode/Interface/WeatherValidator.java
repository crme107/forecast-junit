package org.example.junitcleancode.Interface;

public interface WeatherValidator {
    boolean isValidWindSpeed(int wind);
    boolean isValidCloudsPercentage(int clouds);
    boolean isValidTemperature(int temperature);
    boolean isValidPressure(int pressure);
    boolean isValidVisibility(long visibility);
}

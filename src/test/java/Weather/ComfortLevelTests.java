package Weather;

import org.example.junitcleancode.Entity.Weather;
import org.example.junitcleancode.InterfaceImpl.ComfortLevelCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComfortLevelTests {
    private Weather weather;
    private final ComfortLevelCalculatorImpl comfortCalculator = new ComfortLevelCalculatorImpl();

    @BeforeEach
    void setup() {
        weather = new Weather(LocalTime.of(15, 35), true, 3, 25, 45, 100, 1000);
    }

    @Test
    @DisplayName("Testing calculateWindComfortLevel()")
    void windTest() {
        assertAll(
                () -> { weather.setWindSpeed(15); assertEquals(comfortCalculator.calculateWindComfortLevel(weather.getWindSpeed()), 0, "Incorrect wind comfort level"); },
                () -> { weather.setWindSpeed(9); assertEquals(comfortCalculator.calculateWindComfortLevel(weather.getWindSpeed()), 1, "Incorrect wind comfort level"); },
                () -> { weather.setWindSpeed(8); assertEquals(comfortCalculator.calculateWindComfortLevel(weather.getWindSpeed()), 2, "Incorrect wind comfort level"); },
                () -> { weather.setWindSpeed(5); assertEquals(comfortCalculator.calculateWindComfortLevel(weather.getWindSpeed()), 3, "Incorrect wind comfort level"); },
                () -> { weather.setWindSpeed(3); assertEquals(comfortCalculator.calculateWindComfortLevel(weather.getWindSpeed()), 4, "Incorrect wind comfort level"); }
        );
    }

    @Test
    @DisplayName("Testing calculateTemperatureComfortLevel()")
    void temperatureTest() {
        assertAll(
                () -> { weather.setTemperature(-2); assertEquals(comfortCalculator.calculateTemperatureComfortLevel(weather.getTemperature()), 1, "Incorrect temperature comfort level"); },
                () -> { weather.setTemperature(10); assertEquals(comfortCalculator.calculateTemperatureComfortLevel(weather.getTemperature()), 2, "Incorrect temperature comfort level"); },
                () -> { weather.setTemperature(20); assertEquals(comfortCalculator.calculateTemperatureComfortLevel(weather.getTemperature()), 3, "Incorrect temperature comfort level"); },
                () -> { weather.setTemperature(25); assertEquals(comfortCalculator.calculateTemperatureComfortLevel(weather.getTemperature()), 4, "Incorrect temperature comfort level"); }
        );
    }

    @Test
    @DisplayName("Testing calculateCloudsComfortLevel()")
    void cloudTest() {
        assertAll(
                () -> { weather.setCloudsPercentage(90); assertEquals(comfortCalculator.calculateCloudsComfortLevel(weather.getCloudsPercentage()), 0, "Incorrect cloud % comfort level"); },
                () -> { weather.setCloudsPercentage(51); assertEquals(comfortCalculator.calculateCloudsComfortLevel(weather.getCloudsPercentage()), 1, "Incorrect cloud % comfort level"); },
                () -> { weather.setCloudsPercentage(10); assertEquals(comfortCalculator.calculateCloudsComfortLevel(weather.getCloudsPercentage()), 3, "Incorrect cloud % comfort level"); },
                () -> { weather.setCloudsPercentage(45); assertEquals(comfortCalculator.calculateCloudsComfortLevel(weather.getCloudsPercentage()), 2, "Incorrect cloud % comfort level"); }
        );
    }

    @Test
    @DisplayName("Testing calculateWeatherComboComfortLevel()")
    void comboTest() {
        int windSpeed = 7;
        int cloudPercentage = 15;

        assertEquals(comfortCalculator.calculateWeatherComboComfortLevel(false, cloudPercentage, windSpeed), 0, "Incorrect combo comfort level");
        assertEquals(comfortCalculator.calculateWeatherComboComfortLevel(true, cloudPercentage, windSpeed), 1, "Incorrect combo comfort level");

        cloudPercentage = 0;
        assertEquals(comfortCalculator.calculateWeatherComboComfortLevel(false, cloudPercentage, windSpeed), 2, "Incorrect combo comfort level");

        windSpeed = 0;
        assertEquals(comfortCalculator.calculateWeatherComboComfortLevel(false, cloudPercentage, windSpeed), 3, "Incorrect combo comfort level");
    }
}

package Weather;

import org.example.junitcleancode.Entity.Forecast;
import org.example.junitcleancode.InterfaceImpl.WeatherValidatorImpl;
import org.example.junitcleancode.Entity.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTests {
    private Weather weather;

    @BeforeEach
    void setup() {
        WeatherValidatorImpl validator = new WeatherValidatorImpl();
        weather = new Weather(LocalTime.of(15, 35), true, 3, 25, 45, 100, 1000);
        weather.setValidator(validator);
    }

    @Test
    @DisplayName("Testing Weather constructor")
    void creatingWeatherTest() {
        Weather clone = new Weather();
        assertEquals(Weather.class, clone.getClass(), "Weather constructor is not working correctly");
    }

    @Test
    @DisplayName("Testing isValidDate()")
    void validateDateTest() {
        Forecast forecast = new Forecast(LocalDate.of(2024, 11, 13), new ArrayList<>());
        assertTrue(forecast.isValidDate(forecast.getDate()), "Date is not valid");
    }

    @Test
    @DisplayName("Testing isValidWeather")
    void validateWeatherTest() {
        assertTrue(weather.isValidWeather(), "Weather data is not valid");
    }

    @Test
    @DisplayName("Testing isValidCloudsPercentage() from validator")
    void validateCloudPercentageTest() {
        assertTrue(weather.getValidator().isValidCloudsPercentage(weather.getCloudsPercentage()), "Clouds percentage is not valid");
    }
}

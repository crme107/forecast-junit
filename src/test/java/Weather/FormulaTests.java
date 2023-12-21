package Weather;

import org.example.junitcleancode.InterfaceImpl.WeatherValidatorImpl;
import org.example.junitcleancode.Entity.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FormulaTests {
    private Weather weather;

    @BeforeEach
    void setup() {
        WeatherValidatorImpl validator = new WeatherValidatorImpl();
        weather = new Weather(LocalTime.of(15, 35), true, 3, 25, 45, 100, 1000);
        weather.setValidator(validator);
    }

    @Test
    @DisplayName("Testing formula for calculateWeatherRating()")
    public void weatherRatingTest() {
        assertEquals(weather.calculateWeatherRating(), 202, "Rating is being calculated incorrectly");
    }

    @Test
    @DisplayName("Testing else block for calculateWeatherRating()")
    public void weatherRatingElseBlockTest() {
        weather.setWindSpeed(150);
        assertEquals(weather.calculateWeatherRating(), 0);
    }

    @Test
    @DisplayName("Testing formula for isGoodForWalking()")
    public void goodForWalkingTest() {
        weather.setRaining(false);
        weather.setTemperature(20);
        assertTrue(weather.isGoodForWalking());
    }

    @Test
    @DisplayName("Testing formula for isGoodForWalking() #2")
    public void badForWalkingTest() {
        weather.setTemperature(40);
        assertFalse(weather.isGoodForWalking());
    }

    @Test
    @DisplayName("Testing formula for isGoodForSwimming()")
    public void goodForSwimmingTest() {
        weather.setTemperature(28);
        weather.setRaining(false);
        weather.setWindSpeed(2);
        weather.setCloudsPercentage(20);
        assertTrue(weather.isGoodForSwimming());
    }
}
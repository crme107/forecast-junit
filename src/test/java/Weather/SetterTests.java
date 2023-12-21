package Weather;

import org.example.junitcleancode.Entity.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetterTests {
    private static Weather weather;

    @BeforeEach
    void setup() {
        weather = new Weather(LocalTime.of(15, 35), true, 3, 25, 45, 100, 1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    @DisplayName("Testing setter setWindSpeed()")
    void windSetterTest(int speed) {
        weather.setWindSpeed(speed);
        assertEquals(weather.getWindSpeed(), 2);
    }

    @ParameterizedTest()
    @CsvSource(value = {"15, 35"})
    @DisplayName("Testing setter setTime()")
    void dateSettersTest(int hour, int minute) {
        weather.setTime(LocalTime.of(hour, minute));
        assertEquals(weather.getTime(), LocalTime.of(hour, minute));
    }

    @ParameterizedTest
    @ValueSource(booleans = true)
    @DisplayName("Testing setter setRaining()")
    void setRainingTest(boolean rain) {
        weather.setRaining(rain);
        assertTrue(weather.isRaining());
    }

    @ParameterizedTest
    @ValueSource(ints = 20)
    @DisplayName("Testing setter setTemperature()")
    void setTemperatureTest(int temperature) {
        weather.setTemperature(temperature);
        assertEquals(weather.getTemperature(), 20);
    }

    @ParameterizedTest
    @ValueSource(ints = 74)
    @DisplayName("Testing setter setCloudsPercentage()")
    void setCloudsPercentageTest(int clouds) {
        weather.setCloudsPercentage(clouds);
        assertEquals(weather.getCloudsPercentage(), 74);
    }

    @ParameterizedTest
    @ValueSource(ints = 140)
    @DisplayName("Testing setter setVisibility()")
    void setDistanceVisibleTest(int distance) {
        weather.setVisibility(distance);
        assertEquals(weather.getVisibility(), 140);
    }

    @ParameterizedTest
    @ValueSource(ints = 935)
    @DisplayName("Testing setter setPressure()")
    void setPressureTest(int pressure) {
        weather.setPressure(pressure);
        assertEquals(weather.getPressure(), 935);
    }
}
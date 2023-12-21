package Weather;

import org.example.junitcleancode.Entity.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class GetterTests {
    private static Weather weather;

    @BeforeEach
    void setup() {
        weather = new Weather(LocalTime.of(15, 35), true, 3, 25, 45, 100, 1000);
    }

    @Test
    @DisplayName("Testing getters getTime() and get12hFormatTime()")
    void getDateTest() {
        assertAll("Getters are not working correctly",
                () -> assertEquals(weather.getTime(), LocalTime.of(15, 35)),
                () -> assertEquals(
                        weather.convertTo12HourTimeFormat(),
                        LocalTime.of(15, 35).format(DateTimeFormatter.ofPattern("h:mm a"))
                )
        );
    }

    @Test
    @DisplayName("Testing getter isRaining()")
    void isRainingTest() {
        assertTrue(weather.isRaining());
    }

    @Test
    @DisplayName("Testing getter getTemperature()")
    void getTemperatureTest() {
        assertEquals(weather.getTemperature(), 25);
    }

    @Test
    @DisplayName("Testing getter getCloudsPercentage()")
    void getCloudsPercentageTest() {
        assertEquals(weather.getCloudsPercentage(), 45);
    }

    @Test
    @DisplayName("Testing getter getVisibility()")
    void getDistanceVisibleTest() {
        assertEquals(weather.getVisibility(), 100);
    }

    @Test
    @DisplayName("Testing getter getPressure()")
    void getPressureTest() {
        assertEquals(weather.getPressure(), 1000);
    }
}
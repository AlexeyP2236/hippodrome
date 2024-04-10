import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    public static Stream<String> testWithMethodSource() {
        return Stream.of(" ", "\n", "\t");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("testWithMethodSource")
    public void nullAndEmptyHorse(String name) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        if (name == null) assertEquals("Name cannot be null.", throwable.getMessage());
        else assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @ParameterizedTest
    @CsvSource("-1, -1")
    public void speedAndDistanceHorse(int speed, int distance) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse("name", speed, distance));
        if (speed < 0) assertEquals("Speed cannot be negative.", throwable.getMessage());
        else if (distance < 0) assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @ParameterizedTest
    @CsvSource("Fox, 1, 1")
    public void checkHorseThreeParameter(String name, int speed, int distance) {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(name, horse.getName());
        assertEquals(speed, horse.getSpeed());
        assertEquals(distance, horse.getDistance());
    }

    @ParameterizedTest
    @CsvSource("Fox, 1")
    public void checkHorseTwoParameter(String name, int speed) {
        Horse horse = new Horse(name, speed);
        assertEquals(name, horse.getName());
        assertEquals(speed, horse.getSpeed());
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void move() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Fox", 31, 45).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
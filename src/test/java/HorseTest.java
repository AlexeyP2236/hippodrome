import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    public static Stream<String> streamWithMethodSource() {
        return Stream.of(" ", "\n", "\t");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("streamWithMethodSource")
    public void testNullAndEmptyHorse(String name) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        if (name == null) assertEquals("Name cannot be null.", throwable.getMessage());
        else assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @ParameterizedTest
    @CsvSource("-1, -1")
    public void testSpeedAndDistanceHorse(int speed, int distance) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse("name", speed, distance));

        if (speed < 0) assertEquals("Speed cannot be negative.", throwable.getMessage());
        else if (distance < 0) assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @ParameterizedTest
    @CsvSource("Fox, 1, 1")
    public void testHorseThreeParameter(String name, int speed, int distance) {
        Horse horse = new Horse(name, speed, distance);

        assertEquals(name, horse.getName());
        assertEquals(speed, horse.getSpeed());
        assertEquals(distance, horse.getDistance());
    }

    @ParameterizedTest
    @CsvSource("Fox, 1")
    public void testHorseTwoParameter(String name, int speed) {
        Horse horse = new Horse(name, speed);

        assertEquals(name, horse.getName());
        assertEquals(speed, horse.getSpeed());
        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 11.1})
    public void testMove(double random) {
        int speed = 31;
        int distance = 45;
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("Fox", speed, distance);
            horse.move();

            assertEquals(distance + speed * random, horse.getDistance());
        }
    }
}
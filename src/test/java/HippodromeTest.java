import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    List<Horse> horses = new ArrayList<>();

    public List<Horse> getNumberHorse(int number) {
        for (int i = 0; i < number; i++) {
            horses.add(Instancio.create(Horse.class));
        }
        return horses;
    }


    @ParameterizedTest
    @NullAndEmptySource
    public void testNullAndEmptyHippodrome(List<Horse> horseList) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));

        if (horseList == null) assertEquals("Horses cannot be null.", throwable.getMessage());
        else assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    void testGetHorses() {
        Hippodrome hippodrome = new Hippodrome(getNumberHorse(30));

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void testMove() {
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        horses.add(new Horse("1", 1, 1));
        horses.add(new Horse("2", 2, 2));
        horses.add(new Horse("3", 3, 3));
        Horse horse = new Horse("4", 4, 4);
        horses.add(horse);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse, hippodrome.getWinner());
    }
}
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    List<Horse> horses = new ArrayList<>();

    public List<Horse> getNumberHorse(int number) {
        for (int i = 0; i < number; i++) {
            horses.add(Instancio.create(Horse.class));
        }
        return horses;
    }

    @Test
    void testMain() {
        Assertions.assertTimeout(Duration.ofSeconds(22),
                );
    }
}
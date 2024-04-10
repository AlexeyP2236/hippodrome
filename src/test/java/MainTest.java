import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    void testMain() {
        String[] args = new String[] {"--input"};
        try {
            Main.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
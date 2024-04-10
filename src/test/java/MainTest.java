import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Disabled
    @Timeout(22)
    void testMain() {
        String[] arguments = new String[] { "--input"};
        try {
            Main.main(arguments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
import com.example.demo1.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    Dice d = new Dice();

    @Test
    void getValue() {
        // range: [1,4]
        assertNotEquals(-1, d.getValue());
        assertNotEquals(5, d.getValue());
    }

    @Test
    void getDirection() {
        assertNotEquals("-1", d.getDirection());
        assertNotEquals("dsadas", d.getDirection());
    }
}
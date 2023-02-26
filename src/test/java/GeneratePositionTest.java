import com.example.demo1.Board;
import com.example.demo1.GeneratePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratePositionTest {
    Board b = new Board(3,10,2,2);
    GeneratePosition pos = new GeneratePosition(b);

    @Test
    void getX() {
        // in x-axis, only positions in range (min_x ~ max_x) will be generated. See that class.
        assertNotEquals(2, pos.getX());
        assertNotEquals(11,pos.getX());
    }

    @Test
    void getY() {
        assertNotEquals(18,pos.getY());
    }
}
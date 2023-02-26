import com.example.demo1.Board;
import com.example.demo1.Pub;
import com.example.demo1.Roadblock;
import com.example.demo1.Teleport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesTest {
    Board b = new Board(2,10,2,2);
    Pub pub = new Pub(b);
    Roadblock rb = new Roadblock(b);
    Teleport t = new Teleport(b);

    @Test
    void testGetURLs() {
        assertEquals("/Pub.png", pub.getImage());
        assertEquals("/Roadblock.png", rb.getImage());
        assertEquals("/Teleport.jpg", t.getImage());
    }

    @Test
    void testGetTypes() {
        assertEquals("Pub", pub.getType());
        assertEquals("Roadblock", rb.getType());
        assertEquals("Teleport", t.getType());
    }

}
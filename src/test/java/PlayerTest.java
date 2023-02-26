import com.example.demo1.Board;
import com.example.demo1.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Board b = new Board(2,10,2,2);
    Player p1 = new Player("James",true,b,0);
    Player p2 = new Player("Who",false,b,1);

    @Test
    void move() {
        // can't move out of bounds
        p1.move(11,0, b);
        assertEquals(9, p1.getX());
        p2.move(-444,0, b);
        assertEquals(0, p2.getX());
    }

    @Test
    void getName() {
        // only user can specify names, otherwise names start with "Bot"
        assertEquals("James", p1.getName());
        assertNotEquals("Who", p2.getName());
    }

    @Test
    void getScore() {
        // initialization sets to 0
        assertEquals(0, p1.getScore());
        assertEquals(0, p2.getScore());
    }

    @Test
    void setScore() {
        p1.setScore(-333);
        assertEquals(0, p1.getScore());
        p1.setScore(9);
        assertEquals(9, p1.getScore());
    }

    @Test
    void getFinished() {
        assertEquals(false, p1.getFinished());
        assertEquals(false, p2.getFinished());
    }

    @Test
    void getPos() {
        int x = p1.getX();
        int y = p1.getY();
        assertEquals(y + " " + x, p1.getPos());
    }

    @Test
    void getNo_() {
        assertEquals(0, p1.getNo_());
        assertEquals(1, p2.getNo_());
    }
}
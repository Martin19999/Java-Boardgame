import com.example.demo1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BoardTest {

    @Test
    void initTest() {
        // lanes in range [2, 10]
        // length in range [6, 20]
        // number of players in range [2, number of lanes]
        // number of obstacles in range [0, lanes*(length-5)]

        // normal
        Board board = new Board(2,6,2,2);
        assertEquals(2, board.getLanes());
        assertEquals(6, board.getLength());
        assertEquals(2, board.getNum_of_players());
        assertEquals(2, board.getNum_of_obstacles());

        // lanes < 2
        Board board1 = new Board(1,6,2,2);
        assertEquals(2, board1.getLanes());
        // lanes > 10
        Board board2 = new Board(13,6,2,2);
        assertEquals(10, board2.getLanes());

        // length < 6
        Board board3 = new Board(4,2,2,2);
        assertEquals(6, board3.getLength());
        // length > 20
        Board board4 = new Board(4,244,2,2);
        assertEquals(20, board4.getLength());

        // number of players < 2
        Board board5 = new Board(4,2,-1,2);
        assertEquals(2, board5.getNum_of_players());
        // number of players > lanes
        Board board6 = new Board(4,244,777777,2);
        assertEquals(4, board6.getNum_of_players());

        // number of obstacles < 1
        Board board7 = new Board(4,2,3,-5);
        assertEquals(0, board7.getNum_of_obstacles());
        // number of obstacles too big
        Board board8 = new Board(4,244,3,2222);
        assertNotEquals(2222, board8.getNum_of_obstacles());
    }

    @Test
    void toStringTest(){
        Board b = new Board(2,6,2,2);
        assertEquals("Board has 2 lanes, with lane length of 6 and 2 obstacle(s).", b.toString());
    }

    @Test
    void positionTest() {
        // Player's position should be recorded on board
        Board b = new Board(2,6,2,0);
        Player p = new Player("Martin", true, b,1);
        b.setPlayer_coordinate(p.getX(), p.getY(), p);
        assertEquals(p, b.getPlayer_coordinate()[p.getX()][p.getY()]);

//        Player p1 = new Player("g", true, b,3);
//        b.setPlayer_coordinate(25,30, p);
//        b.setPlayer_coordinate(p1.getX(), p1.getY(), p);
//        assertEquals(p1, b.getPlayer_coordinate()[p1.getX()][p1.getY()]);

        // Obstacle's position should be recorded on board
        Board b1 = new Board(2,6,2,0);
        Pub pub = new Pub(b);
        b1.setObstacle_coordinate(pub.getX(), pub.getY(), pub);
        assertEquals(pub, b1.getObstacle_coordinate()[pub.getX()][pub.getY()]);

    }

}
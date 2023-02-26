package com.example.demo1;

public class Roadblock extends Obstacle{
    public Roadblock(Board board) {
        super(board,"/Roadblock.png", Type.Roadblock);
        // generate a random position on this board,
        // but only even lanes get Roadblock, so it's easier to manage,
        // don't have to worry Roadblocks blocking every possible way for a Player to pass
        boolean found = false;
        while (!found) {
            GeneratePosition pos = new GeneratePosition(board);
            if (pos.getY() % 2 == 0) {
                this.x = pos.getX();
                this.y = pos.getY();
                found = true;
            }
        }
    }
}


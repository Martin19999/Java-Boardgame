package com.example.demo1;

public class Teleport extends Obstacle{
    public Teleport(Board board) {
        super(board, "/Teleport.jpg", Type.Teleport);
        // generate a random position on this board
        GeneratePosition pos = new GeneratePosition(board);
        this.x = pos.getX();
        this.y = pos.getY();
    }
}

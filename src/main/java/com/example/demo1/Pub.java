package com.example.demo1;

public class Pub extends Obstacle{
    public Pub(Board board) {
        super(board, "/Pub.png",Type.Pub);
        // generate a random position on this board
        GeneratePosition pos = new GeneratePosition(board);
        this.x = pos.getX();
        this.y = pos.getY();
    }



}

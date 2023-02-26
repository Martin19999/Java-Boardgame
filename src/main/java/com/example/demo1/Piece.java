package com.example.demo1;

/**
 * An interface stands for pieces on a game board. It has two classes implement it:
 *      1. Obstacle
 *      2. Player
 *
 */
public interface Piece {
    /**
     * get the x position (e.g. the 2nd square on x-axis) of the Piece on a board
     *
     * @return x position of the Piece on a board
     */
    int getX();

    /**
     * get the y position (e.g. the 2nd square on y-axis) of the Piece on a board
     *
     * @return y position of the Piece on a board
     */
    int getY();
}

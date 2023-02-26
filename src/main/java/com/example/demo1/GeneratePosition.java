package com.example.demo1;

/**
 * For randomly generating Obstacle positions within a board.
 * <p>
 * Or randomly generating Player positions when they step on a Pub (Obstacle)
 * -- they are drunk so they lost directions
 *
 */
public class GeneratePosition {
    private int x_pos;
    private int y_pos;

    /**
     * Initialisation: Generate positions according to current board to avoid collisions.
     *
     * @param board the board you want to generate positions on.
     */
    public GeneratePosition(Board board){
        // in x-axis, only positions in range (min_x ~ max_x) will be generated.
        int max_x = board.getLength() - 1 - 1;
        int min_x = 4;
        int max_y = board.getLanes() - 1;
        int min_y = 0;

        boolean not_found = true;
        while(not_found){
            int provisional_x = (int) Math.floor(Math.random() * (max_x - min_x + 1) + min_x);
            int provisional_y = (int) Math.floor(Math.random() * (max_y - min_y + 1) + min_y);
            // only if there is no collisions, positions will be generated.
            // don't have to worry about no space available because number of Players, Obstacles
            // are limited and checked in other classes.
            if (board.getObstacle_coordinate()[provisional_x][provisional_y] == null &&
                    board.getPlayer_coordinate()[provisional_x][provisional_y] == null){
                this.x_pos = provisional_x;
                this.y_pos = provisional_y;
                not_found = false;
            }
        }
    }

    /**
     * get the generated x position on a board
     *
     * @return the generated x position on a board
     */
    public int getX() {
        return x_pos;
    }

    /**
     * get the generated y position on a board
     *
     * @return the generated y position on a board
     */
    public int getY() {
        return y_pos;
    }
}

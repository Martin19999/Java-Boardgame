package com.example.demo1;

/**
 * Represents the track field of the game.
 *
 */
public class Board {

    // Number of Lanes, the length of each lane, the total number of players, obstacles.
    private final int lanes;
    private final int length;
    private final int num_of_players;
    private final int num_of_obstacles;

    // Use: when the game starts, allocate the lanes for the players
    private final Boolean[] has_player;
    // track the pieces' positions on the board
    private Player[][] player_coordinate;
    private Obstacle[][] obstacle_coordinate;

    /**
     * This class represents the game board. It has the board's attributes such as number of lanes,
     * length of each track etc
     * <p>
     * It also documents the position of Player & Obstacle in 2D arrays.
     *
     * @param lanes number of lanes in the board, or how many squares are there in y-axis
     * @param length how many squares are there for each lane in x-axis
     * @param num_of_players number of player on the board
     * @param num_of_obstacles number of obstacles on the baord
     */
    public Board(int lanes, int length, int num_of_players, int num_of_obstacles) {
        // Don't have to write if else...
        // lanes in range [2, 10]
        // length in range [6, 20]
        // number of players in range [2, number of lanes]
        // number of obstacles in range [0, lanes*(length-5)]
        this.lanes = Math.max(Math.min(lanes, 10), 2);
        this.length = Math.max(Math.min(length, 20), 6);
        this.num_of_players = Math.min(this.lanes, Math.max(num_of_players, 2));
        this.num_of_obstacles = Math.min(this.lanes*(this.length-5), Math.max(num_of_obstacles, 0));

        // initialization: the lanes for the players
        has_player = new Boolean[this.lanes];
        for (int i = 0; i < this.lanes; i++){
            has_player[i] = false;
        }
        // initialization: positions for the pieces, they are all null now.
        this.player_coordinate = new Player[length][lanes];
        this.obstacle_coordinate = new Obstacle[length][lanes];
    }

    /**
     * A string representation of the board. Specifying
     * 1. number of lanes
     * 2. length (number of squares)
     * 3. number of obstacles
     *
     * @return A string representation of the board.
     */
    @Override
    public String toString() {
        return "Board has "+ getLanes() + " lanes, with lane length of " +
                getLength() + " and " + getNum_of_obstacles() + " obstacle(s).";
    }

    /**
     * to verify if a player's move is valid. i.e. not overlapping with other players etc
     *
     * @param move_x the distance a player is going to move in x-axis
     * @param move_y the distance a player is going to move in y-axis
     * @param p any object that implements the Piece interface, such as a Player
     * @return an integer giving different message about the validity of a move:
     *
     *          3 -> Overlap with a Teleport (Obstacle), which a Player is allowed to step on.
     *          2 -> Overlap with a Pub (Obstacle), which a Player is allowed to step on.
     *          1 -> able to move because the destination has no other objects, and is within the bound,
     *                  or it's out of bound in positive x-axis --- the Player has won.
     *          -1 -> Overlap with a Roadblock (Obstacle), which a Player is NOT allowed to step on.
     *          -2 -> Overlap with another Player or out of bound, can't move
     */
    public int move_ok(int move_x, int move_y, Piece p) {
        int new_x = p.getX() + move_x;
        int new_y = p.getY() + move_y;
        // out of bound, not ok
        if (new_x < 0 || new_y > lanes -1 || new_y < 0){
            return -2;
        }
        // win, but ok to move!!!
        if (new_x > length - 1){
            return 1;
        }

        if (player_coordinate[new_x][new_y] == null) {
            if (obstacle_coordinate[new_x][new_y] == null) {
                return 1;
            }else{
                if (obstacle_coordinate[new_x][new_y] instanceof Teleport){
                    return 3;
                } else if (obstacle_coordinate[new_x][new_y] instanceof Pub){
                    return 2;
                } else if (obstacle_coordinate[new_x][new_y] instanceof Roadblock) {
                    return -1;
                }
            }
        }
        return -2;
    }

    /**
     * get number of lanes of a board
     *
     * @return number of lanes of a board
     */
    public int getLanes() {
        return lanes;
    }

    /**
     * get number of squares in x-axis of a board
     *
     * @return number of squares in x-axis of a board
     */
    public int getLength() {
        return length;
    }

    /**
     * get the number of players on the board
     *
     * @return number of players on the board
     */
    public int getNum_of_players() {
        return num_of_players;
    }

    /**
     * get the number of obstacles on the board
     *
     * @return number of obstacles on the board
     */
    public int getNum_of_obstacles() {
        return num_of_obstacles;
    }

    /**
     * see if a lane has a Player. Only used in initialization of Players' lane number.
     *
     * @return an array of boolean representing if that lane of the specified index has been allocated
     *         a Player.
     */
    public Boolean[] getHas_player() {
        return has_player;
    }

    /**
     * Set when a lane is allocated a Player. Only used in initialization of Players' lane number.
     *
     * @param pos the number representation of the lane.
     */
    public void setHas_player(int pos) {
        this.has_player[pos] = true;
    }

    /**
     * Get positions of players on this board
     *
     * @return a 2D array representing the position of Players on this board
     */
    public Player[][] getPlayer_coordinate() {
        return player_coordinate;
    }

    /**
     * Modify the Player position record.
     *
     * @param x the x position of a Player
     * @param y the y position of a Player
     * @param p the Player object
     */
    public void setPlayer_coordinate(int x, int y, Player p) {
        player_coordinate[x][y] = p;
    }

    /**
     * Get positions of Obstacles on this board
     *
     * @return a 2D array representing the position of Obstacles on this board
     */
    public Obstacle[][] getObstacle_coordinate() {
        return obstacle_coordinate;
    }

    /**
     * Modify the Obstacle position record.
     *
     * @param x the x position of an Obstacle
     * @param y the y position of an Obstacle
     * @param o the Obstacle object
     */
    public void setObstacle_coordinate(int x, int y, Obstacle o) {
        obstacle_coordinate[x][y] = o;
    }

}


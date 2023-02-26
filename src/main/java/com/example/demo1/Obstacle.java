package com.example.demo1;

/**
 * A class stands for Obstacle that implements Piece. There are 3 types of Obstacles:
 *      1. Roadblock. A Player can't step on it. A Player must change a lane if he wants to
 *                      go forward when a Roadblock is in front of him.
 *      <p>
 *      2. Pub. When a Player's final step in a round is on this Obstacle, the Player is randomly allocated
 *               to a new position -- because he had a few pints and get drunk, so he lost his direction.
 *      <p>
 *      3. Teleport. When a Player's final step in a round is on this Obstacle, the Player exchange his
 *                      position with another random Player.
 *
 */
public class Obstacle implements Piece{
    protected int x;
    protected int y;
    protected Board board;
    protected Type type;
    protected String imageSrc;

    /**
     * Initializer. Specifying the Board this Obstacle belong to, the source Image, the Obstacle Type.
     *
     * @param board the Board this Obstacle belong to
     * @param imageSrc a string representation of a path of the Image this Obstacle uses
     * @param type a member of enum specifying the type of this Obstacle
     */
    public Obstacle(Board board, String imageSrc, Type type) {
        this.board = board;
        this.imageSrc = imageSrc;
        this.type = type;
    }

    /**
     * Three types of Obstacles.
     *
     */
    enum Type {
        Roadblock, Pub, Teleport;
    }

    /**
     * @return a String representation of this Obstacle, specifying location and type.
     */
    @Override
    public String toString() {
        return "Position: " + getX() + ", " + getY() + ". Type: " + getType();
    }

    /**
     * @return returns a x position (e.g. second square on x-axis)
     * */
    public int getX() {
        return x;
    }

    /**
     * @return returns a y position (e.g. second square on y-axis)
     */
    public int getY() {
        return y;
    }

    /**
     * @return a String representation of the type of the Obstacle.
     */
    public String getType(){
        return type.toString();
    }

    /**
     * @return a String representation a path of this Obstacle
     */
    public String getImage() {
        return imageSrc;
    }

}


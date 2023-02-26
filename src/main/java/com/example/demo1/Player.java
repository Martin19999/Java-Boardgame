package com.example.demo1;

/**
 *  Represents a Player.
 */
public class Player implements Piece {
    private Boolean is_you; // if the Player is a user (you)
    private String name; // Name
    private int score; // Score
    private Boolean finished; // If that player has crossed the finish line
    private int no_; // the sequence of this Player in the Player array (e.g. first one, or second..)
    // position of a player
    private int x;
    private int y;

    /**
     * Initialize all the information of a Player might have.
     *
     * @param name Name of the player
     * @param is_you if the Player is a user (you), the name can be specified, otherwise name is Bot+No_.
     * @param board the board a Player is on
     * @param no the sequence of this Player in a Player array
     */
    public Player(String name, Boolean is_you, Board board, int no) {
        Boolean[] board_occupancy = board.getHas_player();
        this.is_you = is_you;
        if (is_you) {
            this.name = name;
        }else {
            this.name = "Bot" + no;
        }
        this.score = 0;
        this.finished = false;
        this.x = 0;
        this.no_ = no;

        // allocate a lane for a player.
        Boolean not_found = true;
        while (not_found){
            int provisional_y = (int) Math.floor(Math.random() * (board.getLanes() -1 - 0 + 1) + 0 );
            if (!board_occupancy[provisional_y]) {
                this.y = provisional_y;
                board.setHas_player(provisional_y);
                not_found = false;
            }
        }
    }

    /**
     * Alter the position of a Player. Validation is done in Board class.
     *
     * @param x x position of a Player
     * @param y y position of a Player
     * @param board Board this Player belongs to
     */
    public void move(int x, int y, Board board){
        // when a Player cross the finish line, we still want to display him within this board
        if (getX() + x > board.getLength() - 1){
            setX(board.getLength() - 1);
        } else if (getX() + x < 0) {
            setX(0);
        } else{ // normal setting
            setX(getX() + x);
        }

        setY(getY() + y);
        // win
        if (getX() >= board.getLength() - 1){
            finished = true;
            setX(board.getLength() - 1); // set x to last position
        }
    }

    /**
     *
     * @return a String representation of a Player.
     */
    @Override
    public String toString() {
        String info = "Position: " + getX() + ", " + getY() + ". Score: " + getScore() +
                ". Finished: " + getFinished() + ". Name: " + getName();
        if (is_you){
            info += "(User)";
        }
        return info;
    }

    /**
     *
     * @return the name of this Player
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @return the score of this Player
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score of this Player
     *
     * @param score the score of this Player
     */
    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        }
    }

    /**
     *
     * @return a boolean represents if this Player has crossed the finish line
     */
    public Boolean getFinished() {
        return finished;
    }

    /**
     * Alter the finish status of this Player
     *
     * @param finished a boolean represents if this Player has crossed the finish line
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /**
     *
     * @return x position of this Player on a Board
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x position of this Player
     *
     * @param x x position of this Player on a Board
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return y position of this Player on a Board
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y position of this Player
     *
     * @param y y position of this Player on a Board
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return a string representation of the position of a Player: x and y
     */
    public String getPos(){
        return getY() + " " + getX();
    }

    /**
     *
     * @return the number sequence of this Player
     */
    public int getNo_() { return no_; }

}

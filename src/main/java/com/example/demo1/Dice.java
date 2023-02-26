package com.example.demo1;

public class Dice {

    // Dice 1: Steps, 1 - 4
    private int value;
    // Dice 2: Direction
    private Direction direction;

    /**
     * Initialisation of a Dice object. Set the number of steps a Player who rolled this dice
     * going to take. And set the direction as well.
     *
     */
    public Dice() {
        this.value = (int) Math.floor(Math.random() * (4 - 1 + 1) + 1);
        this.direction = Direction.values()[(int) Math.floor(Math.random() * (3 - 0 + 1) + 0)];
    }

    /**
     * Represents the directions a Dice could provide
     *
     */
    enum Direction {
        forward, forward_2, backward, stay
    }

    /**
     * Get the number of a Dice provides
     *
     * @return the number of a Dice provides
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the direction of a Dice provides
     *
     * @return the direction of a Dice provides
     */
    public Direction getDirection() {
        return direction;
    }







}


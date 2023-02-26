package com.example.demo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * A Game class represents the Game model. It stores information about, or gather these together:
 *      1. Board
 *      2. Players
 *      3. Obstacles
 *
 */
public class Game {

    private Board test_board;
    private int num_of_players;
    private int num_of_obstacles;

    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;

    /**
     * Initialize the Game model.
     *
     * @param name The username
     * @param lanes number of squares on a board in y-axis
     * @param length number of squares on a board in x-axis
     * @param num_of_players number of players on a board
     * @param num_of_obs number of obstacles on a board
     */
    public Game(String name, int lanes, int length,int num_of_players, int num_of_obs) {
        this.test_board = new Board(lanes, length, num_of_players, num_of_obs);
        this.num_of_players = test_board.getNum_of_players();
        this.num_of_obstacles = test_board.getNum_of_obstacles();
        this.players = new ArrayList<Player>();
        this.obstacles = new ArrayList<Obstacle>();

        // adding new player object to the players tracking list
        players.add(new Player(name,true, test_board, 0));
        for (int i = 0; i < this.num_of_players - 1; i ++){
            players.add(new Player("",false, test_board, i + 1));
        }
        // setting the player_coordinate
        for (Player p: players){
            test_board.setPlayer_coordinate(p.getX(), p.getY(), p);
        }

        // adding randomly generated obstacles to the obstacles tracking list
        String[] types = {"roadblock", "pub", "teleport"};
        int max = types.length - 1;
        int min = 0;
        for (int i = 0; i < this.num_of_obstacles; i ++){
            int choice = (int) Math.floor(Math.random() * (max - min + 1) + min );
            switch (choice) {
                case 0:{
                    Roadblock r = new Roadblock(test_board);
                    obstacles.add(r);
                    test_board.setObstacle_coordinate(r.getX(), r.getY(), r);
                    break;
                }
                case 1: {
                    Pub p = new Pub(test_board);
                    obstacles.add(p);
                    test_board.setObstacle_coordinate(p.getX(), p.getY(), p);
                    break;
                }
                case 2:{
                    Teleport t = new Teleport(test_board);
                    obstacles.add(t);
                    test_board.setObstacle_coordinate(t.getX(), t.getY(), t);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    /**
     * A player makes a move (multiple steps),
     *
     * @param dice a Dice object
     * @param noOfPlayer the sequence of a Player in the arrayList has all the Players
     * @param heads (Javafx) an arrayList of heads of the Players, Circle objects
     * @param bodies (Javafx) an arrayList of bodies of the Players, Rectangle objects
     * @return if a Player finishes all the length (i.e. reach the finish line)
     */
    public boolean move(Dice dice, int noOfPlayer, ArrayList<Circle> heads, ArrayList<Rectangle> bodies){
        Dice.Direction direction = dice.getDirection();
        int steps = dice.getValue();
        int moveOk = -1;  // see Board Class "move_ok" for detailed explanation for the meaning of moveOk.

        Player p = players.get(noOfPlayer);

        return moveSteps(noOfPlayer, heads, bodies,steps, direction, moveOk,p, 0);
    }

    // A recursive method. Essentially just a method extracted from the above method "move( )"
    // to avoid using loops, they together imitate a "for loop", with some time intervals set
    // between each iteration.
    public boolean moveSteps(int noOfPlayer, ArrayList<Circle> heads, ArrayList<Rectangle> bodies, int steps, Dice.Direction direction, int moveOk, Player p, int no_) {
        if (direction.toString().contains("forward")){
            moveOk = test_board.move_ok(1, 0, p);
        }else if (direction.toString().equals("backward")){
            moveOk = test_board.move_ok(-1, 0, p);
        }else { System.out.println("You diced Stay"); return false; }

        if (moveOk > 0){
            test_board.setPlayer_coordinate(p.getX(), p.getY(), null);
            if (direction.toString().contains("forward")) {
                p.move(1, 0, test_board);

                heads.get(noOfPlayer).setCenterX(heads.get(noOfPlayer).getCenterX() + 50);
                bodies.get(noOfPlayer).setX(bodies.get(noOfPlayer).getX() + 50);

            } else {
                p.move(-1, 0, test_board);

                heads.get(noOfPlayer).setCenterX(heads.get(noOfPlayer).getCenterX() - 50);
                bodies.get(noOfPlayer).setX(bodies.get(noOfPlayer).getX() - 50);
            }
            test_board.setPlayer_coordinate(p.getX(), p.getY(), p);
            System.out.println("Moved 1 (in x axis) success");
            // user step on Pub / Teleport
            if (no_ == steps - 1){
                if (moveOk == 2) {   // Pub,  move to a random place
                    System.out.println("You're drunk and landed in a random place");
                    GeneratePosition pos = new GeneratePosition(test_board);
                    int move_x = pos.getX() - p.getX();
                    int move_y = pos.getY() - p.getY();

                    heads.get(noOfPlayer).setCenterX(heads.get(noOfPlayer).getCenterX() + 50*move_x);
                    bodies.get(noOfPlayer).setX(bodies.get(noOfPlayer).getX() + 50*move_x);
                    heads.get(noOfPlayer).setCenterY(heads.get(noOfPlayer).getCenterY() + 50*move_y);
                    bodies.get(noOfPlayer).setY(bodies.get(noOfPlayer).getY() + 50*move_y);

                    test_board.setPlayer_coordinate(p.getX(), p.getY(), null);
                    p.move(move_x, move_y, test_board);
                    test_board.setPlayer_coordinate(pos.getX(), pos.getY(), p);

                } else if (moveOk == 3) {  //Teleport
                    System.out.println("Teleport!");
                    // choose a random player, but not self!!
                    int max = num_of_players - 1;
                    int min = 0;
                    int choice = -1;
                    boolean not_found = true;
                    while (not_found){
                        choice = (int) Math.floor(Math.random() * (max - min + 1) + min );
                        if (choice != noOfPlayer){
                            not_found = false;
                        }
                    }
                    Player other = players.get(choice);

                    int this_move_x = other.getX() - p.getX();
                    int this_move_y = other.getY() - p.getY();
                    int other_move_x = this_move_x * (-1);
                    int other_move_y = this_move_y * (-1);

                    heads.get(noOfPlayer).setCenterX(heads.get(noOfPlayer).getCenterX() + 50*this_move_x);
                    bodies.get(noOfPlayer).setX(bodies.get(noOfPlayer).getX() + 50*this_move_x);
                    heads.get(noOfPlayer).setCenterY(heads.get(noOfPlayer).getCenterY() + 50*this_move_y);
                    bodies.get(noOfPlayer).setY(bodies.get(noOfPlayer).getY() + 50*this_move_y);

                    heads.get(choice).setCenterX(heads.get(choice).getCenterX() + 50*other_move_x);
                    bodies.get(choice).setX(bodies.get(choice).getX() + 50*other_move_x);
                    heads.get(choice).setCenterY(heads.get(choice).getCenterY() + 50*other_move_y);
                    bodies.get(choice).setY(bodies.get(choice).getY() + 50*other_move_y);

                    test_board.setPlayer_coordinate(p.getX(), p.getY(), null);
                    test_board.setPlayer_coordinate(other.getX(), other.getY(), null);
                    p.move(this_move_x, this_move_y, test_board);
                    other.move(other_move_x, other_move_y, test_board);
                    test_board.setPlayer_coordinate(p.getX(), p.getY(), p);
                    test_board.setPlayer_coordinate(other.getX(), other.getY(), other);
                }
            }
        }else{
            // can't move
            // 1 - it's bc of other players, then you can choose to stay or change direction
            // 2 - it's bc of obstacles, you have to change direction, bc there'll be next turn and...

            // scenario 1 -> it's a soft no, choose to stay or change direction.

            if (moveOk == -2) {
                System.out.println("Can't move!!");
                return false;
            }


//                String userInput = "";
//                if (moveOk == 0) {
//                    if (p.getIs_you()){
//                        Scanner input = new Scanner(System.in);
//                        System.out.println("Oops you can't move in that direction, do you want to change a lane?");
//                        userInput = input.nextLine();
//                    }else{
//                        boolean stay = new Random().nextBoolean();
//                        if (stay) { userInput = "yes"; }
//                        else { userInput = "no"; }
//                    }
//                }

            // (scenario 1 -> yes + scenario 2 -> Roadblock)  change direction.
            if (moveOk == -1) {   //!!!!!!
                System.out.println("You chose to stay instead");
                return false;
            }
        }
        if (p.getFinished()){
            System.out.println("hooooo");
            return true;
        }else{
            if (no_ == steps - 1) { return false; }
        }

        if (no_ < steps - 1) {
            int finalMoveOk = moveOk;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), ev -> {
                moveSteps(noOfPlayer, heads, bodies,steps, direction, finalMoveOk,p,no_ + 1);
            }));
            timeline.play();

        }
        return false;
    }

    /**
     * Return a string representation of a Game model: Board, Players & Obstacles
     *
     * @return a string representation of a Game model
     */
    @Override
    public String toString() {
        String info = super.toString() + '\n';
        info += test_board.toString() + '\n';
        info += "^^^^^^^^ Players ^^^^^^^^" + '\n';
        for (Player p: players){
            info += p.toString() + '\n';
        }
        info += "^^^^^^^^ Obstacles ^^^^^^^^" + '\n';
        for (Obstacle o: obstacles){
            info += o.toString() + '\n';
        }
        return info;
    }

    /**
     * Get the board of this Game
     *
     * @return the board of this Game
     */
    public Board getTest_board() {
        return test_board;
    }

    /**
     * Get a list of Players in this Game
     *
     * @return an arrayList of Players in this Game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Get the number of players in this Game
     *
     * @return number of players in this Game
     */
    public int getNum_of_players() {
        return num_of_players;
    }

}


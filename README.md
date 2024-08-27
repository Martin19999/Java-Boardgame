# Java Boardgame 

<img width="1084" alt="Screenshot 2023-02-26 at 22 46 42" src="https://user-images.githubusercontent.com/116632169/221442094-20ad3375-82df-4d9b-b4c1-510ef0b4ea31.png">

What it this game about:

Several users roll the dices in turn. The first dice decide the steps you take: 1~4. The second dice decides the direction you head to. The first one crosses the finish line wins. 

If you stumble on an obstacle:

 *      1. Roadblock. A Player can't step on it. A Player must change a lane if he wants to
 *                      go forward when a Roadblock is in front of him.
 *     
 *      2. Pub. When a Player's final step in a round is on this Obstacle, the Player is randomly allocated
 *               to a new position -- because he had a few pints and get drunk, so he lost his direction.
 *     
 *      3. Teleport. When a Player's final step in a round is on this Obstacle, the Player exchange his
 *                      position with another random Player.

How to run this game:

Run it through "HelloApplication" Class.

1. A window will pop up, this is the 1st scene - a welcome page. It has a button says "Let's Begin". Click it, we will enter the 2nd scene.

2. The 2nd scene let user specify the user name, board size etc. If no name is entered, the default name "who are u" is used. If the size is invalid, default values will be used. To enter the 3rd scene, press the button "enter game".

3. The 3rd scene is a game scene. Press the "Start" button at top to start the game. User goes first. Press the "Roll" button to roll a dice, and after that all other users (bots) will play their turn automatically. When a round ends, enter the next round until someone finishes.


How to run the tests:

Under "src" folder, choose "test" folder. And choose "Java" folder (the only folder there). Then you'll see all the test files.

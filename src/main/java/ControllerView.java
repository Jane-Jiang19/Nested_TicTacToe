/*
 * Author: Jingyan Jiang
 * This class is the Controller and View in the MVC pattern.
 * It provides a function get_players() to get players' information from the users.
 * It also provides a function play() for players to play the game *
 */

import java.util.Scanner;

public class ControllerView {

    // Store the information of the first player
    public Player player1;

    // Store the information fo the second player
    public Player player2;

    // Store the model instance
    public Model model;

    public ControllerView(){
        model = new Model();
    }

    /*
     * The function to get players' information from users
     */
    public void get_players(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the type of player 1 (human or computer): ");
        String type1 = sc.nextLine();
        player1 = new Player(type1, "X");

        System.out.println("Enter the type of player 2 (human or computer): ");
        String type2 = sc.nextLine();
        player2 = new Player(type2, "O");

    }


    /*
     * The function that allows the players to play the game
     * 1. Print out all the boards
     * 2. Ask for inputs from the players in turn
     * 3. Ends the game when there is a winner on meta board
     */
    public void play(){
        while (true){
            model.print_boardlist();

            String turn_result1 = model.each_turn(player1);

            if (turn_result1.equals("win")){
                System.out.println("Player1 wins!");
                break;
            } else if (turn_result1.equals("tie")){
                System.out.println("Reached a tie!");
                break;
            }

            String turn_result2 = model.each_turn(player2);

            if (turn_result2.equals("win")){
                System.out.println("Player2 wins!");
                break;
            } else if (turn_result2.equals("tie")){
                System.out.println("Reached a tie!");
                break;
            }
        }
    }

    /*
     * All required actions happen from here.
     * 1. Initialize the Controller and View of this game
     * 2. Get players' information
     * 3. Play the game
     */
    public static void main(String[] args){
        ControllerView cv = new ControllerView();
        cv.get_players();
        cv.play();
    }



}

/*
 * Author: Jingyan Jiang
 * This class is used to store the information of the players
 * Also, it provides the input function for the players
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Player {

    // human or computer
    public String type;

    // input type: X or O
    // Assume that player1 always use "X" and player2 always use "O"
    public String input_type;

    // Player1 or Player2
    public String name;

    // Use to locate the location of subboard on the meta board
    public HashMap<Integer, Integer> meta_coordinates_dict;

    // Use to locate the location of subboard on the meta board
    public HashMap<Integer, Integer> reverse_meta_coordinates_dict;

    public Player(String type, String input_type){
        this.type = type;
        this.input_type = input_type;
        if (input_type.equals("X")){
            name = "Player1 ("+type+")";
        } else{
            name = "Player2 ("+type+")";
        }

        meta_coordinates_dict = new HashMap<>();
        reverse_meta_coordinates_dict = new HashMap<>();
        int num = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                meta_coordinates_dict.put(i*10+j, num);
                reverse_meta_coordinates_dict.put(num, i*10+j);
                num++;

            }
        }


    }

    /*
     * Input function for human players
     * @args: boardlist, which contains the meta board and the 9 subboard instances
     * @return: input, the array contains the location of input board and the input coordinates
     */
    private int[] human_input(ArrayList<Board> boardlist){
        Board meta_board = boardlist.get(0);
        Scanner scanner = new Scanner(System.in);

        // Assume that the player would input the board number following the rules below
        System.out.print("Enter the board number that you want to play on ([1-9] from left to right and from top and bottom): ");
        int board_number = scanner.nextInt();
        int meta_x = reverse_meta_coordinates_dict.get(board_number) % 10;
        int meta_y = reverse_meta_coordinates_dict.get(board_number) / 10;

        // check if the chosen board is valid or not
        while (!meta_board.board[meta_y][meta_x].equals("-")){
            System.out.println("Invalid board number! Enter again!");
            // Assume that the player would input the board number following the rules printed
            System.out.print("Enter the board number that you want to play on ([1-9] from left to right and from top and bottom): ");
            board_number = scanner.nextInt();
            meta_x = reverse_meta_coordinates_dict.get(board_number) % 10;
            meta_y = reverse_meta_coordinates_dict.get(board_number) / 10;
        }

        // Assume that the player would input the coordinates following the rules printed
        System.out.print("Enter the input coordinate x ([0-2] from left to right): ");
        int x = scanner.nextInt();
        System.out.print("Enter the input coordinate y ([0-2] from top to bottom): ");
        int y = scanner.nextInt();

        int[] input = new int[3];
        input[0] = board_number;
        input[1] = x;
        input[2] = y;

        return input;
    }

    /*
     * Input function for computer players
     * First obtain valid inputs
     * Then randomly choose from them
     * @args: boardlist, which contains the meta board and the 9 subboard instances
     * @return: input, the array contains the location of input board and the input coordinates
     */
    private int[] computer_input(ArrayList<Board> boardlist){
        Board meta_board = boardlist.get(0);

        // Get available subboards
        ArrayList<Integer> meta_coordinates = meta_board.available_xy();
        Random random = new Random();
        int meta_x_y = meta_coordinates.get(random.nextInt(meta_coordinates.size()));
        Board input_board = boardlist.get(meta_coordinates_dict.get(meta_x_y));

        //Get available coordinates on the chosen subboard
        ArrayList<Integer> board_coordinates = input_board.available_xy();

        int x_y = board_coordinates.get(random.nextInt(board_coordinates.size()));
        int[] input = new int[3];
        input[0] = meta_coordinates_dict.get(meta_x_y);
        input[1] = x_y % 10;
        input[2] = x_y / 10;

        return input;
    }

    /*
     * The input function for players
     * @args: boardlist, which contains the meta board and the 9 subboard instances
     * @return: input, the array contains the location of input board and the input coordinates
     */
    public int[] player_input(ArrayList<Board> boardlist){
        int[] input;
        if (type.equals("human")){
            input = human_input(boardlist);
        } else{
            input = computer_input(boardlist);
        }
        return input;
    }


}

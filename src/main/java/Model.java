/*
 * Author: Jingyan Jiang
 * This class is the Model in MVC pattern.
 * It provides the function each_turn()
 * to define all actions from players and the underlying model in each turn
 * In each turn, the player would make a valid input,
 * and the model would check if the player wins or not both on the subboard and meta board.
 * It also provides a function to print out all boards
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    // Store the information of all boards: meta board and 9 subboards
    public ArrayList<Board> boardlist;


    public Model(){
        boardlist = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            boardlist.add(new Board());
        }
    }

    /*
     * Print out all boards
     */
    public void print_boardlist(){
        for (int i = 0; i < 10; i++){
            if (i == 0){
                System.out.println("Meta Board:");
            } else{
                System.out.println("Board "+i+":");
            }
            boardlist.get(i).print_board();
            System.out.println("\n");
        }
    }

    /*
     * A private helper function for each_turn() function
     * to update the meta board if there is a winner on a subboard,
     * and end the game if there is a winner on the meta board
     */
    private String update_winner(Player player, int[] input){

        // Use to locate the location of subboard on the meta board
        HashMap<Integer, Integer> reverse_meta_coordinates_dict = new HashMap<>();
        int num = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                reverse_meta_coordinates_dict.put(num, i*10+j);
                num++;

            }
        }

        // check if there is a winner on a subboard
        Board input_board = boardlist.get(input[0]);
        String subwin = input_board.check_winner(player.input_type, input[1], input[2]);
        // If there is a tie on a subboard, refresh the subboard
        if (subwin.equals("tie")){
            boardlist.set(input[0], new Board());
            System.out.println("Update Board "+input[0]+":");
            boardlist.get(input[0]).print_board();

        // If there is a winner on a subboard, update and check the meta board
        } else if (subwin.equals(player.input_type)){
            int meta_x = reverse_meta_coordinates_dict.get(input[0]) % 10;
            int meta_y = reverse_meta_coordinates_dict.get(input[0]) / 10;
            Board meta_board = boardlist.get(0);
            meta_board.take_input(player.input_type, meta_x, meta_y);
            System.out.println("Update Meta Board:");
            meta_board.print_board();

            // check if there is a winner on the meta board
            String win = meta_board.check_winner(player.input_type, meta_x, meta_y);
            if (win.equals(player.input_type)){
                return "win";

            } else if (win.equals("tie")){
                return "tie";

            }
        }
        // No winner on the meta board
        return "continue";
    }


    /*
     * The function to define all actions from the players and the underlying model on each turn
     * @args:
     * Player player: the player for this turn
     * @return:
     * String turn_result: the game result of each turn
     * "tie" if there is a tie on meta board；
     * "win" if there is a winner on meta board and ends the game
     * "continue" the game could continue
     */
    public String each_turn(Player player){
        System.out.println(player.name+"'s turn");
        boolean valid = false;
        int[] input = new int[3];
        Board input_board = new Board();
        while (!valid){
            input = player.player_input(boardlist);
            input_board = boardlist.get(input[0]);
            valid = input_board.take_input(player.input_type, input[1], input[2]);
        }

        System.out.println("Update Board "+input[0]+":");
        input_board.print_board();

        // check if there is a winner
        String turn_result = update_winner(player, input);

        return turn_result;
    }


}

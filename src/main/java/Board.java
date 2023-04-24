/*
 * Author: Jingyan Jiang
 * This class is used to store the information of the boards
 * Also, it provides an input function,
 * a function to check if there is a winner,
 * a function to return all available coordinates on the board,
 * and a function to print out the board.
 */

import java.util.ArrayList;

public class Board {

    public String[][] board;
    public int count;

    public Board(){
        this.board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "-";
            }
        }
        this.count = 0;
    }

    /*
     * A function to take input coordinates and check if they are valid
     * If valid, update the board and return true
     * If not, return false
     * Assume that x and y both belongs to [0, 2],
     * x_o only contains "X" or "O"
     */
    public boolean take_input(String x_o, int x, int y){
        if (board[y][x].equals("-")){
            board[y][x] = x_o;
            count++;
            return true;
        } else{
            System.out.println("Invalid Input! Enter again!");
            return false;
        }
    }


    /*
     * For each turn, check if there is a winner after a valid input
     * @args:
     * String x_o: input type
     * int x: input coordinates
     * int y: input coordinates
     * @return:
     * String x_o:
     * the input type of the winner if there is a winner;
     * "tie" if there is a tie;
     * "continue" if there is no winner     *
     */
    public String check_winner(String x_o, int x, int y){

        // horizontally
        if (board[y][(x+1)%3].equals(x_o) && board[y][(x+2)%3].equals(x_o)){
            return x_o;
        }

        // vertically
        if (board[(y+1)%3][x].equals(x_o) && board[(y+2)%3][x].equals(x_o)){
            return x_o;
        }

        // diagonally
        if (board[0][0].equals(x_o) && board[1][1].equals(x_o) && board[2][2].equals(x_o)){
            return x_o;
        } else if (board[0][2].equals(x_o) && board[1][1].equals(x_o) && board[2][0].equals(x_o)){
            return x_o;
        }

        // No winner, check if it is a tie
        if (count == 9){
            return "tie";
        } else{
            return "continue";
        }

    }

    /*
     * A function to get all available coordinates on the board
     */
    public ArrayList<Integer> available_xy(){
        ArrayList<Integer> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j].equals("-")){
                    coordinates.add(i*10+j);
                }
            }
        }
        return coordinates;
    }

    /*
     * A function to print out the board
     */
    public void print_board(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

}

// Exercise 8.18: TicTacToe.java
// Program that plays the game of tic-tac-toe.
// Written by Nixon Pastor

import java.util.Scanner;

public class TicTacToe{
    private final int BOARDSIZE = 3; // size of the board
    private enum Status { WIN, DRAW, CONTINUE }; // game states
    private char[][] board; // board representation
    private boolean firstPlayer; // whether it's player 1's move
    private boolean gameOver; // whether game is over

    // Constructor
    public TicTacToe() {
        board = new char[BOARDSIZE][BOARDSIZE];
        firstPlayer = true;
        gameOver = false;
    }

    // start game
    public void play() {
        Scanner input = new Scanner(System.in);
        int row; // row for next move
        int column; // column for next move

        System.out.println("Player X's turn.");

        while (!gameOver) {
            if(firstPlayer){
                printBoard();
                System.out.print("Enter your row: ");
                row = input.nextInt();
                System.out.print("Enter your column: ");
                column = input.nextInt();

                while(!validMove(row-1, column-1)) {
                    System.out.print("Enter your row: ");
                    row = input.nextInt();
                    System.out.print("Enter your column: ");
                    column = input.nextInt();
                }

                board[row-1][column-1] = 'X';
                System.out.println();
                printStatus('X');
                firstPlayer = false;
            }
            else{
                printBoard();
                System.out.print("Enter your row: ");
                row = input.nextInt();
                System.out.print("Enter your column: ");
                column = input.nextInt();

                while(!validMove(row-1, column-1)) {
                    System.out.print("Enter your row: ");
                    row = input.nextInt();
                    System.out.print("Enter your column: ");
                    column = input.nextInt();
                }

                board[row-1][column-1] = 'O';
                System.out.println();
                printStatus('O');
                firstPlayer = true;

            }

        }
    }

    // show game status in status bar
    private void printStatus(int player) {
        Status status = gameStatus();

        // check game status
        switch (status) {
            case WIN:
                System.out.printf("Player %c wins.\n", player);
                printBoard();
                gameOver = true;
                break;
            case DRAW:
                System.out.println("Game is a draw.\n");
                printBoard();
                gameOver = true;
                break;
            case CONTINUE:
                if (player == 'X')
                    System.out.println("Player O's turn.");
                else
                    System.out.println("Player X's turn.");
                break;
        }
    }

    // get game status
    private Status gameStatus() {
        int a;

        // check for a win on diagonals
        // check for win in rows
        // check for win in columns
        // check for a completed game

        if(board[0][0] != 0 && board[1][1] == board[0][0] && board[2][2] == board[0][0]){
            return Status.WIN;
        }
        else if(board[2][0] != 0 && board[1][1] == board[2][0] && board[0][2] == board[2][0] ){
            return Status.WIN;
        }

        for(int i = 0; i < board.length; i++){
            if(board[i][0] != 0 && board[i][1] == board[i][0] && board[i][2] == board[i][0]){
                return Status.WIN;
            }
        }

        for(int i=0; i < board.length; i++){
            if(board[0][i] != 0 && board[1][i] == board[0][i] && board[2][i] == board[0][i]){
                return Status.WIN;
            }
        }


        for(int i=0; i < board.length; i++){
            for(int j=0; j < board[i].length; j++){
                if(board[i][j] == 0){
                    return Status.CONTINUE;
                }
            }
        }

        return Status.DRAW;

    }

    // display board
    public void printBoard() {
        System.out.println(" _______________________ ");

        for (int row = 0; row < BOARDSIZE; row++) {
            System.out.println("|       |       |       |");

            for (int column = 0; column < BOARDSIZE; column++) {
                printSymbol(column, board[row][column]);
            }

            System.out.println("|_______|_______|_______|");
        }
    }

    // print moves
    private void printSymbol(int column, char value) {
        System.out.printf("|   %c   ", value);

        if (column == 2) {
            System.out.println("|");
        }
    }

    // validate move
    private boolean validMove(int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3 &&
                board[row][column] == 0;
    }
}



import java.util.Random;
import java.util.Scanner;

public class TicTac {
    static {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t  ________________________    _______________________");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t |___  ___|___  ___|  ____|  |___  ___|   _   |  ____|");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t    |  |     |  |  |  |   ____  |  |  |  |_|  |  |");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t    |  |     |  |  |  |  |____| |  |  |   _   |  |");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t    |  |   __|  |__|  |___      |  |  |  | |  |  |___");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t    |__|  |________|______|     |__|  |__| |__|______|");
        System.out.println("\n\n");
    }

    private Scanner sc = new Scanner(System.in);
    private char[] board = new char[9];
    private char turn = 'X';
    private Random random = new Random();
    private int winX = 0;
    private int winO = 0;
    private String name;
    private char myChoice = 'O';

    public static void main(String[] args) {
        TicTac t = new TicTac();
        int numberOfGames = 1;
        long start, end;
        start = System.currentTimeMillis();
        do {
            t.play();
            numberOfGames--;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t----------------------------------------------------------------");
        } while (numberOfGames > 0);
        end = System.currentTimeMillis();
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\tTime taken by the program to execute is : " + (end - start));
    }

    private void playersTurn() {
        int r;
        boolean check = false;
        do {
            System.out.println("\n\n\t\t\t\t\t\t\t\twhere do you wnt to play , select from 1 - 9 :");
            try {
                r = getChoice();
                if (!(r > 0 && r < 10)) {
                    Thread.sleep(1000);
                    throw new NumberFormatException("Invalid Number");
                }
                r -= 1;
                if (board[r] == ' ') {
                    board[r] = turn;
                    check = true;
                } else if (board[r] == myChoice) {
                    System.out.println("you already played there");
                } else {
                    System.out.println("computer played there");
                }
            } catch (Exception e) {
                System.out.println("invalid choice. \t ");
            }
        } while (!check);
    }

    private int getChoice() {
        String x = "1";

        try {
            x = sc.nextLine();
            if (x.matches("^[1-9]$")) {

            } else {

                throw new NumberFormatException("Invalid Number");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(x);
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    private void switchPlayer() {
        if (turn == 'X') {
            turn = 'O';
        } else if (turn == 'O') {
            turn = 'X';
        }
    }

    private boolean win() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = turn;
                if (checkWin()) {
                    return true;
                } else {
                    board[i] = ' ';
                }
            }
        }
        return false;
    }

    private void attack() {
        boolean check = false;
        do {
            int r = random.nextInt(9);
            if (r != 4) {
                r = random.nextInt(9);
            }
            if (board[r] == ' ') {
                board[r] = turn;
                check = true;
            }
        } while (!check);
    }

    private boolean defence() {
        switchPlayer();
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = turn;
                if (checkWin()) {
                    switchPlayer();
                    board[i] = turn;
                    return true;
                } else {
                    board[i] = ' ';
                }
            }
        }
        switchPlayer();
        return false;
    }

    private boolean filled() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }

    private void play() {
        initializeBoard();
        do {
            if (win()) {
                switchPlayer();
            } else if (defence()) {
                switchPlayer();
            } else {
                attack();
                switchPlayer();
            }
            display();
            if (turn == myChoice && !checkWin() && !filled()) {
                playersTurn();
                switchPlayer();
                display();
            }
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println("\n");
        } while (!filled() && !checkWin());
        if (checkWin()) {
            switchPlayer();
            if (turn == 'O') {
                winO++;
            } else {
                winX++;
            }
            System.out.println();
            display();
            System.out.println("\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t  " + turn + "  has won");
        } else {
            System.out.println();
            display();
            System.out.println("\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t   game is draw... ");
        }
    }

    private boolean checkWin() {
        return (board[0] == board[1] && board[1] == board[2] && board[0] != ' ' || board[3] == board[4] && board[4] == board[5] && board[3] != ' ' || board[6] == board[7] && board[7] == board[8] && board[6] != ' ' || board[0] == board[4] && board[4] == board[8] && board[0] != ' ' || board[2] == board[4] && board[4] == board[6] && board[2] != ' ' || board[0] == board[3] && board[3] == board[6] && board[0] != ' ' || board[1] == board[4] && board[4] == board[7] && board[1] != ' ' || board[2] == board[5] && board[5] == board[8] && board[2] != ' ');
    }

    private void display() {

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + board[0] + " | " + board[1] + " | " + board[2] + "           PLAYER 'O' | CPU  'X'");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---|---|---            ---------|---------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + board[3] + " | " + board[4] + " | " + board[5] + "                      |");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---|---|---" + "\t   \t\t\t\t" + winO + "\t|\t" + winX);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + board[6] + " | " + board[7] + " | " + board[8] + "                      |");
    }
}

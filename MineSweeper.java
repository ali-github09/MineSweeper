import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class MineSweeper {
    int rowNumber, colNumber, size;
    String[][] map;
    String[][] board;
    boolean game = true;

    Scanner input = new Scanner(System.in);
    Random scan = new Random();

    MineSweeper(int rowNumber, int colNumber){
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
        this.map = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }

    public void run() {
       int row, col, success = 0;
       prepare();
       print(map);
        System.out.println("Game has started !");

        // while ile kullanıcıdan bir sayı ve sütun değeri isteyip orada mayın olup olmadığını kontrol ediyoruz.

        while (game) {
            print(board);
            System.out.print("Row : ");
            row = input.nextInt();
            System.out.print("Column : ");
            col = input.nextInt();

            if (row < 0 || row >= rowNumber) {
                System.out.println("Unvalid dimension !");
                continue;
            }

            if (col < 0 || row >= colNumber) {
                System.out.println("Unvalid dimension !");
                continue;
            }

            if ( !map[row][col].equals("*") ) {
                checkMine(row, col);
                success++;
                if (success == ( size - (size/4)) ) {
                    System.out.println("Congraulations. You won the Game ! ");
                    break;
                }
            } else {
                game = false;
                System.out.println("Game Over !");
            }
        }
    }

    public void prepare() {
        int randomRow, randomCol, count = 0;
        while (count != (size/4)) {
            randomRow = scan.nextInt(0,this.rowNumber);
            randomCol = scan.nextInt(0,this.colNumber);
            if ( map[randomRow][randomCol] != null && !map[randomRow][randomCol].equals("*")) {
                map[randomRow][randomCol] = "*";
                count++;
            }
        }
    }

    public void print (String[][] arr) {
        System.out.println(Arrays.toString(map));
    }

    public void checkMine (int r, int c) {
        if (   board[r][c].equals("-")) {
            if ( (c < colNumber-1) && (map[r][c+1].equals("*"))) {
                board[r][c] = "1";
            }
            if ( (r < rowNumber-1) && (map[r+1][c].equals("*")) ) {
                board[r][c] = "1";
            }
            if ( (r > 0) && (map[r-1][c].equals("*")) ) {
                board[r][c] = "1";
            }
            if ( (c > 0) && (map[r][c-1].equals("*")) ) {
                board[r][c] = "1";
            }
            if (board[r][c].equals("-")) {
                board[r][c] = "2";
            }
        }
    }
}

import java.util.Random;
import java.util.Scanner;


public class MineSweeper {
    int rowNumber, colNumber, size;
    String[][] map;
    String[][] board;
    boolean game = true;
    int success;

    Scanner input = new Scanner(System.in);
    Random scan = new Random();

    // kurucu metodumuzu oluşturuyoruz.
    MineSweeper(int rowNumber, int colNumber){
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
        this.map = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }

    // oyunu çalıştırıyoruz.
    public void run() {
       int row, col;
       prepare();
       System.out.println("Revieled Mine Field");
       print(map);
       System.out.println("Game has started !");

        // while ile kullanıcıdan bir sayı ve sütun değeri isteyip orada mayın olup olmadığını kontrol ediyoruz.
        while (game) {
            print(board);
            System.out.print("Row : ");
            row = input.nextInt();
            System.out.print("Column : ");
            col = input.nextInt();

            // ilk önce girilen değerlerin satır ve sütun sayısından küçük ve 0'dan büyük olmasını kontrol ediyoruz
            if (row < 0 || row >= rowNumber) {
                System.out.println("Unvalid dimension !");
                continue;
            }

            if (col < 0 || col >= colNumber) {
                System.out.println("Unvalid dimension !");
                continue;
            }

            //eğer mayın yoksa seçilen kutunun etrafını kontrol ediyoruz.
            if ( !map[row][col].equals("*") ) {
                checkMine(row, col);
                if (success == ( size - (size/4)) ) {
                    System.out.println("Congraulations. You won the Game ! ");
                    break;
                }
            //eğer mayın varsa direk false dönüp oyunu bitiriyoruz
            } else {
                game = false;
                System.out.println("Game Over !");
            }
        }
    }

    // ilk defa seçilen bir kutu ise etrafındaki mayın sayısını yazdırıyoruz.
    // daha önce seçilmiş ise kullanıcıyı başka bir kutu seçmesi için uyarıyoruz.
    public void checkMine (int r, int c) {
        if (board[r][c].equals("-")) {
            calcSurroundingMineNumber(r,c);
            success++;
        } else {
            System.out.println("You have opened that box ! Please select another one");
        }
    }

    // kutunun etrafındaki (alt,süt,sağ,sol, ve tüm köşeler olmak üzere 8 kutu) kutularda mayın olup olmadığını kontrol ediyoruz.
    public void calcSurroundingMineNumber(int row, int col) {
        int minecount = 0;

        //etraftaki msyın sayısını buluyoruz.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < rowNumber && newCol >= 0 && newCol < colNumber) {
                    if (map[newRow][newCol].equals("*")) {
                        minecount++;
                    }
                }
            }
        }
        // bulduğumuz mayın sayısını o kutunun içine bir string değer olarak atıyoruz.
        String stringminecount = String.valueOf(minecount);
            board[row][col] = stringminecount;
    }


    // kullanıcının göreceği (board) ve mayınların döşeneceği (map) alanları oluşturuyoruz.
    public void prepare() {
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                map[i][j] = "-";
                board[i][j] = "-";
            }
        }

        // mayın tarlasına boyutların 1/4'ü kadar mayın döşüyoruz.
       int randomRow, randomCol, count = 0;
       while (count != (size/4)) {
            randomRow = scan.nextInt(rowNumber);
            randomCol = scan.nextInt(colNumber);
            if (!map[randomRow][randomCol].equals("*")) {
                map[randomRow][randomCol] = "*";
                count++;
            }
        }
    }

    // seçtiğimiz dizileri ekrana matris şeklinde yazdırıyoruz.
    public void print (String[][] arr) {
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
    }


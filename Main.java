import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int row, column;
        System.out.println("Welcome to MineSweeper Game !");
        System.out.println("Please type the dimensions");
        System.out.print("row number : ");
        row = input.nextInt();
        System.out.print("column number : ");
        column = input.nextInt();

        MineSweeper mine = new MineSweeper(row,column);
        mine.run();
    }
}

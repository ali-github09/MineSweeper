import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int row, column;
        System.out.println("Welcome to MineSweeper Game !");
        System.out.println("Please type the dimensions");

        // eğer girilen satır ve sütun sayısı 2'den küçük ise kullanıcıdan tekrar doğru bir değer girmesini istiyoruz.
        do {
            System.out.print("Row number : ");
            row = input.nextInt();
            System.out.print("Column number : ");
            column = input.nextInt();
            if(row < 2)
                System.out.println("Row number must be greater than 2");
            if(column < 2)
                System.out.println("Column number must be greater than 2");
        } while(row < 2 || column < 2);

        // kurucu metodumuzdan yeni bir nesne oluşturarak
        MineSweeper mine = new MineSweeper(row,column);
        mine.run();
    }
}

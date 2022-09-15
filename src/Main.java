import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);

        System.out.print("Satır boyutu girin:");
        int rowNumber=input.nextInt();
        
        System.out.print("Sütun boyutu girin:");
        int colNumber=input.nextInt();

        MineSweeper mine=new MineSweeper(rowNumber,colNumber);
        mine.run();
        input.close();
    }
}

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber;
    int colNumber;
    String [][] map;
    int mineNumber;
    Random random=new Random();
    Scanner input=new Scanner(System.in);


    MineSweeper(int rowNumber,int colNumber){
        this.rowNumber=rowNumber;
        this.colNumber=colNumber;
        this.map=new String[this.rowNumber][this.colNumber];
        this.mineNumber=(this.rowNumber*this.colNumber)/4;//Mayın sayımız elaman sayısının çeyreği kadar.
        for (String[] i : map) {
            Arrays.fill(i, "-");            
        }

    }

    void run(){
        String [][] temp = new String[map.length+2][map[0].length+2];
        int GameCount=rowNumber*colNumber-mineNumber; //Oyunda elaman sayısı-mayın sayısı kadar seçim olasılığımız var.
        int row;
        int col;

        mine(map);
        printMap();

        while(GameCount>0){ //Oyun seçim olasılığı kadar süren döngü.
            System.out.print("Satır giriniz : ");
            row=input.nextInt();
            System.out.print("Sütun giriniz: ");
            col=input.nextInt();
            while(row<0 || col<0 || row>=map.length || col>=map[0].length){//Girilen satır ve sütunun oluşturulan matris ile uygunluğunu kontrol eden koşul.
                System.out.print("Tekrar satır giriniz : ");
                row=input.nextInt();
                System.out.print("Tekrar sütun giriniz: ");
                col=input.nextInt();
            }

            createNewMap(temp);

            if(map[row][col] == "*"){
                System.out.print("Kaybettiniz!");
                return;
            }else{
                if(map[row][col]=="-"){
                    map[row][col]=Integer.toString(getCountOfMine(row,col,temp));
                    GameCount--;
                }
            }
            System.out.println("===========================");
            printMap();
        }
        System.out.print("Tebrikler. Kazandınız! ");
    }
    void createNewMap(String [][] temp){
        for( int i=0;i< temp.length;i++){
            for(int j=0;j< temp[0].length;j++){
                if(i==0)
                    temp[i][j]="-";
                else if(j==0)
                    temp[i][j]="-";
                else if(j==temp[0].length-1)
                    temp[i][j]="-";
                else if(i==temp.length-1)
                    temp[i][j]="-";
                else
                    temp[i][j]=map[i-1][j-1];
            }
        }
    }
    int getCountOfMine(int row,int col, String[][] temp){//Mayın olmayan yer seçildiğinde çevresindeki mayın sayısını veren metod.
        int total=0;
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(temp[i][j]=="*")
                    total++;
            }
        }
        return total;
    }
    void mine(String [][] matrix){ //Random mayın yerleştirme metodu.
        int x,y;
        for(int i=0;i<mineNumber;i++){
            x=random.nextInt(map.length);
            y=random.nextInt(map[0].length);
            if(map[x][y] == "-")
                map[x][y]="*";
            else
                i--;
        }
    }
    void printMap(){
        for (String[] i: map){
            for (String j :i){
                if(j=="*" || j== "-")       //Mayını gizli bir şekilde göstermek için oluşturulan koşul.
                    System.out.print("-");
                else
                    System.out.print(j);    //Mayın olmayan indexlerin değerini yazdıran satır.
            }
            System.out.println();
        }
    }
}
import java.util.*;

public class TannenbaumAB
{
    public static void main(String[] args){
        Scanner eingabe = new Scanner(System.in);
        int hoehe = eingabe.nextInt();

        for (int i = 0; i < hoehe; i++){
            printSpaces(i, hoehe, " ");
            printLeaves(i, "*");
            System.out.println();
        }
        printSpaces(0, hoehe, " ");
        System.out.print("I");
    }
    public static void printSpaces(int i, int hoehe, String letter){
        for (int j = 0; j < hoehe - i - 1; j++) {
            System.out.print(letter);
        }
    }
    public static void printLeaves(int i, String letter){
        for (int j = 0; j < i * 2 + 1; j++) {
            System.out.print(letter);
        }
    }
}
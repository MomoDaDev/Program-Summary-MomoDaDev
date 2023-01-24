import java.util.*;
import java.util.Random;

public class Statistik{
    static Random random;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int userinput = input.nextInt();
        long startTime = System.nanoTime();

        random = new Random();
        int[] statistic = new int[10];

        for (int i = 0; i < userinput; i++) {
            statistic[getZufall()]++;
        }

        int max = 0;
        for (int i = 0; i < statistic.length; i++) {
            if(statistic[i] > max){
                max = statistic[i];
            }
        }

        int X_value = (max - 1) / 50 + 1;

        for (int i = 0; i < statistic.length; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < statistic[i] / X_value; j++) {
                System.out.print("X");
            }
            System.out.println();
        }

        System.out.println("One X is equivalent to " + X_value + " times");

        long stopTime = System.nanoTime();
        long estimated_time = stopTime - startTime;
        System.out.println("Estimated time: " + (estimated_time / 1000000000.0) + " seconds");
    }

    public static int getZufall(){
        return random.nextInt(10);
    }
}
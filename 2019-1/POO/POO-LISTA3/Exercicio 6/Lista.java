import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Lista {
    public static void main(String[] args) {
        Random gerador = new Random();
        int[] inteiros = new int[100];
        for(int i = 0; i < 100; i++) {
            inteiros[i] = gerador.nextInt();
        }
        Arrays.sort(inteiros);
        for(int j = 0; j < 100; j++) {
            System.out.println(inteiros[j]);
        }
    }

}
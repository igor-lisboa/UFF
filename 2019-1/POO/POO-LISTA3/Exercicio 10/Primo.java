import java.util.Scanner;

public class Primo implements Runnable{
    private int numero = 0;
    private static int n = 0;

    public synchronized int proximoNumero() {
        return numero++;
    }
    
    @Override
    public void run() {
        for(int k = 0; k < n; k++) {
            numero = proximoNumero();
            int div = 0;
            for(int j = 1; j < numero + 1; j++) {
                if(numero % j == 0)
                    div++;
            }
            if(div == 2)
                System.out.println(numero);
        }
    }
    
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite um numero inteiro: ");
        n = ler.nextInt();
        Runnable primo = new Primo();
        for(int i = 0; i < 2; i++) {
            Thread t = new Thread(primo);
            t.start();
        }
    }

}
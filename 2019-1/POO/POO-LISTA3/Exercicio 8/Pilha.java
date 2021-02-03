import java.util.ArrayList;
import java.util.Random;

public class Pilha {
    static ArrayList<Integer> caracteres;
    static int max;
    static Random gerador;

    public Pilha(int max) {
        this.caracteres = new ArrayList<Integer>();
        this.max = max;
        this.gerador = new Random();
    }

    public static void main(String[] args) {
        Pilha p = new Pilha(1000);
        new Thread(produtor).start();
        new Thread(consumidor).start();
    }

    private static Runnable produtor = new Runnable() {
        public void adicionaCaracter() {
            for(int i = 0; i < 1000; i++)
                if(caracteres.size() < max)
                    caracteres.add(gerador.nextInt());
                else
                    consumidor.join();
        }
    };

    private static Runnable consumidor = new Runnable() {
        public void retiraCaracter() {
            if(caracteres.size() > 0) {
                System.out.println("Caracter removido: "+ caracteres.get(1));
                caracteres.remove(1);
            } else {
                produtor.join();
            }
        }
    };

}
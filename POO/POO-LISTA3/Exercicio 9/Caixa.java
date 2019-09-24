import java.util.Scanner;
import java.util.ArrayList;

public class Caixa {
    private Scanner ler;
    public ArrayList<String> pedidos;
    private final int max = 20;

    public Caixa() {
        this.ler = new Scanner(System.in);
        this.pedidos = new ArrayList<String>();
    }

    public Runnable adicionaPedido = new Runnable() {
        public void run() {
            System.out.println("Digite o nome do prato:");
            String p = ler.nextLine();
            if(pedidos.size() < max) {
                pedidos.add(p);
            } else {
                retiraPedido.join();
            }
        }
    };
    
}
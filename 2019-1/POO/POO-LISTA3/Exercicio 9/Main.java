public class Main {
    public static void main(String[] args) {
        Caixa caixa = new Caixa();
        Cozinha cozinha = new Cozinha();
        Cozinheiro cozinheiro = new Cozinheiro();
        new Thread(caixa.adicionaPedido).start();
        new Thread(cozinha.retiraPedido).start();
    }

}
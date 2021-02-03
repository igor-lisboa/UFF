public class Cozinha {

    public Cozinha() {

    }

    public Runnable retiraPedido = new Runnable() {
        public void run() {
            String pedido = caixa.pedidos.get(1);
            int quantidade = 0;
            for(int i = 0; i < caixa.pedidos.size(); i++) {
                if(caixa.pedidos.get(i) == pedido) {
                    quantidade++;
                    caixa.pedidos.remove(i);
                }
            cozinheiro.fazPrato(pedido, quantidade);
            }
        }
    };

}
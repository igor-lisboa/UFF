public class Laptop implements Aparelho {
    @Override
    public void armazena() {
        System.out.println("Armazenando Laptop");
    }
    @Override
    public void embala() {
        System.out.println("Embalando Laptop");
    }
    @Override
    public void monta() {
        System.out.println("Montando Laptop");
    }
    @Override
    public void testa() {
        System.out.println("Testando Laptop");
    }
}
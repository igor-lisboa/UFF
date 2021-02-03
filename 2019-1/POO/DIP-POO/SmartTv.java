public class SmartTv implements Aparelho {
    @Override
    public void armazena() {
        System.out.println("Armazenando SmartTv");
    }
    @Override
    public void embala() {
        System.out.println("Embalando SmartTv");
    }
    @Override
    public void monta() {
        System.out.println("Montando SmartTv");
    }
    @Override
    public void testa() {
        System.out.println("Testando SmartTv");
    }
}
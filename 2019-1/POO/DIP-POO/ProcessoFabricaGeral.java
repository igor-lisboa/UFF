public class ProcessoFabricaGeral {
    public void fabrica(Aparelho x) {
        x.monta();
        x.testa();
        x.embala();
        x.armazena();
    }
}
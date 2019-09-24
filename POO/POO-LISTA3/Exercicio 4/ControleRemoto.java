public interface ControleRemoto {

    void aumentarVolume();
    void diminuirVolume();
    void aumentarCanal();
    void diminuirCanal();
    void trocarCanal(int canal);
    int consultaVolume();
    int consultaCanal();

}
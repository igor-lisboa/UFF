public class Televisao implements ControleRemoto {
    private int volume;
    private int canal;

    public Televisao() {
        this.volume = 0;
        this.canal = 0;
    }

    public void aumentarVolume() {
        if(this.volume < 100)
            this.volume++;
        else
            System.out.println("Volume no maximo!");
    }

    public void diminuirVolume() {
        if(this.volume > 0)
            this.volume--;
        else
            System.out.println("Volume no minimo");
    }

    public void aumentarCanal() {
        this.canal++;
    }

    public void diminuirCanal() {
        this.canal--;
    }

    public void trocarCanal(int canal) {
        this.canal = canal;
    }

    public int consultaVolume() {
        return this.volume;
    }

    public int consultaCanal() {
        return this.canal;
    }

}
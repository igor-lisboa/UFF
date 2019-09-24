public class Elevador {
    private int andarAtual;
    private int totalAndares;
    private int capacidadeElevador;
    private int pessoasNoElevador;

    public Elevador() {
        this.andarAtual = 0;
        this.pessoasNoElevador = 0;
    }

    public void inicializa(int totalAndares, int capacidadeElevador) {
        this.totalAndares = totalAndares;
        this.capacidadeElevador = capacidadeElevador;
    }

    public void entra() {
        if(this.pessoasNoElevador < this.capacidadeElevador)
            this.pessoasNoElevador++;
        else
            System.out.println("O elevador esta cheio!");
    }

    public void sai() {
        if(this.pessoasNoElevador > 0)
            this.pessoasNoElevador--;
        else
            System.out.println("O elevador esta vazio!");
    }

    public void sobe() {
        if(this.andarAtual < this.totalAndares)
            this.andarAtual++;
        else
            System.out.println("O elevador ja se encontra no ultimo andar!");
    }

    public void desce() {
        if(this.andarAtual > 0)
            this.andarAtual--;
        else
            System.out.println("O elevador ja se encontra no terro");
    }

}
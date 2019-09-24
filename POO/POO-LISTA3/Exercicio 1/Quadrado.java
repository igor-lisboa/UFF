public class Quadrado extends Figura {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    public double getLado() {
        return this.lado;
    }

    public double calculaArea() {
        return lado * lado;
    }

}
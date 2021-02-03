public class Circulo extends Figura {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    public double getRaio() {
        return this.raio;
    }

    public double calculaArea() {
        return 3.14 * Math.pow(raio, 2);
    }

}
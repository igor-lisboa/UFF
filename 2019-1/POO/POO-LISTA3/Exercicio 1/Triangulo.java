public class Triangulo extends Figura {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return this.base;
    }

    public double getAltura() {
        return this.altura;
    }

    public double calculaArea() {
        return (base * altura) / 2;
    }

}
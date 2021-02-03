public class Main {
    public static void main(String[] args) {
        Figura q = new Quadrado(4);
        Figura t = new Triangulo(5, 8);
        Figura r = new Retangulo(7, 12);
        Figura c = new Circulo(35);
        System.out.println(q.calculaArea());
        System.out.println(t.calculaArea());
        System.out.println(r.calculaArea());
        System.out.println(c.calculaArea());
    }

}
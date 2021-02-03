package br.com.banco;

/**
 * ContaCorrente
 */
public class ContaCorrente extends Conta implements Tributavel{

    private Cliente titular;
    public ContaCorrente(Cliente titular, int numero, String agencia, String dataAbertura) {
        super(titular, numero, agencia, dataAbertura);
    }

    //15% ao ano sobre o saldo
    public double getValorImposto() {
        return super.getSaldo() * 0.15;
    }

    public String getTipo() {
        return "Conta Corrente";
    }

    public double calcularRendimento(int periodo) {
        return (Math.pow(1.1, periodo) - 1);
    }

    public double calcularTributacao(int periodo) {
        return calcularRendimento(periodo) * 0.15;
    }

    public String getTitular() {
        return this.titular.toString();
    }

}
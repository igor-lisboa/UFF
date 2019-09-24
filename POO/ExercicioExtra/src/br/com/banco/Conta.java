package br.com.banco;

/**
 * Conta
 */
abstract public class Conta {
    private Cliente titular;
    private int numero;
    private String agencia;
    private double saldo;
    private String dataAbertura;

    public Conta(Cliente titular, int numero, String agencia, String dataAbertura) {
        this.titular=titular;
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = 0;
        this.dataAbertura=dataAbertura;
    }

    public Cliente recuperaTitular(){
        return this.titular;
    }

    public int recuperaNumero(){
        return this.numero;
    }

    public String recuperaAgencia(){
        return this.agencia;
    }

    /**
     * @return the dataAbertura
     */
    public String recuperaDataAbertura() {
        return dataAbertura;
    }

    abstract String getTipo();

    public boolean sacar(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    abstract public double calcularRendimento(int periodo);

    abstract public double calcularTributacao(int periodo);

    public boolean transferir(double valor, Conta contaDestino) {
        if (sacar(valor)) {
            contaDestino.depositar(valor);
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double calcularSaldoFuturo(int periodo) {
        return this.saldo * (1 + this.calcularRendimento(periodo) - this.calcularTributacao(periodo));
    }
}
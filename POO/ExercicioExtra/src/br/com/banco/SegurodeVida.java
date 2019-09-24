package br.com.banco;

/**
 * SegurodeVida
 */
public class SegurodeVida implements Tributavel{
    private int apolice;
    private double valor;
    private Cliente titular;

    public SegurodeVida(Cliente titular, int apolice, double valor) {
        this.titular = titular;
        this.setApolice(apolice);
        this.valor = valor;
    }

    /**
     * @return the apolice
     */
    public int getApolice() {
        return apolice;
    }

    /**
     * @param apolice the apolice to set
     */
    public void setApolice(int apolice) {
        this.apolice = apolice;
    }

    public String getTipo() {
        return "Seguro de Vida";
    }

    //faixa fixa de R$500 + 2% do valor do seguro
    public double getValorImposto() {
        return 500 + this.valor * 0.02;
    }

    public String getTitular() {
        return this.titular.toString();
    }
}
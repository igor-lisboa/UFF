package br.com.banco;

/**
 * Cliente
 */
public class Cliente implements Ativos{

    private String primeiroNome;
    private String ultimoNome;
    private String cpf;

    public Cliente(String primeiroNome, String ultimoNome, String cpf) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.cpf = cpf;
    }

    public String toString() {
        return "Nome:" + this.primeiroNome + " " + this.ultimoNome + " | CPF:" + this.cpf;
    }

    public void ativos(ContaCorrente contaC, ContaPoupanca contaP, SegurodeVida seguroV) {
        Object[] ativos = new Object[3];
        ativos[0]=contaC;
        ativos[1]=contaP;
        ativos[2]=seguroV;
        for (Object ativo : ativos) {
            if(ativo instanceof Tributavel){
                System.out.println(((Tributavel) ativo).getTipo() + ": Imposto devido: " + ((Tributavel) ativo).getValorImposto());
            } else {
                System.out.println(((Conta) ativo).getTipo() + ": Ativo não tributável.");
            }
        }
        return;
    }
}
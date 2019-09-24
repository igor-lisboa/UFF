package app;

import br.com.banco.*;

public class App {
    public static void main(String[] args) throws Exception{

        Cliente cliente1 = new Cliente("Flavio", "Seixas", "123456");

        ContaCorrente contaCorrente = new ContaCorrente(cliente1, 1, "UFF", "03/04/2019");
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente1, 1, "UFF", "03/04/2019");
        SegurodeVida contaSegurodeVida = new SegurodeVida(cliente1, 1, 1000);
        
        contaCorrente.depositar(1000);
        contaPoupanca.depositar(1000);

        Tributavel tributContaCorrente = contaCorrente;
        Tributavel tributContaSegurodeVida = contaSegurodeVida;

        tributContaCorrente.getValorImposto();
        tributContaCorrente.getTipo();

        tributContaSegurodeVida.getValorImposto();
        tributContaSegurodeVida.getTipo();

        cliente1.ativos(contaCorrente, contaPoupanca, contaSegurodeVida);

        System.out.println("Saldo futuro para conta corrente: " + contaCorrente.calcularSaldoFuturo(5));
        System.out.println("Saldo futuro para conta poupanca: " + contaPoupanca.calcularSaldoFuturo(5));


    }
}
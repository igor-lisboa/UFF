import java.lang.ArrayList;

public class Agenda {
    private ArrayList<Pessoa> pessoas;
    private final int total = 10;

    public Agenda() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    public void armazenaPessoa(String nome, int idade, float altura) {
        if(pessoas.size() < 10)
            pessoas.add(new Pessoa(nome, idade, altura));
        else
            System.out.println("Agenda cheia!");
    }

    public void removePessoa(String nome) {
        for (Pessoa pessoa : pessoas)
            if(pessoa.nome == nome)
                pessoas.remove(pessoa);
            else
                System.out.println("Nome nao encontrado!");
    }

    public int buscaPessoa(String nome) {
        for (Pessoa pessoa : pessoas)
            if (pessoa.nome == nome)
                return indexOf(pessoa);
            else
                return 0;
    }

    public void imprimeAgenda() {
        for (Pessoa pessoa : pessoas)
            System.out.println(pessoa.getNome +" | "+ pessoa.getIdade +" | "+ pessoa.getAltura);
    }

    public void imprimePessoa(int index) {
        Pessoa pessoa = pessoas.get(i);
        System.out.println(pessoa.getNome +" | "+ pessoa.getIdade +" | "+ pessoa.getAltura);
    }

}
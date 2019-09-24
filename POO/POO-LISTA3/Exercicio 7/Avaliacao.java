public class Avaliacao {
    private String nomeAluno;
    private String disciplina;
    private ArrayList<Double> notas;

    public Avaliacao(String nomeAluno, String disciplina) {
        this.nomeAluno = nomeAluno;
        this.disciplina = disciplina;
        this.notas = new ArrayList<Double>();
    }

    public void registrarNota(double nota) {
        if(nota < 0) 
            throw new ArithmeticException("Nota nao pode ser negativa!");
        else if(nota > 10)
            throw new ArithmeticException("Nota nao pode ser maior que dez!");
        else
            this.notas.add(nota);
    }

    public void calcularMedia() {
        double soma = 0;
        if(this.notas.size() == 0)
            throw new IllegalArgumentException("Divisor nao pode ser zero!");
        else
            for(int i = 0; i < this.notas.size(); i++)
                soma += this.notas.get(i);
            System.out.println(soma / this.notas.size());
    }

}
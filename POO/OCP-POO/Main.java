import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        ProcessoDaSalaDeEmergencia hospital_do_andarai = new ProcessoDaSalaDeEmergencia();
    }
}

abstract class Atividades {
    public void chamarEmpregado(Empregado empregado){
        System.out.println(empregado);
    };
}

class ProcessoDaSalaDeEmergencia {
    public ProcessoDaSalaDeEmergencia() {
        Medico medico = new Medico(1, "Cristiano da Silva", "Ortopedia", false);
        Enfermeira enfermeira = new Enfermeira(2, "Criusoleide da Costa", "Pediatria", true);
        medico.chamarEmpregado(enfermeira);
		medico.executar("fornecerDiagnostico");
		enfermeira.executar("checarSinaisVitais");
    }
}

abstract class Empregado extends Atividades {
    private int id;
    private String nome;
    private String departamento;
    private Boolean trabalhando;

    public Empregado(int id, String nome, String departamento, Boolean trabalhando) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.trabalhando = trabalhando;
    }

	abstract void executar(String metodo);

    @Override
    public String toString() {
        return "Empregado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", departamento='" + departamento + '\'' +
                ", trabalhando=" + trabalhando +
                '}';
    }
}

class Enfermeira extends Empregado {
    public Enfermeira(int id, String nome, String departamento, Boolean trabalhando) {
        super(id, nome, departamento, trabalhando);
    }

	@Override
	public void executar(String cmd) {
		Method method;

		try {
			method = this.getClass().getMethod(cmd);
			method.invoke(this);
		}
		catch (SecurityException e) {System.out.println(e);}
		catch (NoSuchMethodException e) {System.out.println(e);}
		catch (IllegalArgumentException e) {System.out.println(e);}
		catch (IllegalAccessException e) {System.out.println(e);}
		catch (InvocationTargetException e) {System.out.println(e);}

	}

    @Override
    public String toString() {
        return "Enfermeira{} " + super.toString();
    }

	public void checarSinaisVitais(){
		System.out.println("Checando sinais vitais");
	};

    public void coletarSangue(){
		System.out.println("Coletando Sangue");
	};

    public void higienizarPaciente(){
		System.out.println("Higienizando Paciente");
	};
}

class Medico extends Empregado {
    public Medico(int id, String nome, String departamento, Boolean trabalhando) {
        super(id, nome, departamento, trabalhando);
    }

	@Override
	public void executar(String cmd) {
		Method method;

		try {
			method = this.getClass().getMethod(cmd);
			method.invoke(this);
		}
		catch (SecurityException e) {System.out.println(e);}
		catch (NoSuchMethodException e) {System.out.println(e);}
		catch (IllegalArgumentException e) {System.out.println(e);}
		catch (IllegalAccessException e) {System.out.println(e);}
		catch (InvocationTargetException e) {System.out.println(e);}

	}

    @Override
    public String toString() {
        return "Medico{} " + super.toString();
    }

	private void prescreverMedicamento(){
		System.out.println("Preescrevendo Medicamento");
	};

    public void fornecerDiagnostico(){
		System.out.println("Fornecendo Diagnóstico");
	};
}
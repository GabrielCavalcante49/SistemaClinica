package ClassesGerais;

public class Paciente {
    // Esta classe instanciará cada paciente que for registrado nas filas e históricos do sistema.

    private int id;
    private String nome;
    private TipoConsulta tipoConsulta;
    private int idade;

    public Paciente(int id, String nome, TipoConsulta tipoConsulta, int idade) {
        this.id = id;
        this.nome = nome;
        this.tipoConsulta = tipoConsulta;
        this.idade = idade;
    }

    // Este método será de extrema importancia para as outras classes que armazenarão instancias de pacientes.
    public boolean isPrioritario(){
        return idade >= 60;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public int getIdade() {
        return idade;
    }

    public String toString() {
        return "Paciente: " + nome + " (ID: " + id + ")";
    }
}
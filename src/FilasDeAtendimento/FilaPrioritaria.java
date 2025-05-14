package FilasDeAtendimento;

public class FilaPrioritaria<Paciente> extends FilaGeral<Paciente> {

    /*Esta classe foi uma forma encontrada pare resolver o tópico 2 de "Requisitos Técnicos". Quando o método
    isPrioritario() for true, as instancias de pacientes prioritarios entrarão aqui. Herda de FilaGeral, por ser
    idêntico em estrutura.*/
    public FilaPrioritaria(int capacity) {
        super(capacity);
    }
}

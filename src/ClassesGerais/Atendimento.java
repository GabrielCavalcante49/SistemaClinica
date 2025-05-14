package ClassesGerais;

import java.time.LocalDateTime;

public class Atendimento {
    /*
    Essa classe possui objetivos muito específicos, fazendo ela ser necessária para o sistema. Primeiro, ela será de suma importancia para
    registrar a data em que o paciente foi registrado no histórico do sistema(quando saiu da fial), além de posssibilitar multiplos atendimento
    do mesmo. Segundo, será util para um método chamado rollback que será usado em outra classe. Por último, essa classe tem como objetivo
    diminuir o acoplamento.
    */

    private Paciente paciente;
    private TipoConsulta tipoConsulta;
    private LocalDateTime data;

    public Atendimento(Paciente paciente, TipoConsulta tipoConsulta) {
        this.paciente = paciente;
        this.tipoConsulta = tipoConsulta;
        this.data = LocalDateTime.now();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String toString() {
        return "Paciente: " + paciente.getNome() +
                " | Tipo de Consulta: " + tipoConsulta +
                " | Data/Hora: " + data;
    }
}

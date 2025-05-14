package Sistema;

import ClassesGerais.Paciente;
import ClassesGerais.Atendimento;
import Historico.HistoricoAtendimentos;
import FilasDeAtendimento.FilaGeral;
import FilasDeAtendimento.FilaPrioritaria;

public class SistemaAtendimento {
    /*
    Esta é a classe resposável por fazer os atendimentos corretamente. Instanciará uma FilaPrioritaria, uma FilaGeral,
    um HistóricoAtendimentos. Faz o registro de paciente, atende os pacientes alternado entre a fila geral e de prioridade e
     cancela o ultimo a tendimento.

    */

    private FilaPrioritaria<Paciente> prioritaria;
    private FilaGeral<Paciente> geral;
    private HistoricoAtendimentos<Atendimento> historico;
    private boolean proximoPrioritario;

    public SistemaAtendimento(int capacidadePrioritaria, int capacidadeGeral, int capacidadeHistorico) {
        this.prioritaria = new FilaPrioritaria<>(capacidadePrioritaria);
        this.geral = new FilaGeral<>(capacidadeGeral);
        this.historico = new HistoricoAtendimentos(capacidadeHistorico);
        this.proximoPrioritario = true; // O sistema começará pela prioridade
    }

    public void register(Paciente paciente) {
        if (paciente.isPrioritario()) {
            if (prioritaria.isFull()) {
                throw new IllegalStateException("Fila prioritária está cheia.");
            }
            prioritaria.enQueue(paciente);
        } else {
            if (geral.isFull()) {
                throw new IllegalStateException("Fila geral está cheia.");
            }
            geral.enQueue(paciente);
        }
    }

    public void next() {
        Paciente atendido = null;

        if (proximoPrioritario) {
            if (!prioritaria.isEmpty()) {
                atendido = prioritaria.deQueue();
            } else if (!geral.isEmpty()) {
                atendido = geral.deQueue();
            }
        } else {
            if (!geral.isEmpty()) {
                atendido = geral.deQueue();
            } else if (!prioritaria.isEmpty()) {
                atendido = prioritaria.deQueue();
            }
        }

        if (atendido != null) {
            Atendimento registro = new Atendimento(atendido, atendido.getTipoConsulta());
            historico.addInLog(registro); // Registra no histórico

            System.out.println("Paciente atendido: " + " (ID: " + atendido.getId() + ") " + " - Nome: "
                    +  atendido.getNome()+ " - Idade: " + atendido.getIdade());
            proximoPrioritario = !proximoPrioritario; // Alterna entre true ou false para determinar quem será o próximo da fila
        } else {
            System.out.println("Sem pacientes para atender.");
        }
    }

    //Cancela e retorna o ultimo paciente na fila.
    public Atendimento rollback() {
        if (historico.isEmpty()) {
            System.out.print("Histórico Vazio.");
        }

        Atendimento ultimo = historico.getLast();
        int indexUltimo = historico.getSize() - 1;
        historico.remove(indexUltimo);

        Paciente paciente = ultimo.getPaciente();
        if (paciente.isPrioritario()) {
            prioritaria.enQueue(paciente);
        } else {
            geral.enQueue(paciente);
        }
        return ultimo;
    }

    public void log() {
        historico.showLog();
    }

    public void showQueues() {
        System.out.println("Fila Prioritária:");
        prioritaria.show();
        System.out.println("\nFila Geral:");
        geral.show();
    }
}
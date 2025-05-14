package Historico;

import Base.ArrayBase;

public class HistoricoAtendimentos<Atendimento> extends ArrayBase<Atendimento> {

    /*
    No sistema final, quando um paciente é atendido(sai da fila), ele é registrado aqui.
    */

    public HistoricoAtendimentos(int capacity) {
        super(capacity);
    }

    public void addInLog(Atendimento atendimento) {
        super.add(atendimento);
    }

    // Retorna ultimo Atendimento (usado no rollback())
    public Atendimento getLast(){
        if (isEmpty()){
            System.out.print("Histórico Vazio.");
        }
        return search(getSize() - 1);
    }

    public void showLog() {
        if (isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }
        System.out.println("Histórico de Atendimentos:");
        for (int i = 0; i < getSize(); i++) {
            System.out.println(search(i));
        }
    }
}

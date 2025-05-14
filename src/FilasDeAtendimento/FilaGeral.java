package FilasDeAtendimento;

import Base.ArrayBase;

public class FilaGeral<Paciente> extends ArrayBase<Paciente> {
    // Todas as instâncias de pacientes com o isPrioridade() igual a false passarão por aqui primeiro.
    // A fila implementada a seguir é uma fila linear.

    public FilaGeral(int capacity) {
        super(capacity);
    }

    public void enQueue(Paciente paciente) {
        if (isFull()) {
            System.out.print("Fila cheia. Aguarde um momento.");
        }
        super.add(paciente);
    }

    public Paciente deQueue() {
        if (isEmpty()) {
            System.out.print("Fila vazia: não há pacientes cadastrados.");
        }
        Paciente removed = search(0);
        super.remove(0);
        return removed;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Fila vazia.");
            return;
        }
        System.out.println();
        for (int i = 0; i < getSize(); i++) {
            System.out.print(search(i) + " ");
        }
        System.out.println();
    }
}

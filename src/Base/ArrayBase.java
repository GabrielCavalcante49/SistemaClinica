package Base;

public class ArrayBase <T> {
    /*
    A classe implementada a seguir corresponde a um array gênerico que será usado como base para as
    estruturas de dados posteriores, servido como a classe mãe.

    Classe mãe de: FilaGeral e FilaPrioritaria.
    */

    private T[] array;
    private int size;

    public ArrayBase(int capacity){
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public boolean isFull(){
        return size == getLenght();
    }

    public int getLenght(){
        return array.length;
    }

    public int getSize() {
        return size;
    }

    public T search(int position){
        verifyPosition(position);
        return this.array[position];
    }

    public void verifyPosition(int position){
        if(position < 0 || position >= array.length){
            System.out.print("Posição Invalida.");
        }
    }

    public void add(T valor){
        if (isFull()){
            reshape();
        } else{
            this.array[this.size] = valor;
            this.size++;
        }
    }

    public void remove(int position){
        verifyPosition(position);
        if (isEmpty()){
            System.out.println("Vazio.");
        }
        for(int i = position; i < size; i++){
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    private void reshape(){
        if (isFull()){
            T[] newArray = (T[]) new Object[this.array.length*2];
            for (int i = 0; i < array.length; i++){
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            s.append(array[i]);
            if (i < size - 1) s.append(", ");
        }
        s.append("]");
        return s.toString();
    }
}
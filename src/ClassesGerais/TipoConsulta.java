package ClassesGerais;

public enum TipoConsulta {
    CLINICO_GERAL(1),
    ORTOPEDIA(2),
    DERMATOLOGIA(3),
    PEDIATRIA(4),
    GINECOLOGIA(5),
    CARDIOLOGIA(6),
    OFTALMOLOGIA(7),
    PSIQUIATRIA(8),
    UROLOGIA(9),
    ENDOCRINOLOGIA(10);

    private final int tipoNumero;

    TipoConsulta(int tipoNumero) {
        this.tipoNumero = tipoNumero;
    }

    public int getTipoNumero() {
        return tipoNumero;
    }

    public static TipoConsulta fromNumero(int numero){
        for (TipoConsulta tipo: TipoConsulta.values()){
            if (tipo.getTipoNumero() == numero){
                return tipo;
            }
        }
        throw  new IllegalArgumentException("Número Invalido.");
    }
}
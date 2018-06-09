package projetos.goulart.goulartlikert.constants;

/**
 * Criado por Igor Goulart de Oliveira em 08/06/2018.
 */
public enum LikertEnum {

    OTIMO("Ótimo", 1),
    BOM("Bom", 2),
    REGULAR("Regular", 3),
    FRACO("Fraco", 4),
    INSATISFEITO("Insatisfeito", 5);

    private String description;
    private int code;

    private LikertEnum(String description, int code){
        this.description = description;
        this.code = code;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription(){
        return description;
    }

    public int getCode(){
        return code;
    }

}

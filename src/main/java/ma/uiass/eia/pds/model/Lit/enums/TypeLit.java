package ma.uiass.eia.pds.model.Lit.enums;

public enum TypeLit {
    MECANIQUE("Mécanique"),
    ELECTRIQUE("Électrique");
    String name;

    TypeLit(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static TypeLit fromString(String s){
        TypeLit typeLit = MECANIQUE;
        if (s.equalsIgnoreCase("electrique"))
            typeLit = ELECTRIQUE;
        return typeLit;
    }
}

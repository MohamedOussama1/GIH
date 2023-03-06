package ma.uiass.eia.pds.model.Lit;

public enum TypeLit {
    STANDARD("standard"),
    ELECTRIC("électrique");
    String name;

    TypeLit(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static TypeLit fromString(String s){
        TypeLit typeLit = STANDARD;
        if (s.equalsIgnoreCase("électrique"))
            typeLit = ELECTRIC;
        return typeLit;
    }
}

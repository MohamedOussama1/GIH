package ma.uiass.eia.pds.model.espace.chambre;

public enum TypeChambre {
    SINGLE("Single"),
    DOUBLE("Double"),
    MULTI("Multi");
    String name;

    TypeChambre(String name) {
        this.name = name;
    }

    // Convert String coming from the Front End to Enum
    public static TypeChambre fromString(String s) {
        TypeChambre typeChambre = null;
        switch (s.toLowerCase()) {
            case "single":
                typeChambre = SINGLE;
                break;
            case "double":
                typeChambre = DOUBLE;
                break;
            case "multi":
                typeChambre = MULTI;
                break;
        }
        return typeChambre;
    }
}
package ma.uiass.eia.pds.model.departement;

public enum NomDepartement {
    URGENCE,
    CARDIOLOGIE,
    NEUROLOGIE,
    ONCOLOGIE,
    GASTROENTROLOGIE,
    OPHTALMOLOGIE;
    public static NomDepartement fromString(String s) {
        NomDepartement nomDepartement = null;
        switch (s.toLowerCase()) {
            case "urgence":
                nomDepartement = URGENCE;
                break;
            case "cardiologie":
                nomDepartement = CARDIOLOGIE;
                break;
            case "neurologie":
                nomDepartement = NEUROLOGIE;
                break;
            case "oncologie":
                nomDepartement = ONCOLOGIE;
                break;
            case "gastroentrologie":
                nomDepartement = GASTROENTROLOGIE;
                break;
            case "ophtalmologie":
                nomDepartement = OPHTALMOLOGIE;
                break;
        }
        return nomDepartement;
    }
}

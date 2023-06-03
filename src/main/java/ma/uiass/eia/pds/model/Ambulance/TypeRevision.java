package ma.uiass.eia.pds.model.Ambulance;

public enum TypeRevision {
    S("Simple"),
    CD("Courte durée"),
    LD("Longue durée");
    String name;

    TypeRevision(String name) {
        this.name = name;
    }
}

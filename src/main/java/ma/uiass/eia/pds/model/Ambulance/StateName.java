package ma.uiass.eia.pds.model.Ambulance;

public enum StateName {
    F("Fonctionnel"),
    NFCD("Non Fonctionnel Courte Durée"),
    NFLD("Non Fonctionnel Longue Durée");
    String  name;

    StateName(String name) {
        this.name = name;
    }
}

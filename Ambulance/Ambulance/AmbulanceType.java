package ma.uiass.eia.pds.model.Ambulance;

public enum AmbulanceType {
    SOINS_DE_BASE("soins de base"), // Basic Life Support
    SOINS_INTENSIFS("soins intensifs"), // Advanced Life Support
    SECOURS_URGENCE("secours d'urgence "); // Specialty Care Transport

    private final String name;

    AmbulanceType(String name) {
        this.name = name;
    }

    // Convert String coming from the Front End to Enum
    public static AmbulanceType fromString(String s) {
        AmbulanceType ambulanceType = null;
        switch (s.toUpperCase()) {
            case "soins de base":
                ambulanceType = SOINS_DE_BASE;
                break;
            case "soins intensifs":
                ambulanceType = SOINS_INTENSIFS;
                break;
            case "secours d'urgence":
                ambulanceType = SECOURS_URGENCE;
                break;
        }
        return ambulanceType;
    }
}
package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;

import java.util.List;

public interface DemandeDmRepository {
    List<DemandeDm> getAllDemandes();
    List<DemandeDm> getDemandesByDepartement(String nomDepartement);

    void ajouterDemandeDM(String typeDm, String nomDm, String nomDepartement, int qte);

    void updateEtatDemande(int id, String etatDemande);
}

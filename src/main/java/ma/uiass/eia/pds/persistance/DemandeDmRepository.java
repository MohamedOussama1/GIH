package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;

import java.util.List;

public interface DemandeDmRepository {
    List<String > getAllDemandes();
    List<String> getDemandesByDepartement(String nomDepartement);
    void ajouterDetailDemandeDM(String nomDm, int qte, int idDemande);
    int ajouterDemandeDm(String nomDepartement);

    void updateEtatDemande(int id, String etatDemande);

    void updateEtatDetail(int idDetail, String etatDetail);
}

package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;

import java.util.List;

public interface DemandeDmService {

    int ajouterDemandeDm(String nomDepartement);
    void ajouterDetailDemandeDm(String nomDM, int qte, int idDemandeDm);
    void updateEtatDemande(int id, String etatDemande);
    List<String> getAllDemandes();
    List<String> getDemandesByDepartement(String nomDepartement);

    void updateEtatDetail(int idDetail, String etatDetail);
}

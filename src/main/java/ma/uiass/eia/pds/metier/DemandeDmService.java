package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;

import java.util.List;

public interface DemandeDmService {

    void ajouterDemandeDm(String typeDm, String nomDm, String nomDepartement, int qte) ;
    void updateEtatDemande(int id, String etatDemande);
    List<DemandeDm> getAllDemandes();
    List<DemandeDm> getDemandesByDepartement(String nomDepartement);
}

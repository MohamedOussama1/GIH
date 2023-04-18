package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.persistance.DemandeDmRepository;
import ma.uiass.eia.pds.persistance.DemandeDmRepositoryImpl;

import java.util.List;

public class DemandeDmServiceImpl implements DemandeDmService{
    DemandeDmRepository demandeDmRepository = new DemandeDmRepositoryImpl();
    @Override
    public void ajouterDemandeDm(String typeDm, String nomDm, String nomDepartement, int qte) {
        demandeDmRepository.ajouterDemandeDM(typeDm, nomDm, nomDepartement, qte);
    }

    @Override
    public void updateEtatDemande(int id, String etatDemande) {
        demandeDmRepository.updateEtatDemande(id, etatDemande);
    }

    @Override
    public List<DemandeDm> getAllDemandes() {
        return demandeDmRepository.getAllDemandes();
    }

    @Override
    public List<DemandeDm> getDemandesByDepartement(String nomDepartement) {
        return demandeDmRepository.getDemandesByDepartement(nomDepartement);
    }
}

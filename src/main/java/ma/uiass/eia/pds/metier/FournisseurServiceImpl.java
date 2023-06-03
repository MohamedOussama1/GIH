package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.fournisseur.Fournisseur;
import ma.uiass.eia.pds.persistance.FournisseurRepository;
import ma.uiass.eia.pds.persistance.FournisseurRepositoryImpl;

import java.util.List;

public class FournisseurServiceImpl implements FournisseurService{
    FournisseurRepository fournisseurRepository =new FournisseurRepositoryImpl();
    @Override
    public List<Fournisseur> getAll() {
        return fournisseurRepository.getAll();
    }

    @Override
    public void getFournisseurByName(String nom) {
        fournisseurRepository.getFournisseurByName(nom);
    }

    @Override
    public void saveFournisseur(String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel) {
        fournisseurRepository.saveFournisseur(nom,ville,email,codeAPE,formeJuridique,numSiren,tel);
    }

    @Override
    public int deleteFournisseur( int id) {
        return fournisseurRepository.deleteFournisseur(id);
    }

    @Override
    public void modifyFournisseur(int id,String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel) {
        fournisseurRepository.modifyFournisseur(id,nom,ville,email,codeAPE,formeJuridique,numSiren,tel);
    }
}

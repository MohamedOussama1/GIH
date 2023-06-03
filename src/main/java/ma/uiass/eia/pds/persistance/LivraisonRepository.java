package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.livraison.DetailLivraison;
import ma.uiass.eia.pds.model.livraison.LivraisonDM;
import org.json.JSONArray;

import java.util.List;

public interface LivraisonRepository {
    void saveLivraison(JSONArray detailLivraisonList, String fournisseur);
    DetailLivraison saveDetailLivraison(int qte, String dm, int idLivraison);

    List<LivraisonDM> getAllLivraison();

}

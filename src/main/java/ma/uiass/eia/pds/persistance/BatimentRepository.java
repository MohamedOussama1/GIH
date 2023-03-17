package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.batiment.Batiment;

import java.util.List;

public interface BatimentRepository {
    void saveBatiment(String nomBatiment);
    Batiment findByName(String nomBatiment);
    List<Batiment> getAllBatiment();
    void updateBatiment(String nomBatimet);
    void deleteBatiment(String nomBatiment);
}

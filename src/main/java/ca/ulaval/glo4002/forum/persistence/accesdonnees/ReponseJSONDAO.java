package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ReponseJSONDAO extends FichierJSONDAO<ReponseJSON> {

    public ReponseJSONDAO() {
        this("src/main/resources/reponses.json");
    }

    public ReponseJSONDAO(String fichierJson) {
        super(fichierJson);
    }

    @Override
    protected TypeReference<List<ReponseJSON>> typeCible() {
        return new TypeReference<>() {
        };
    }
}

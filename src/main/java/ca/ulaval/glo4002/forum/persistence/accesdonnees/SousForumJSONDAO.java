package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class SousForumJSONDAO extends FichierJSONDAO<SousForumJSON> {

    public SousForumJSONDAO() {
        super("src/main/resources/sousforums.json");
    }

    public SousForumJSONDAO(String fichierJson) {
        super(fichierJson);
    }


    @Override
    protected TypeReference<List<SousForumJSON>> typeCible() {
        return new TypeReference<>() {
        };
    }
}

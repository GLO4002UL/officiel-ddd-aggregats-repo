package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class UtilisateurJSONDAO extends FichierJSONDAO<UtilisateurJSON> {

    public UtilisateurJSONDAO(String fichierJson) {
        super(fichierJson);
    }

    @Override
    protected TypeReference<List<UtilisateurJSON>> typeCible() {
        return new TypeReference<>() {
        };
    }
}

package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class MessageJSONDAO extends FichierJSONDAO<MessageJSON> {
    public MessageJSONDAO(String fichierJson) {
        super(fichierJson);
    }

    @Override
    protected TypeReference<List<MessageJSON>> typeCible() {
        return new TypeReference<>() {
        };
    }
}

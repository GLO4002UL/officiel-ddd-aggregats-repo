package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class FichierJSONDAO<T> {
    protected final JsonMapper mapper;
    protected String fichierJson;

    public FichierJSONDAO(String fichierJson) {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(new ZonedDateTimeSerializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        this.mapper = JsonMapper.builder()
                .addModule(module)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT).build();
        this.fichierJson = fichierJson;
    }

    public List<T> lire() throws IOException {
        return this.mapper.readValue(new File(fichierJson), typeCible());
    }

    public void ecrire(T[] aEcrire) throws IOException {
        this.mapper.writeValue(new File(fichierJson), aEcrire);
    }

    protected abstract TypeReference<List<T>> typeCible();

}

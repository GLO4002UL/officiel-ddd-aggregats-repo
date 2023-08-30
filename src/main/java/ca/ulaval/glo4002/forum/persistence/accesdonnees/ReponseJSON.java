package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class ReponseJSON {
    public UUID id;

    @JsonProperty("reply-to")
    public UUID messageRepondu;

    @JsonProperty("author-email")
    public String courrielAuteur;

    @JsonProperty("publication-date")
    public ZonedDateTime datePublication;

    @JsonProperty("content")
    public String contenu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReponseJSON that = (ReponseJSON) o;
        return Objects.equals(id, that.id) && Objects.equals(messageRepondu, that.messageRepondu) && Objects.equals(courrielAuteur, that.courrielAuteur) && datePublication.isEqual(that.datePublication) && Objects.equals(contenu, that.contenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageRepondu, courrielAuteur, datePublication, contenu);
    }

    @Override
    public String toString() {
        return "ReponseJSON{" +
                "id=" + id +
                ", messageRepondu=" + messageRepondu +
                ", courrielAuteur='" + courrielAuteur + '\'' +
                ", datePublication=" + datePublication +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}

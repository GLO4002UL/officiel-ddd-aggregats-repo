package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MessageJSON {
    public UUID id;
    @JsonProperty("title")
    public String titre;
    @JsonProperty("content")
    public String contenu;
    @JsonProperty("publication-date")
    public ZonedDateTime datePublication;
    @JsonProperty("author-email")
    public String courrielAuteur;
    @JsonProperty("subforum-slug")
    public String slugSousForum;
    public List<String> upvotes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageJSON that = (MessageJSON) o;
        return Objects.equals(id, that.id) && Objects.equals(titre, that.titre) && Objects.equals(contenu, that.contenu) && (datePublication.isEqual(that.datePublication)) && Objects.equals(courrielAuteur, that.courrielAuteur) && Objects.equals(slugSousForum, that.slugSousForum) && Objects.equals(upvotes, that.upvotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, contenu, datePublication, courrielAuteur, slugSousForum, upvotes);
    }

    @Override
    public String toString() {
        return "MessageJSON{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", datePublication=" + datePublication +
                ", courrielAuteur='" + courrielAuteur + '\'' +
                ", slugSousForum='" + slugSousForum + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}


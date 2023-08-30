package ca.ulaval.glo4002.forum.domain.messages;

import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reponse {

    private final UUID id;
    private final String contenu;
    private final Courriel auteur;
    private final ZonedDateTime datePublication;

    public Reponse(UUID id, String contenu, Courriel auteur, ZonedDateTime datePublication) {
        this.id = id;
        this.contenu = contenu;
        this.auteur = auteur;
        this.datePublication = datePublication;
    }

    public boolean estIdentique(Reponse autre) {
        if (this == autre) return true;
        if (autre == null) return false;
        return Objects.equals(id, autre.id) && Objects.equals(contenu, autre.contenu) && Objects.equals(auteur, autre.auteur) && Objects.equals(datePublication, autre.datePublication);
    }

    public boolean estEcritePar(Courriel auteur) {
        return this.auteur.equals(auteur);
    }

    public int comparerDate(Reponse autre) {
        return this.datePublication.compareTo(autre.datePublication);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return estIdentique((Reponse) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contenu, auteur, datePublication);
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", auteur=" + auteur +
                ", datePublication=" + datePublication +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public Courriel getAuteur() {
        return auteur;
    }

    public ZonedDateTime getDatePublication() {
        return ZonedDateTime.from(this.datePublication);
    }
}

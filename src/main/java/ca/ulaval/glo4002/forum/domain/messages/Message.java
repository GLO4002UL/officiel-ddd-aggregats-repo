package ca.ulaval.glo4002.forum.domain.messages;

import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import ca.ulaval.glo4002.forum.domain.utilisateurs.Utilisateur;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Collections.unmodifiableList;

public class Message {
    private final UUID id;
    private final Utilisateur auteur;
    private final ZonedDateTime datePublication;
    private final String titre;
    private final String contenu;
    private final List<Courriel> upvoters;
    private final List<Reponse> reponses;

    public Message(UUID id, Utilisateur auteur, ZonedDateTime datePublication, String titre, String contenu, List<Courriel> upvoters, List<Reponse> reponses) {
        this.id = id;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.titre = titre;
        this.contenu = contenu;
        this.upvoters = new ArrayList<>(upvoters);
        this.reponses = new ArrayList<>(reponses);
    }

    public static Message nouveauMessage(Utilisateur auteur, ZonedDateTime datePublication, String titre, String contenu) {
        return new Message(null, auteur, datePublication, titre, contenu, new ArrayList<>(), new ArrayList<>());
    }

    public boolean estIdentique(Message autre) {
        return autre != null
                && Objects.equals(id, autre.id)
                && this.auteur.aLeMemeCourriel(autre.auteur)
                && datePublication.isEqual(autre.datePublication)
                && Objects.equals(titre, autre.titre)
                && Objects.equals(contenu, autre.contenu)
                && Objects.equals(upvoters, autre.upvoters)
                && Objects.equals(reponses, autre.reponses);
    }

    public int comparerDate(Message autre) {
        if (autre == null) {
            return 1;
        }
        return this.datePublication.compareTo(autre.datePublication);
    }

    public boolean possedeId() {
        return this.id != null;
    }

    public List<Reponse> listerReponses() {
        return this.reponses.stream().sorted(Reponse::comparerDate).toList();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", auteur=" + auteur +
                ", datePublication=" + datePublication +
                ", titre='" + titre + '\'' +
                ", message='" + contenu + '\'' +
                ", upvoters=" + upvoters +
                ", reponses=" + reponses +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public ZonedDateTime getDatePublication() {
        return ZonedDateTime.from(datePublication);
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public List<Courriel> getUpvoters() {
        return unmodifiableList(upvoters);
    }

    public List<Reponse> getReponses() {
        return unmodifiableList(reponses);
    }
}

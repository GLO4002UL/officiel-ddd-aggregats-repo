package ca.ulaval.glo4002.forum.donneestest;

import ca.ulaval.glo4002.forum.domain.messages.Reponse;
import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.ReponseJSON;

import java.time.ZonedDateTime;
import java.util.UUID;

import static ca.ulaval.glo4002.forum.donneestest.DatesDeTest.PREMIER_SEPTEMBRE;

public class ReponseBuilder {

    public static final UUID ID = UUID.randomUUID();
    public static final String CONTENU = "Reponse de test";
    public static final String REPONDANT = "repondant";

    private UUID id;
    private String contenu;
    private String auteur;
    private ZonedDateTime datePublication;
    private UUID messageRepondu;

    private ReponseBuilder() {
        String seed = GenerateurAleatoire.aleatoire();
        this.id = UUID.randomUUID();
        this.messageRepondu = UUID.randomUUID();
        this.contenu = CONTENU + " " + seed;
        this.auteur = UtilisateurBuilder.UneAdresseCourriel(REPONDANT + seed);
        this.datePublication = ZonedDateTime.now();
    }

    public static ReponseBuilder UneReponsePredeterminee() {
        var builder = new ReponseBuilder();
        builder.id = ID;
        builder.messageRepondu = MessageBuilder.ID;
        builder.contenu = CONTENU;
        builder.auteur = UtilisateurBuilder.UneAdresseCourriel(REPONDANT);
        builder.datePublication = PREMIER_SEPTEMBRE;
        return builder;
    }

    public static ReponseBuilder UneReponseAleatoire() {
        return new ReponseBuilder();
    }

    public ReponseBuilder publieeLe(ZonedDateTime datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    public ReponseBuilder de(String repondant) {
        this.auteur = repondant;
        return this;
    }

    public ReponseBuilder auMessage(UUID id) {
        this.messageRepondu = id;
        return this;
    }

    public Reponse creerEntite() throws Courriel.CourrielInvalide {
        return new Reponse(this.id, this.contenu, Courriel.de(this.auteur), this.datePublication);
    }

    public ReponseJSON creerJSON() {
        var json = new ReponseJSON();
        json.id = this.id;
        json.contenu = this.contenu;
        json.courrielAuteur = this.auteur;
        json.datePublication = this.datePublication;
        json.messageRepondu = this.messageRepondu;
        return json;
    }
}

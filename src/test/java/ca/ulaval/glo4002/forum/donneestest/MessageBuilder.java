package ca.ulaval.glo4002.forum.donneestest;

import ca.ulaval.glo4002.forum.domain.messages.Message;
import ca.ulaval.glo4002.forum.domain.messages.Reponse;
import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import ca.ulaval.glo4002.forum.domain.utilisateurs.Utilisateur;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.MessageJSON;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponseAleatoire;
import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponsePredeterminee;
import static ca.ulaval.glo4002.forum.donneestest.SousForumBuilder.UnSousForumAleatoire;
import static ca.ulaval.glo4002.forum.donneestest.SousForumBuilder.UnSousForumPredetermine;
import static ca.ulaval.glo4002.forum.donneestest.UtilisateurBuilder.*;

public class MessageBuilder {

    public static final UUID ID = UUID.randomUUID();
    public static final String TITRE_NOUVEAU = "Nouveau message";
    public static final String CONTENU_NOUVEAU = "Ceci est un nouveau message de test";
    private static final String TITRE = "Titre du message de test";
    private static final String CONTENU = "Contenu du message de test";
    private static final String UPVOTER = "Upvoter";
    private UUID id;
    private Utilisateur auteur;
    private ZonedDateTime datePublication;
    private String titre;
    private String contenu;
    private ArrayList<Courriel> upvoters;
    private ArrayList<Reponse> reponses;
    private String slugSousForum;

    private MessageBuilder() throws Courriel.CourrielInvalide {
        String seed = GenerateurAleatoire.aleatoire();
        this.id = UUID.randomUUID();
        this.auteur = UnUtilisateurAleatoire().creerEntite();
        this.datePublication = ZonedDateTime.now();
        this.titre = TITRE + " " + seed;
        this.contenu = CONTENU + " " + seed;
        this.reponses = new ArrayList<>();
        this.reponses.add(UneReponseAleatoire().creerEntite());
        this.upvoters = new ArrayList<>();
        this.upvoters.add(Courriel.de(UneAdresseCourriel()));
        this.slugSousForum = UnSousForumAleatoire().creerJSON().slug;
    }

    public static MessageBuilder UnMessagePredetermine() throws Courriel.CourrielInvalide {
        var builder = new MessageBuilder();
        builder.id = ID;
        builder.auteur = UnUtilisateurPredefini().creerEntite();
        builder.datePublication = DatesDeTest.PREMIER_SEPTEMBRE;
        builder.titre = TITRE;
        builder.contenu = CONTENU;
        builder.upvoters = new ArrayList<>(List.of(Courriel.de(UneAdresseCourriel(UPVOTER))));
        builder.reponses = new ArrayList<>(List.of(UneReponsePredeterminee().creerEntite()));
        builder.slugSousForum = UnSousForumPredetermine().creerJSON().slug;
        return builder;
    }

    public static MessageBuilder UnMessageAleatoire() throws Courriel.CourrielInvalide {
        return new MessageBuilder();
    }

    public static Message creerNouveauMessage() throws Courriel.CourrielInvalide {
        return Message.nouveauMessage(UnUtilisateurAleatoire().creerEntite(), ZonedDateTime.now(), TITRE_NOUVEAU, CONTENU_NOUVEAU);
    }

    public MessageBuilder sansUpvote() {
        this.upvoters = new ArrayList<>();
        return this;
    }

    public MessageBuilder sansReponse() {
        this.reponses = new ArrayList<>();
        return this;
    }

    public MessageBuilder avecTitre(String titre) {
        this.titre = titre;
        return this;
    }

    public MessageBuilder avecContenu(String contenu) {
        this.contenu = contenu;
        return this;
    }

    public MessageBuilder publieLe(ZonedDateTime datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    public MessageBuilder avecReponse(Reponse reponse) {
        this.reponses.add(reponse);
        return this;
    }

    public MessageBuilder duSousForum(String slug) {
        this.slugSousForum = slug;
        return this;
    }

    public Message creerEntite() {
        return new Message(this.id, this.auteur, this.datePublication, this.titre, this.contenu, this.upvoters, this.reponses);
    }

    public MessageJSON creerJSON() {
        var json = new MessageJSON();
        json.id = this.id;
        json.titre = this.titre;
        json.contenu = this.contenu;
        json.datePublication = this.datePublication;
        json.courrielAuteur = this.auteur.getCourriel().getAddresse();
        json.upvotes = this.upvoters.stream().map(Courriel::getAddresse).toList();
        json.slugSousForum = this.slugSousForum;
        return json;
    }
}

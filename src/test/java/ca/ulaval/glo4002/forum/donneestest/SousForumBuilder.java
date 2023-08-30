package ca.ulaval.glo4002.forum.donneestest;

import ca.ulaval.glo4002.forum.domain.messages.Slug;
import ca.ulaval.glo4002.forum.domain.messages.SousForum;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.SousForumJSON;

public class SousForumBuilder {

    public static final String SLUG = "sousforum-test";
    private static final String CATEGORIE = "Categorie de sous forum de test";
    private static final String DESCRIPTION = "Description de sous forum de test";
    private String slug;

    private String categorie;

    private String description;

    private SousForumBuilder() {
        String seed = GenerateurAleatoire.aleatoire();
        this.slug = SLUG + seed;
        this.categorie = CATEGORIE + " " + seed;
        this.description = DESCRIPTION + " " + seed;
    }

    public static SousForumBuilder UnSousForumPredetermine() {
        var builder = new SousForumBuilder();
        builder.slug = SLUG;
        builder.categorie = CATEGORIE;
        builder.description = DESCRIPTION;
        return builder;
    }

    public static SousForumBuilder UnSousForumAleatoire() {
        return new SousForumBuilder();
    }

    public SousForumBuilder avecCategorie(String categorie) {
        this.categorie = categorie;
        return this;
    }

    public SousForumBuilder decritPar(String description) {
        this.description = description;
        return this;
    }

    public SousForumBuilder avecSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public SousForum creerEntite() throws Slug.SlugInvalide {
        return new SousForum(new Slug(this.slug), this.categorie, this.description);
    }

    public SousForumJSON creerJSON() {
        var json = new SousForumJSON();
        json.categorie = this.categorie;
        json.slug = this.slug;
        json.description = this.description;
        return json;
    }
}

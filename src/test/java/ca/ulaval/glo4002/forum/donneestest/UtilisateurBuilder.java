package ca.ulaval.glo4002.forum.donneestest;

import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import ca.ulaval.glo4002.forum.domain.utilisateurs.Utilisateur;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.UtilisateurJSON;

import static ca.ulaval.glo4002.forum.donneestest.GenerateurAleatoire.aleatoire;

public class UtilisateurBuilder {

    public static final String COURRIEL = "utilisateur@test.com";
    private static final String NOM = "Utilisateur de test";
    private static final boolean NON_ADMIN = false;
    private String nom;
    private String courriel;
    private boolean estAdmin;

    private UtilisateurBuilder() {
        String seed = aleatoire();
        this.nom = "utilisateur " + seed;
        this.courriel = this.nom.replace(" ", "-") + "@test.com";
        this.estAdmin = false;
    }

    public static UtilisateurBuilder UnUtilisateurAleatoire() {
        return new UtilisateurBuilder();
    }

    public static UtilisateurBuilder UnAdminAleatoire() {
        return UnUtilisateurAleatoire().admin();
    }

    public static UtilisateurBuilder UnUtilisateurPredefini() {
        var builder = new UtilisateurBuilder();
        builder.nom = NOM;
        builder.courriel = COURRIEL;
        builder.estAdmin = NON_ADMIN;
        return builder;
    }

    public static UtilisateurBuilder UnAdminPredefini() {
        return UnUtilisateurPredefini().admin();
    }

    public static String UneAdresseCourriel() {
        return UneAdresseCourriel(aleatoire());
    }

    public static String UneAdresseCourriel(String partieLocale) {
        return partieLocale + "@test.com";
    }

    public UtilisateurBuilder admin() {
        this.estAdmin = true;
        return this;
    }

    public UtilisateurBuilder avecCourriel(String courriel) {
        this.courriel = courriel;
        return this;
    }

    public Utilisateur creerEntite() throws Courriel.CourrielInvalide {
        return new Utilisateur(this.nom, Courriel.de(this.courriel), this.estAdmin);
    }

    public UtilisateurJSON creerJSON() {
        var json = new UtilisateurJSON();
        json.nom = this.nom;
        json.courriel = this.courriel;
        json.estAdmin = this.estAdmin;
        return json;
    }
}
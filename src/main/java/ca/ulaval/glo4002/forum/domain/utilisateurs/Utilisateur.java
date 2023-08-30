package ca.ulaval.glo4002.forum.domain.utilisateurs;

import java.util.Objects;

public class Utilisateur {
    private String nom;
    private Courriel courriel;
    private boolean estAdministrateur;

    public Utilisateur(String nom, Courriel courriel, boolean estAdministrateur) {
        this.nom = nom;
        this.courriel = courriel;
        this.estAdministrateur = estAdministrateur;
    }

    public String getNom() {
        return nom;
    }

    public Courriel getCourriel() {
        return this.courriel;
    }

    public boolean estAdministrateur() {
        return estAdministrateur;
    }

    public boolean estIdentique(Utilisateur autre) {
        if (autre == null) return false;
        if (this == autre) return true;
        return estAdministrateur == autre.estAdministrateur && Objects.equals(nom, autre.nom) && Objects.equals(courriel, autre.courriel);
    }

    public boolean aLeMemeCourriel(Utilisateur autre) {
        if (autre == null) return false;
        if (this == autre) return true;
        return this.aLeCourriel(autre.courriel);
    }

    public boolean aLeCourriel(Courriel courriel) {
        return Objects.equals(this.courriel, courriel);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", courriel=" + courriel +
                ", estAdministrateur=" + estAdministrateur +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, courriel, estAdministrateur);
    }

    public static class ExisteDeja extends Exception {
        public ExisteDeja(Utilisateur aEnregistrer) {
            super(aEnregistrer.courriel.getAddresse() + " est deja utilisé");
        }
    }
}

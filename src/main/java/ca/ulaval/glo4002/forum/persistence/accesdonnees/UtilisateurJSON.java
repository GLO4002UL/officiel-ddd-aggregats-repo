package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UtilisateurJSON {

    @JsonProperty("username")
    public String nom;
    @JsonProperty("email")
    public String courriel;
    @JsonProperty("admin")
    public boolean estAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurJSON that = (UtilisateurJSON) o;
        return estAdmin == that.estAdmin && Objects.equals(nom, that.nom) && Objects.equals(courriel, that.courriel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, courriel, estAdmin);
    }
}

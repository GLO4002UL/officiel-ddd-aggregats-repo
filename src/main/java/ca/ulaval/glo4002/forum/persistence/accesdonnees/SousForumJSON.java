package ca.ulaval.glo4002.forum.persistence.accesdonnees;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SousForumJSON {
    @JsonProperty("category")
    public String categorie;
    public String description;
    public String slug;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SousForumJSON json = (SousForumJSON) o;
        return Objects.equals(categorie, json.categorie) && Objects.equals(description, json.description) && Objects.equals(slug, json.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categorie, description, slug);
    }
}

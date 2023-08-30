package ca.ulaval.glo4002.forum.domain.messages;

import java.util.Objects;

public class SousForum {
    private final Slug slug;
    private final String categorie;
    private final String description;

    public SousForum(Slug slug, String categorie, String description) {
        this.slug = slug;
        this.categorie = categorie;
        this.description = description;
    }

    public boolean aLeMemeSlug(SousForum autre) {
        return this.slug.equals(autre.slug);
    }

    public boolean estIdentique(SousForum autre) {
        return Objects.equals(slug, autre.slug) && Objects.equals(categorie, autre.categorie) && Objects.equals(description, autre.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return estIdentique((SousForum) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug, categorie, description);
    }

    public String getSlug() {
        return this.slug.getSlug();
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }
}

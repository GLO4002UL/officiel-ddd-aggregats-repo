package ca.ulaval.glo4002.forum.domain.messages;

import java.util.Objects;

public class Slug {
    public static final String SLUG_VALIDE = "[a-zA-Z0-9-]+";
    private final String slug;

    public Slug(String slug) throws SlugInvalide {
        if (slug == null || !slug.matches(SLUG_VALIDE)) {
            throw new SlugInvalide(slug);
        }
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slug slug1 = (Slug) o;
        return Objects.equals(slug, slug1.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }

    public static class SlugInvalide extends Exception {

        public SlugInvalide(String slug) {
            super("Un slug doit respecter la regex " + SLUG_VALIDE);
        }
    }
}

package ca.ulaval.glo4002.forum.domain.messages;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SlugTest {

    private static void assertThrowsSlugInvalide(String slug) {
        assertThrows(Slug.SlugInvalide.class, () -> new Slug(slug));
    }

    @Test
    public void refuseLesSlugVides() {
        assertThrowsSlugInvalide("");
        assertThrowsSlugInvalide("   ");
        assertThrowsSlugInvalide(null);
    }

    @Test
    public void accepteLesCarateresAlphanumeriquesEtTirets() throws Exception {
        var abc123 = new Slug("abc-123");
        assertThat(abc123.getSlug()).isEqualTo("abc-123");
    }

    @Test
    public void refuseLesAutresCaracteres() {
        assertThrowsSlugInvalide("!");
        assertThrowsSlugInvalide("#");
        assertThrowsSlugInvalide(" ");
        assertThrowsSlugInvalide(",");
        assertThrowsSlugInvalide("~");
        assertThrowsSlugInvalide("_");
    }

}

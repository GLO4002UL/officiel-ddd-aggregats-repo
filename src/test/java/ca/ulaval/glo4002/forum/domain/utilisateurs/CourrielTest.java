package ca.ulaval.glo4002.forum.domain.utilisateurs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CourrielTest {

    @Test
    public void deuxCourrielsSontIdentiquesSiMemeAdresse() throws Courriel.CourrielInvalide {
        var courriel = Courriel.de("utilisateur@email.com");
        var memeCourriel = Courriel.de("utilisateur@email.com");
        var autreCourriel = Courriel.de("autre@email.com");

        assertEquals(courriel, memeCourriel);
        assertNotEquals(courriel, autreCourriel);
    }

    @Test()
    public void nePeutPasCreerDeCourrielInvalide() {
        assertThrows(Courriel.CourrielInvalide.class, () -> Courriel.de("adresse-invalide"));
    }

}
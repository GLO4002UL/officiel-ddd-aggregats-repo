package ca.ulaval.glo4002.forum.domain.messages;

import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.forum.donneestest.SousForumBuilder.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SousForumTest {

    @Test
    public void deuxSousForumAvecLesMemesAttributsSontIdentiques() throws Exception {
        var sousForum = UnSousForumPredetermine().creerEntite();
        var autreCategorie = UnSousForumPredetermine().avecCategorie("autre categorie").creerEntite();
        var autreDescription = UnSousForumPredetermine().decritPar("autre description").creerEntite();
        var autreSlug = UnSousForumPredetermine().avecSlug("autre-slug").creerEntite();
        var autreSousForum = UnSousForumAleatoire().creerEntite();

        assertTrue(sousForum.estIdentique(sousForum));
        assertFalse(sousForum.estIdentique(autreCategorie));
        assertFalse(sousForum.estIdentique(autreDescription));
        assertFalse(sousForum.estIdentique(autreSlug));
        assertFalse(sousForum.estIdentique(autreSousForum));
    }

    @Test
    public void deuxSousForumAvecLeMemeSlug() throws Exception {
        var sousForum = UnSousForumPredetermine().creerEntite();

        assertTrue(sousForum.aLeMemeSlug(sousForum));
        assertTrue(sousForum.aLeMemeSlug(UnSousForumPredetermine().creerEntite()));
        assertTrue(sousForum.aLeMemeSlug(UnSousForumAleatoire().avecSlug(SLUG).creerEntite()));
        assertFalse(sousForum.aLeMemeSlug(UnSousForumAleatoire().creerEntite()));
    }


}
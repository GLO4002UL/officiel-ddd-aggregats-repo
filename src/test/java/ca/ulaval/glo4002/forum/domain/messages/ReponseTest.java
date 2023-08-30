package ca.ulaval.glo4002.forum.domain.messages;

import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import ca.ulaval.glo4002.forum.donneestest.UtilisateurBuilder;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.forum.donneestest.DatesDeTest.*;
import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponseAleatoire;
import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponsePredeterminee;
import static org.junit.jupiter.api.Assertions.*;

class ReponseTest {

    @Test
    void deuxReponsesSontIdentiquesSiMemesAttributs() throws Exception {
        var reponse = UneReponsePredeterminee().creerEntite();
        var reponseIdentique = UneReponsePredeterminee().creerEntite();
        var autreReponse = UneReponseAleatoire().creerEntite();

        assertTrue(reponse.estIdentique(reponseIdentique));
        assertFalse(reponse.estIdentique(autreReponse));
        assertFalse(reponse.estIdentique(null));
    }

    @Test
    public void uneReponsePeutIdentifierSonAuteur() throws Exception {
        var repondant = UtilisateurBuilder.UneAdresseCourriel();
        var reponse = UneReponseAleatoire().de(repondant).creerEntite();

        assertTrue(reponse.estEcritePar(Courriel.de(repondant)));
        assertFalse(reponse.estEcritePar(Courriel.de("autre-utilisateur@email.com")));
        assertFalse(reponse.estEcritePar(null));
    }

    @Test
    public void uneReponsePeutComparerSaDateDePublication() throws Exception {
        var reponse = UneReponseAleatoire().publieeLe(QUINZE_AOUT).creerEntite();
        var apres = UneReponseAleatoire().publieeLe(PREMIER_SEPTEMBRE).creerEntite();
        var avant = UneReponseAleatoire().publieeLe(VINGTDEUX_JUILLET).creerEntite();

        assertEquals(reponse.comparerDate(reponse), 0);
        assertEquals(reponse.comparerDate(apres), -1);
        assertEquals(reponse.comparerDate(avant), 1);
    }
}
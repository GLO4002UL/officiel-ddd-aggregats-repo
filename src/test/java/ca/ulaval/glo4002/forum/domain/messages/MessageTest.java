package ca.ulaval.glo4002.forum.domain.messages;

import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.forum.donneestest.DatesDeTest.*;
import static ca.ulaval.glo4002.forum.donneestest.MessageBuilder.*;
import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponseAleatoire;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {

    @Test
    public void deuxMessagesSontIdentiquesSiMemesAttributs() throws Courriel.CourrielInvalide {
        var premier = UnMessagePredetermine().creerEntite();
        var premierIdentique = UnMessagePredetermine().creerEntite();
        var premierSansUpvote = UnMessagePredetermine().sansUpvote().creerEntite();
        var premierSansReponse = UnMessagePredetermine().sansReponse().creerEntite();
        var premierAutreTitre = UnMessagePredetermine().avecTitre("Autre titre").creerEntite();
        var premierAutreContenu = UnMessagePredetermine().avecContenu("Autre contenu").creerEntite();
        var premierAutreDate = UnMessagePredetermine().publieLe(PREMIER_SEPTEMBRE.minusHours(42)).creerEntite();
        var autre = UnMessageAleatoire().creerEntite();

        assertTrue(premier.estIdentique(premier));
        assertTrue(premier.estIdentique(premierIdentique));
        assertFalse(premier.estIdentique(premierSansUpvote));
        assertFalse(premier.estIdentique(premierSansReponse));
        assertFalse(premier.estIdentique(premierAutreTitre));
        assertFalse(premier.estIdentique(premierAutreContenu));
        assertFalse(premier.estIdentique(premierAutreDate));
        assertFalse(premier.estIdentique(autre));
        assertFalse(premier.estIdentique(null));
    }

    @Test
    public void peutComparerDateDePublication() throws Exception {
        var apres = UnMessageAleatoire().publieLe(PREMIER_SEPTEMBRE).creerEntite();
        var avant = UnMessageAleatoire().publieLe(VINGTDEUX_JUILLET).creerEntite();

        assertThat(avant.comparerDate(apres)).isEqualTo(-1);
        assertThat(apres.comparerDate(avant)).isEqualTo(1);
        assertThat(apres.comparerDate(apres)).isEqualTo(0);
        assertThat(apres.comparerDate(null)).isEqualTo(1);
    }

    @Test
    public void peutVerifierLaPresenceDunId() throws Exception {
        assertThat(creerNouveauMessage().possedeId()).isFalse();
        assertThat(UnMessageAleatoire().creerEntite().possedeId()).isTrue();
    }

    @Test
    public void peutListerLesReponsesParOrdreDePublication() throws Exception {
        var reponse = UneReponseAleatoire().publieeLe(QUINZE_AOUT).creerEntite();
        var apres = UneReponseAleatoire().publieeLe(PREMIER_SEPTEMBRE).creerEntite();
        var avant = UneReponseAleatoire().publieeLe(VINGTDEUX_JUILLET).creerEntite();

        var message = UnMessageAleatoire().sansReponse()
                .avecReponse(reponse)
                .avecReponse(apres)
                .avecReponse(avant).creerEntite();

        var reponses = message.listerReponses();
        assertThat(reponses).hasSize(3);
        assertThat(reponses.get(0)).matches(m -> m.estIdentique(avant));
        assertThat(reponses.get(1)).matches(m -> m.estIdentique(reponse));
        assertThat(reponses.get(2)).matches(m -> m.estIdentique(apres));
    }

}
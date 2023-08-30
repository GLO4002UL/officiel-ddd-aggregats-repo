package ca.ulaval.glo4002.forum.domain.messages;

import ca.ulaval.glo4002.forum.domain.messages.Forum.SousForumMemeSlug;
import ca.ulaval.glo4002.forum.donneestest.MessageBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static ca.ulaval.glo4002.forum.donneestest.DatesDeTest.*;
import static ca.ulaval.glo4002.forum.donneestest.MessageBuilder.UnMessageAleatoire;
import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponseAleatoire;
import static ca.ulaval.glo4002.forum.donneestest.SousForumBuilder.UnSousForumAleatoire;
import static java.util.Collections.emptyList;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ForumTest {

    @Test
    public void peutCreerUnForumVide() throws Exception {
        assertTrue(Forum.vide().estVide());
    }

    @Test
    public void refuseDeuxSousForumAvecMemeSlug() throws Exception {
        var sousForum = UnSousForumAleatoire().creerEntite();
        var memeSlug = UnSousForumAleatoire().avecSlug(sousForum.getSlug()).creerEntite();

        assertThrows(SousForumMemeSlug.class, () -> Forum.avec(Map.of(
                sousForum, emptyList(), memeSlug, emptyList())));
    }

    @Test
    public void peutCreerUnForumEtLireSonContenu() throws Exception {
        var a = UnSousForumAleatoire().creerEntite();
        var b = UnSousForumAleatoire().creerEntite();
        var a1r = UneReponseAleatoire().creerEntite();
        var a1 = UnMessageAleatoire().avecReponse(a1r).creerEntite();
        var a2 = UnMessageAleatoire().creerEntite();

        var forum = Forum.avec(Map.of(
                a, List.of(a1, a2),
                b, emptyList()));

        assertFalse(forum.estVide());
        assertTrue(forum.contientSousForum(a));
        assertTrue(forum.contientSousForum(b));
        assertFalse(forum.contientSousForum(UnSousForumAleatoire().creerEntite()));

        assertTrue(forum.contientMessage(a, a1));
        assertTrue(forum.contientMessage(a, a2));
        assertFalse(forum.contientMessage(b, UnMessageAleatoire().creerEntite()));
        assertFalse(forum.contientMessage(a, UnMessageAleatoire().creerEntite()));
        assertFalse(forum.contientMessage(b, a1));
        assertFalse(forum.contientMessage(b, a2));
    }

    @Test
    public void peutListerLesSousForums() throws Exception {
        assertThat(Forum.vide().listerSousForums()).isEmpty();

        var a = UnSousForumAleatoire().creerEntite();
        var b = UnSousForumAleatoire().creerEntite();
        var forum = Forum.avec(Map.of(
                a, List.of(UnMessageAleatoire().creerEntite()),
                b, emptyList()));

        var sousForumObtenus = forum.listerSousForums();
        assertThat(sousForumObtenus).hasSize(2);
        assertThat(sousForumObtenus.stream().filter(sf -> sf.estIdentique(a))).hasSize(1);
        assertThat(sousForumObtenus.stream().filter(sf -> sf.estIdentique(b))).hasSize(1);
    }

    @Test
    public void peutListerLesMessagesDunSousForumInexistant() throws Exception {
        assertThat(Forum.vide().listerMessages(UnSousForumAleatoire().creerEntite())).isEmpty();
    }

    @Test
    public void peutListerLesMessagesParOrdreDePublication() throws Exception {
        var sousForum = UnSousForumAleatoire().creerEntite();
        var message = UnMessageAleatoire().publieLe(QUINZE_AOUT).creerEntite();
        var avant = UnMessageAleatoire().publieLe(VINGTDEUX_JUILLET).creerEntite();
        var apres = UnMessageAleatoire().publieLe(PREMIER_SEPTEMBRE).creerEntite();

        var forum = Forum.avec(Map.of(sousForum, List.of(message, avant, apres)));
        var messages = forum.listerMessages(sousForum);

        assertThat(messages).hasSize(3);
        assertThat(messages.get(0)).matches(m -> m.estIdentique(avant));
        assertThat(messages.get(1)).matches(m -> m.estIdentique(message));
        assertThat(messages.get(2)).matches(m -> m.estIdentique(apres));
    }

    @Test
    public void peutPublierUnMessageDansUnSousForum() throws Exception {
        var sousForum = UnSousForumAleatoire().creerEntite();
        var forum = Forum.avec(Map.of(sousForum, emptyList()));
        var nouveauMessage = MessageBuilder.creerNouveauMessage();

        forum.publierMessage(sousForum, nouveauMessage);

        var messages = forum.listerMessages(sousForum);
        assertThat(messages).hasSize(1);
        assertThat(messages.get(0)).matches(m -> m.estIdentique(nouveauMessage));
    }

    @Test
    public void refuseDePublierUnMessageAvecUnId() throws Exception {
        var sousForum = UnSousForumAleatoire().creerEntite();
        var forum = Forum.avec(ofEntries(entry(sousForum, emptyList())));

        assertThrows(Forum.NouveauMessageAvecId.class, () -> forum.publierMessage(sousForum, UnMessageAleatoire().creerEntite()));
    }

    @Test
    public void refuseDePublierUnMessageDansUnSousForumInexistant() {
        assertThrows(Forum.SousForumInexistant.class, () -> Forum.vide().publierMessage(
                UnSousForumAleatoire().creerEntite(),
                MessageBuilder.creerNouveauMessage()));

    }
}
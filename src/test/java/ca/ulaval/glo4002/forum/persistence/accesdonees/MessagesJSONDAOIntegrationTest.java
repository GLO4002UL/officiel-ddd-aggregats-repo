package ca.ulaval.glo4002.forum.persistence.accesdonees;

import ca.ulaval.glo4002.forum.domain.utilisateurs.Courriel;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.MessageJSON;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.MessageJSONDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4002.forum.donneestest.MessageBuilder.UnMessageAleatoire;
import static ca.ulaval.glo4002.forum.persistence.accesdonees.FichiersDonneesDeTest.reinitialiser;
import static org.assertj.core.api.Assertions.assertThat;

class MessagesJSONDAOIntegrationTest {

    public static final String FICHIER_JSON = "src/test/resources/messages.json";
    private MessageJSONDAO dao;

    @BeforeEach
    void setUp() {
        dao = new MessageJSONDAO(FICHIER_JSON);
        reinitialiser(FICHIER_JSON);
    }

    @AfterEach
    void tearDown() {
        reinitialiser(FICHIER_JSON);
    }

    @Test
    public void peutChargerLesMessages() throws IOException {
        var messages = dao.lire();

        assertThat(messages).hasSize(7);
        var firstMessage = messages.get(0);
        assertThat(firstMessage.id).isEqualTo(UUID.fromString("f15f9e6e-9245-4c3d-9e46-0e2a497f983d"));
        assertThat(firstMessage.courrielAuteur).isEqualTo("student1@ulaval.ca");
        assertThat(firstMessage.titre).isEqualTo("Introduction to Programming");
        assertThat(firstMessage.contenu).isEqualTo("Hello everyone, I'd like to discuss the basics of programming languages.");
        assertThat(firstMessage.slugSousForum).isEqualTo("software-engineering");
        assertThat(firstMessage.upvotes).isEqualTo(Arrays.stream(new String[]{"teacher2@ulaval.ca", "student6@ulaval.ca"}).toList());
        assertThat(firstMessage.datePublication).isEqualTo(ZonedDateTime.of(2023, 8, 17, 9, 0, 0, 0, ZoneOffset.UTC));
    }

    @Test
    public void peutEcrireDesMessages() throws IOException, Courriel.CourrielInvalide {
        MessageJSON aEcrire = UnMessageAleatoire().creerJSON();
        dao.ecrire(new MessageJSON[]{aEcrire});

        var messages = dao.lire();

        assertThat(messages).isEqualTo(List.of(aEcrire));
    }

}
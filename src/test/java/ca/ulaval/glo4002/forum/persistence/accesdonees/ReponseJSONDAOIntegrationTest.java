package ca.ulaval.glo4002.forum.persistence.accesdonees;

import ca.ulaval.glo4002.forum.persistence.accesdonnees.ReponseJSON;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.ReponseJSONDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4002.forum.donneestest.ReponseBuilder.UneReponseAleatoire;
import static ca.ulaval.glo4002.forum.persistence.accesdonees.FichiersDonneesDeTest.reinitialiser;
import static org.assertj.core.api.Assertions.assertThat;


class ReponseJSONDAOIntegrationTest {

    public static final String FICHIER_JSON = "src/test/resources/reponses.json";
    private ReponseJSONDAO dao;

    @BeforeEach
    void setUp() throws IOException {
        this.dao = new ReponseJSONDAO(FICHIER_JSON);
        reinitialiser(FICHIER_JSON);
    }

    @AfterEach
    void tearDown() throws IOException {
        reinitialiser(FICHIER_JSON);
    }

    @Test
    public void peutLireLesReponses() throws IOException {
        var reponses = dao.lire();
        assertThat(reponses).hasSize(19);

        var derniereReponse = reponses.get(reponses.size() - 1);
        assertThat(derniereReponse.id).isEqualTo(UUID.fromString("e8d5648d-6ff3-42cc-a350-6a7501e1238e"));
        assertThat(derniereReponse.messageRepondu).isEqualTo(UUID.fromString("f15f9e6e-9245-4c3d-9e46-0e2a497f983d"));
        assertThat(derniereReponse.courrielAuteur).isEqualTo("student9@ulaval.ca");
        assertThat(derniereReponse.datePublication).isEqualTo(ZonedDateTime.of(2023, 7, 30, 21, 5, 0, 0, ZoneOffset.UTC));
        assertThat(derniereReponse.contenu).isEqualTo("Learning multiple programming languages can be challenging.");
    }

    @Test
    public void peutEcrire() throws Exception {
        var aEcrire = UneReponseAleatoire().creerJSON();
        dao.ecrire(new ReponseJSON[]{aEcrire});

        var reponses = dao.lire();
        assertThat(reponses).isEqualTo(List.of(aEcrire));
    }


}
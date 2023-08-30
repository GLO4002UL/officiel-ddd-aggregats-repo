package ca.ulaval.glo4002.forum.persistence.accesdonees;

import ca.ulaval.glo4002.forum.persistence.accesdonnees.SousForumJSON;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.SousForumJSONDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static ca.ulaval.glo4002.forum.donneestest.SousForumBuilder.UnSousForumAleatoire;
import static ca.ulaval.glo4002.forum.persistence.accesdonees.FichiersDonneesDeTest.reinitialiser;
import static org.assertj.core.api.Assertions.assertThat;

class SousForumJSONDAOTestIntegration {


    public static final String FICHIER_JSON = "src/test/resources/sousforums.json";
    private SousForumJSONDAO sousForumJSONDAO;

    @BeforeEach
    void setUp() {
        sousForumJSONDAO = new SousForumJSONDAO(FICHIER_JSON);
        reinitialiser(FICHIER_JSON);
    }

    @AfterEach
    void tearDown() {
        reinitialiser(FICHIER_JSON);
    }

    @Test
    public void peutLireLesSousForums() throws IOException {
        var sousForums = sousForumJSONDAO.lire();

        assertThat(sousForums).hasSize(4);

        var meilleurSousForum = sousForums.get(2);
        assertThat(meilleurSousForum.slug).isEqualTo("software-engineering");
        assertThat(meilleurSousForum.description).isEqualTo("Engage in discussions related to software development, programming languages, and technology.");
        assertThat(meilleurSousForum.categorie).isEqualTo("Software engineering");
    }

    @Test
    public void peutEcrire() throws Exception {
        SousForumJSON aEcrire = UnSousForumAleatoire().creerJSON();
        sousForumJSONDAO.ecrire(new SousForumJSON[]{aEcrire});

        var sousForums = sousForumJSONDAO.lire();
        assertThat(sousForums).isEqualTo(List.of(aEcrire));
    }
}
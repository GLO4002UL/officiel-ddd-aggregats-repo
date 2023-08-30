package ca.ulaval.glo4002.forum.persistence.accesdonees;

import ca.ulaval.glo4002.forum.persistence.accesdonnees.UtilisateurJSON;
import ca.ulaval.glo4002.forum.persistence.accesdonnees.UtilisateurJSONDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static ca.ulaval.glo4002.forum.donneestest.UtilisateurBuilder.UnAdminAleatoire;
import static ca.ulaval.glo4002.forum.donneestest.UtilisateurBuilder.UnUtilisateurAleatoire;
import static ca.ulaval.glo4002.forum.persistence.accesdonees.FichiersDonneesDeTest.reinitialiser;
import static org.assertj.core.api.Assertions.assertThat;

class UtilisateurJSONDAOTestIntegration {

    public static final String FICHIER_JSON = "src/test/resources/utilisateurs.json";
    private UtilisateurJSONDAO dao;

    @BeforeEach
    void setUp() {
        dao = new UtilisateurJSONDAO(FICHIER_JSON);
        reinitialiser(FICHIER_JSON);
    }

    @AfterEach
    void tearDown() {
        reinitialiser(FICHIER_JSON);
    }

    @Test
    public void peutLireLesUtilisateurs() throws IOException {
        List<UtilisateurJSON> utilisateurs = dao.lire();

        assertThat(utilisateurs).hasSize(10);

        var premierUtilisateur = utilisateurs.get(0);
        assertThat(premierUtilisateur.courriel).isEqualTo("student1@ulaval.ca");
        assertThat(premierUtilisateur.estAdmin).isFalse();

        var secondUtilisateur = utilisateurs.get(1);
        assertThat(secondUtilisateur.nom).isEqualTo("teacher2");
        assertThat(secondUtilisateur.estAdmin).isTrue();
    }

    @Test
    public void peutEcrire() throws Exception {
        var utilisateurAEcrire = UnUtilisateurAleatoire().creerJSON();
        var adminAEcrire = UnAdminAleatoire().creerJSON();

        dao.ecrire(new UtilisateurJSON[]{utilisateurAEcrire, adminAEcrire});

        var utilisateurs = dao.lire();
        assertThat(utilisateurs).isEqualTo(List.of(utilisateurAEcrire, adminAEcrire));

    }

}
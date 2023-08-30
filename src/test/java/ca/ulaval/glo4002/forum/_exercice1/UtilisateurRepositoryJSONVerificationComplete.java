package ca.ulaval.glo4002.forum._exercice1;

public class UtilisateurRepositoryJSONVerificationComplete {

/*

    public static final String FICHIER_JSON = "src/test/resources/utilisateurs.json";
    private UtilisateurRepositoryJSON vraiRepository;
    private String utilisateursDeTest;

    @BeforeEach
    void setUp() throws IOException {
        var vraieDAO = new UtilisateurJSONDAO(FICHIER_JSON);
        this.vraiRepository = new UtilisateurRepositoryJSON(vraieDAO);
        reinitialiser(FICHIER_JSON);
    }

    @AfterEach
    void tearDown() throws IOException {
        reinitialiser(FICHIER_JSON);
    }
    @Test
    public void scenarioComplet() throws Exception {
        List<Utilisateur> utilisateurs = vraiRepository.charger();
        assertThat(utilisateurs).hasSize(10);

        Optional<Utilisateur> personne = vraiRepository.chercher(Courriel.de("personne@nonexistant.com"));
        assertThat(personne).isEmpty();

        String nouveauCourriel = "nouveau-prof@ulaval.ca";
        vraiRepository.ajouter(new Utilisateur("Nouveau prof", Courriel.de(nouveauCourriel), true));
        Optional<Utilisateur> nouveau = vraiRepository.chercher(Courriel.de(nouveauCourriel));
        var profAttendu = new Utilisateur("Nouveau prof", Courriel.de(nouveauCourriel), true);
        assertThat(nouveau).hasValueSatisfying(u -> u.estIdentique(profAttendu));
        assertThat(vraiRepository.charger()).hasSize(11);

        vraiRepository.effacerTout();
        assertThat(vraiRepository.charger()).isEmpty();
    }
     */
}

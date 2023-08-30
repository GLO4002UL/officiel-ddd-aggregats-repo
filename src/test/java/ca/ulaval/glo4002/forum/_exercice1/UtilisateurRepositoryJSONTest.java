package ca.ulaval.glo4002.forum._exercice1;

public class UtilisateurRepositoryJSONTest {

    /*
    private UtilisateurRepositoryJSON repository;

    private UtilisateurJSONDAO mockDAO;

    private UtilisateurRepositoryJSON vraiRepository;

    @BeforeEach
    void setUp() {
        mockDAO = mock(UtilisateurJSONDAO.class);
        this.repository = new UtilisateurRepositoryJSON(mockDAO);
    }

    @Test
    public void peutChargerTousLesUtilisateurs() throws Exception {
        var utilisateurJSON = UnUtilisateurAleatoire().creerJSON();
        var adminJSON = UnAdminAleatoire().creerJSON();
        stubberMockDao(utilisateurJSON, adminJSON);


        List<Utilisateur> utilisateurs = repository.charger();

        var utilisateurAttendu = new Utilisateur(utilisateurJSON.nom, Courriel.de(utilisateurJSON.courriel), false);
        var adminAttendu = new Utilisateur(adminJSON.nom, Courriel.de(adminJSON.courriel), true);
        assertThat(utilisateurs).satisfiesExactlyInAnyOrder(
                u -> u.estIdentique(utilisateurAttendu),
                u -> u.estIdentique(adminAttendu));
    }


    private void stubberMockDao(UtilisateurJSON... utilisateursExistants) throws IOException {
        when(mockDAO.lire()).thenReturn(Arrays.stream(utilisateursExistants).toList());
    }

    @Test
    public void peutTrouverUnUtilisateurParCourriel() throws Exception {
        UtilisateurJSON premier = UnAdminAleatoire().creerJSON();
        UtilisateurJSON second = UnUtilisateurAleatoire().creerJSON();
        UtilisateurJSON troisieme = UnUtilisateurAleatoire().creerJSON();
        stubberMockDao(premier, second, troisieme);

        Optional<Utilisateur> trouve = repository.chercher(Courriel.de(second.courriel));
        var attendu = new Utilisateur(second.nom, Courriel.de(second.courriel), second.estAdmin);
        assertThat(trouve).hasValueSatisfying(u -> u.estIdentique(attendu));
    }

    @Test
    public void retourneVideSiPasDutilisateurTrouve() throws Exception {
        Optional<Utilisateur> vide = repository.chercher(Courriel.de("utilisateur@nonexistant.com"));
        assertThat(vide).isEmpty();
    }

    @Test
    public void peutAjouterUnUtilisateur() throws Exception {
        String nouveauNom = "nouveau";
        String nouveauCourriel = "nouveau@email.com";
        boolean nouvelAdmin = false;

        repository.ajouter(new Utilisateur(nouveauNom, Courriel.de(nouveauCourriel), nouvelAdmin));

        UtilisateurJSON nouvelUtilisateurJSON = new UtilisateurJSON();
        nouvelUtilisateurJSON.nom = nouveauNom;
        nouvelUtilisateurJSON.courriel = nouveauCourriel;
        nouvelUtilisateurJSON.estAdmin = nouvelAdmin;
        verify(mockDAO).ecrire(new UtilisateurJSON[]{nouvelUtilisateurJSON});
    }

    @Test
    public void peutAjouterUnUtilisateurSansEcraserLesExistants() throws Exception {
        var utilisateurExistant = UnUtilisateurAleatoire().creerJSON();
        stubberMockDao(utilisateurExistant);

        String nouveauNom = "nouveau";
        String nouveauCourriel = "nouveau@email.com";
        boolean nouvelAdmin = false;
        repository.ajouter(new Utilisateur(nouveauNom, Courriel.de(nouveauCourriel), nouvelAdmin));

        UtilisateurJSON nouvelUtilisateurJSON = new UtilisateurJSON();
        nouvelUtilisateurJSON.nom = nouveauNom;
        nouvelUtilisateurJSON.courriel = nouveauCourriel;
        nouvelUtilisateurJSON.estAdmin = nouvelAdmin;

        verify(mockDAO).ecrire(new UtilisateurJSON[]{utilisateurExistant, nouvelUtilisateurJSON});
    }

    @Test
    public void refuseDajouterUtilisateurAvecLeMemeCourriel() throws Exception {
        var utilisateurExistant = UnUtilisateurAleatoire().creerJSON();
        stubberMockDao(utilisateurExistant);

        String nouveauNom = "nouveau";
        String nouveauCourriel = utilisateurExistant.courriel;
        boolean nouvelAdmin = false;
        assertThrows(Utilisateur.ExisteDeja.class, () -> repository.ajouter(new Utilisateur(nouveauNom, Courriel.de(nouveauCourriel), nouvelAdmin)));
    }

    @Test
    public void peutEffacerTousLesUtilisateurs() throws Exception {
        repository.effacerTout();

        verify(mockDAO).ecrire(new UtilisateurJSON[]{});
    }
*/
}

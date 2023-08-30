package ca.ulaval.glo4002.forum._exercice2;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ForumRepositoryJSONTest {
/**
 private ForumRepositoryJSON repository;
 @Mock private FichierJSONDAO<SousForumJSON> sousForumDAO;
 @Mock private FichierJSONDAO<ReponseJSON> reponseDAO;
 @Mock private FichierJSONDAO<MessageJSON> messageDAO;

 private SousForumBuilder sousForum;
 private MessageBuilder message;
 private ReponseBuilder reponse;
 private Message nouveauMessage;

 private static void verifierMessage(Message attendu, List<Message> obtenus) {
 assertThat(obtenus).hasSize(1);
 assertThat(obtenus.get(0)).matches(m -> m.estIdentique(attendu), attendu.toString());
 }

 private static void verifierReponse(Reponse attendue, List<Reponse> obtenues) {
 assertThat(obtenues).hasSize(1);
 assertThat(obtenues.get(0)).matches(r -> r.estIdentique(attendue));
 }

 @BeforeEach void setUp() throws Exception {
 this.repository = new ForumRepositoryJSON(messageDAO, sousForumDAO, reponseDAO);
 this.sousForum = UnSousForumPredetermine();
 this.message = UnMessagePredetermine();
 this.reponse = UneReponsePredeterminee();
 this.nouveauMessage = creerNouveauMessage();
 }

 @Test public void peutChargerSousForum() throws Exception {
 when(sousForumDAO.lire()).thenReturn(List.of(sousForum.creerJSON()));
 when(messageDAO.lire()).thenReturn(List.of(message.creerJSON()));
 when(reponseDAO.lire()).thenReturn(List.of(reponse.creerJSON()));

 Forum forum = repository.chargerForumEntier();

 assertTrue(forum.contientSousForum(sousForum.creerEntite()));
 List<Message> messages = forum.listerMessages(sousForum.creerEntite());
 verifierMessage(message.creerEntite(), messages);
 verifierReponse(reponse.creerEntite(), messages.get(0).listerReponses());
 }

 @Test public void peutEnregistrerForum() throws Exception {
 var aEnregistrer = Forum.avec(ofEntries(
 entry(sousForum.creerEntite(), List.of(message.creerEntite(), nouveauMessage))));

 repository.enregistrerForum(aEnregistrer);

 verify(sousForumDAO).ecrire(new SousForumJSON[]{sousForum.creerJSON()});
 verify(reponseDAO).ecrire(new ReponseJSON[]{reponse.creerJSON()});
 verifierMessagesEcrits();
 }

 private void verifierMessagesEcrits() throws Exception {
 ArgumentCaptor<MessageJSON[]> captureJSON = ArgumentCaptor.forClass(MessageJSON[].class);
 verify(messageDAO).ecrire(captureJSON.capture());

 var messageJSONEcrits = captureJSON.getValue();
 assertThat(messageJSONEcrits).hasSize(2);
 assertThat(messageJSONEcrits[0]).isEqualTo(UnMessagePredetermine().creerJSON());
 assertThat(messageJSONEcrits[1]).matches(identiqueMaisAvecId(nouveauMessage), nouveauMessage.toString());
 }

 private Predicate<MessageJSON> identiqueMaisAvecId(Message attendu) {
 return m -> m.id != null
 && Objects.equals(m.titre, attendu.getTitre())
 && Objects.equals(m.datePublication, attendu.getDatePublication())
 && Objects.equals(m.slugSousForum, sousForum.creerJSON().slug)
 && Objects.equals(m.courrielAuteur, attendu.getAuteur().getCourriel().getAddresse())
 && Objects.equals(m.contenu, attendu.getContenu())
 && Objects.equals(m.datePublication, attendu.getDatePublication())
 && Objects.equals(m.upvotes, attendu.getUpvoters().stream().map(Courriel::toString).toList());
 }
 */
}
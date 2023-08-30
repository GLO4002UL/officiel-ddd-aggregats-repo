package ca.ulaval.glo4002.forum.domain.messages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class Forum {
    private final Map<SousForum, ArrayList<Message>> messagesParSousForum;

    private Forum() {
        this.messagesParSousForum = new HashMap<>();
    }

    public static Forum avec(Map<SousForum, List<Message>> contenu) throws SousForumMemeSlug {
        var forum = new Forum();

        var sousForums = contenu.keySet();
        for (SousForum sousForumCourant : sousForums) {
            var avecMemeSlug = sousForums.stream().filter(autre -> autre.aLeMemeSlug(sousForumCourant)).count();
            if (avecMemeSlug > 1) throw new SousForumMemeSlug(sousForumCourant);

            forum.messagesParSousForum.put(
                    sousForumCourant,
                    new ArrayList<>(List.copyOf(contenu.get(sousForumCourant))));
        }
        return forum;
    }

    public static Forum vide() {
        return new Forum();
    }

    public boolean estVide() {
        return messagesParSousForum.isEmpty();
    }

    public boolean contientSousForum(SousForum recherche) {
        return messagesParSousForum.containsKey(recherche);
    }

    public boolean contientMessage(SousForum sousForum, Message message) {
        return contientSousForum(sousForum) && messagesParSousForum.get(sousForum).contains(message);
    }

    public List<Message> listerMessages(SousForum sousForum) {
        if (!messagesParSousForum.containsKey(sousForum)) return emptyList();
        return messagesParSousForum.get(sousForum).stream()
                .sorted(Message::comparerDate).toList();
    }

    public void publierMessage(SousForum sousForum, Message nouveauMessage) throws NouveauMessageAvecId, SousForumInexistant {
        if (nouveauMessage.possedeId()) throw new NouveauMessageAvecId(nouveauMessage);
        if (!this.messagesParSousForum.containsKey(sousForum)) throw new SousForumInexistant(sousForum);
        this.messagesParSousForum.get(sousForum).add(nouveauMessage);
    }

    public List<SousForum> listerSousForums() {
        return this.messagesParSousForum.keySet().stream().toList();
    }

    public static class NouveauMessageAvecId extends Exception {
        public NouveauMessageAvecId(Message m) {
            super("Impossible de publier un nouveau message avec un id" + m);
        }
    }

    public static class SousForumInexistant extends Exception {
        public SousForumInexistant(SousForum sf) {
            super("Sous forum inexistant " + sf);
        }
    }

    public static class SousForumMemeSlug extends Exception {
        public SousForumMemeSlug(SousForum sf) {
            super("Sous forum avec le meme slug existe deja " + sf);
        }
    }
}

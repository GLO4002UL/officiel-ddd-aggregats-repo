package ca.ulaval.glo4002.forum.domain.utilisateurs;

import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.forum.donneestest.UtilisateurBuilder.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilisateurTest {

    @Test
    void deuxUtilisateursAvecLesMemesAttributsSontIdentiques() throws Exception {
        var utilisateur = UnUtilisateurPredefini().creerEntite();

        assertTrue(utilisateur.estIdentique(utilisateur));
        assertTrue(utilisateur.estIdentique(UnUtilisateurPredefini().creerEntite()));
        assertFalse(utilisateur.estIdentique(UnAdminPredefini().creerEntite()));
        assertFalse(utilisateur.estIdentique(UnUtilisateurAleatoire().creerEntite()));
        assertFalse(utilisateur.estIdentique(null));
    }

    @Test
    void unUtilisateurPeutComparerSonCourrielAvecUnAutreUtilisateur() throws Exception {
        var utilisateur = UnUtilisateurPredefini().creerEntite();

        assertTrue(utilisateur.aLeMemeCourriel(utilisateur));
        assertTrue(utilisateur.aLeMemeCourriel(UnUtilisateurPredefini().creerEntite()));
        assertTrue(utilisateur.aLeMemeCourriel(UnUtilisateurAleatoire().avecCourriel(COURRIEL).creerEntite()));
        assertTrue(utilisateur.aLeMemeCourriel(UnAdminPredefini().creerEntite()));
        assertFalse(utilisateur.aLeMemeCourriel(UnUtilisateurAleatoire().creerEntite()));
        assertFalse(utilisateur.aLeMemeCourriel(null));
    }

    @Test
    void unUtilisateurPeutComparerSonCourrielAvecUnAutreCourriel() throws Exception {
        var utilisateur = UnUtilisateurPredefini().creerEntite();

        assertTrue(utilisateur.aLeCourriel(utilisateur.getCourriel()));
        assertFalse(utilisateur.aLeCourriel(UnUtilisateurAleatoire().creerEntite().getCourriel()));
        assertFalse(utilisateur.aLeCourriel(null));
    }

}
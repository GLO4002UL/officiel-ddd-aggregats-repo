package ca.ulaval.glo4002.forum.persistence.accesdonees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FichiersDonneesDeTest {
    public static void reinitialiser(String fichier) {
        try {
            Files.copy(Path.of(fichier + ".backup"), Path.of(fichier), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de reinitialiser " + fichier, e);
        }
    }
}
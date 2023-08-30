package ca.ulaval.glo4002.forum.domain.utilisateurs;

import java.util.Objects;
import java.util.regex.Pattern;

public class Courriel {
    private final String addresse;

    private Courriel(String addresse) {
        this.addresse = addresse;
    }

    public static Courriel de(String addresse) throws CourrielInvalide {
        if (!estValide(addresse)) {
            throw new CourrielInvalide(addresse);
        }
        return new Courriel(addresse);
    }

    private static boolean estValide(String addresse) {
        return Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$", addresse);
    }

    public String getAddresse() {
        return addresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courriel courriel = (Courriel) o;
        return Objects.equals(addresse, courriel.addresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresse);
    }

    @Override
    public String toString() {
        return "Courriel{" +
                "addresse='" + addresse + '\'' +
                '}';
    }

    public static class CourrielInvalide extends Exception {
        public CourrielInvalide(String addresse) {
            super("Courriel invalide " + addresse);
        }

    }
}

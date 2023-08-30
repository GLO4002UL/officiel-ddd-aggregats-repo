package ca.ulaval.glo4002.forum.donneestest;

import java.util.Random;

public class GenerateurAleatoire {
    public static String aleatoire() {
        int leftLimit = 97; //  'a'
        int rightLimit = 122; // 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
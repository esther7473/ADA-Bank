package ci.ada_bank.util;

import ci.ada_bank.Modeles.Enumeration.CompteType;

import java.time.Year;
import java.util.Random;

public class CompteNumberGenerator {

    public static String generateCompteNumber(CompteType typeCompte) {
        String codeType = typeCompte.name().substring(0, 3).toUpperCase();
        int annee = Year.now().getValue();

        Random random = new Random();
        int numAleatoire = random.nextInt(10000);
        String numFormatte = String.format("%04d", numAleatoire);

        return codeType + annee + numFormatte;
    }


}

package ci.ada_bank.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Display {

    public static void displayWelcomeSimple() {
        System.out.println();
        System.out.println("  ══════════════════════════════════════════════════");
        System.out.println("                                                    ");
        System.out.println("     ██████   █████  ███   ██ ██   ██             ");
        System.out.println("     ██   ██ ██   ██ ████  ██ ██  ██              ");
        System.out.println("     ██████  ███████ ██ ██ ██ █████               ");
        System.out.println("     ██   ██ ██   ██ ██  ████ ██  ██              ");
        System.out.println("     ██████  ██   ██ ██   ███ ██   ██             ");
        System.out.println("                                                    ");
        System.out.println("            █████  ██████   █████                  ");
        System.out.println("           ██   ██ ██   ██ ██   ██                 ");
        System.out.println("           ███████ ██   ██ ███████                 ");
        System.out.println("           ██   ██ ██   ██ ██   ██                 ");
        System.out.println("           ██   ██ ██████  ██   ██                 ");
        System.out.println("                                                    ");
        System.out.println("         🏛️  Banking Excellence Since 2024  🏛️      ");
        System.out.println("                                                    ");
        System.out.println("  ══════════════════════════════════════════════════");
        System.out.println();
        System.out.println("              ✦ BIENVENUE CHEZ BANK ADA ✦");
        System.out.println("                 Votre partenaire financier");
        System.out.println();
    }


    // --- Affichage simple d'un tableau avec tous les champs ---
    public static <T> void displayTable(List<T> objects) {
        displayTable(objects, null, null);
    }

    // --- Affichage avec champs spécifiques (même noms pour en-têtes) ---
    public static <T> void displayTable(List<T> objects, String[] fields) {
        displayTable(objects, fields, fields);
    }

    // --- Affichage complet avec choix des champs et des en-têtes ---
    public static <T> void displayTable(List<T> objects, String[] headers, String[] fields) {
        if (objects == null || objects.isEmpty()) {
            System.out.println("Liste vide");
            return;
        }

        try {
            Class<?> clazz = objects.get(0).getClass();

            // Sélection des champs à afficher
            Field[] fieldsToDisplay;
            if (fields != null) {
                fieldsToDisplay = Arrays.stream(fields)
                        .map(name -> {
                            try {
                                Field f = clazz.getDeclaredField(name);
                                f.setAccessible(true);
                                return f;
                            } catch (NoSuchFieldException e) {
                                throw new RuntimeException("Champ introuvable: " + name);
                            }
                        })
                        .toArray(Field[]::new);
            } else {
                fieldsToDisplay = clazz.getDeclaredFields();
                for (Field f : fieldsToDisplay) f.setAccessible(true);
            }

            // Définition des en-têtes
            String[] columnHeaders = (headers != null) ? headers :
                    Arrays.stream(fieldsToDisplay).map(Field::getName).toArray(String[]::new);

            // Calcul des largeurs max
            int[] widths = new int[fieldsToDisplay.length];
            for (int i = 0; i < widths.length; i++) {
                widths[i] = columnHeaders[i].length();
            }

            for (T obj : objects) {
                for (int i = 0; i < fieldsToDisplay.length; i++) {
                    String value = safeValue(getFieldValue(obj, fieldsToDisplay[i]));
                    widths[i] = Math.max(widths[i], value.length());
                }
            }

            // Impression du tableau
            printSeparator(widths);
            printRow(columnHeaders, widths);
            printSeparator(widths);

            for (T obj : objects) {
                String[] values = new String[fieldsToDisplay.length];
                for (int i = 0; i < fieldsToDisplay.length; i++) {
                    values[i] = safeValue(getFieldValue(obj, fieldsToDisplay[i]));
                }
                printRow(values, widths);
            }

            printSeparator(widths);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --- Récupère la valeur d'un champ ---
    private static String getFieldValue(Object obj, Field field) {
        try {
            Object value = field.get(obj);
            return (value != null) ? value.toString() : "";
        } catch (IllegalAccessException e) {
            return "";
        }
    }

    // --- Remplace null par chaîne vide ---
    private static String safeValue(String value) {
        return (value == null) ? "" : value;
    }

    // --- Affiche une ligne du tableau ---
    private static void printRow(String[] values, int[] widths) {
        System.out.print("|");
        for (int i = 0; i < values.length; i++) {
            System.out.printf(" %-" + widths[i] + "s |", values[i]);
        }
        System.out.println();
    }

    // --- Affiche la ligne de séparation ---
    private static void printSeparator(int[] widths) {
        System.out.print("+");
        for (int w : widths) {
            System.out.print("-".repeat(w + 2) + "+");
        }
        System.out.println();
    }
}

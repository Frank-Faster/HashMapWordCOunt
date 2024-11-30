import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NextLineNeat {
    static SortedMap<Integer, ArrayList<String>> sortedWordArrList = new TreeMap<>(Comparator.reverseOrder());
    static Map<String, Integer> wordMap = new TreeMap<>();
    static ArrayList<String> wordArrList = new ArrayList<>();
    static HashSet<String> wordHashSet = new HashSet<>();

    public static String eingabe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Pfad ein: ");
        return scan.nextLine();
    }

    public static File datei(String filePath) {
        return new File("C:/Users/Djibr/OneDrive - BFW Berlin Brandenburg e" +
                ".V/Dokumente/Fink2401Speicherdaten/IdeaProjects/11_November/HashMapsWordCount/src/bible2.txt");
    }

    public static void splitten(File datei) {
        try (Scanner scanner = new Scanner(datei)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("[^a-zA-Z]+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                        wordHashSet.add(word);
                        wordArrList.add(word);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datei konnte nicht gefunden werden! " + e.getMessage());
        }
    }

    public static void einlesenAusgebenWordMap() {
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            int count = entry.getValue();
            sortedWordArrList.putIfAbsent(count, new ArrayList<>());
            sortedWordArrList.get(count).add(entry.getKey());
        }
        for (Map.Entry<Integer, ArrayList<String>> entry : sortedWordArrList.entrySet()) {
            System.out.println("Häufigkeit: " + entry.getKey() + " Wort: " + entry.getValue());
        }
    }

    public static void einlesenAusgebenWordHashSet() {
        int countSet = 0;
        for (String einzelnesWort : wordHashSet) {
            if (wordHashSet.contains(einzelnesWort)) {
                countSet++;
            }
        }
        System.out.println("Es gibt " + countSet + " verschiedene Wörter. ");
    }

    public static void ausgabeWoerterInsgesamt() {
        System.out.println("Wörter insgesamt: " + wordArrList.size());
    }

    //##################################################################################################################
    public static void main(String[] args) {

        String filePath = eingabe();
        File datei = datei(filePath);
        splitten(datei);
        einlesenAusgebenWordMap();
        einlesenAusgebenWordHashSet();
        ausgabeWoerterInsgesamt();
    }
}

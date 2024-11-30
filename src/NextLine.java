import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.*;

public class NextLine {
    public static void main(String[] args) {
        // Datei, die eingelesen werden soll
        File datei = new File("C:/Users/Djibr/OneDrive - BFW Berlin Brandenburg e" +
                ".V/Dokumente/Fink2401Speicherdaten/IdeaProjects/11_November/HashMapsWordCount/src/bible2.txt");  //
        // Pfad zur Textdatei (hier: text.txt)

        Map<String, Integer> wortMap = new TreeMap<>();
        ArrayList<String> wortArrList = new ArrayList<>();
        HashSet<String> wordHashSet = new HashSet<>();
        // Scanner für die Datei
        try (Scanner scanner = new Scanner(datei)) {
            // Zeilenweise die Datei einlesen
            String[] words = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Aufteilen der Zeile in Wörter
                words = line.split("[^a-zA-Z]+");


                for (String word : words) {
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();  // Um sicherzustellen, dass Wörter in unterschiedlicher Groß-/Kleinschreibung zusammen gezählt werden

//                        Hier lese ich ein wie oft es welche Wörter gibt und falls es dieses Wort noch nicht gibt,
//                        erstelle ich für das neue Wort eine leere ArrayList die ich für jeden Treffer inkrementiere.
                        wortMap.put(word, wortMap.getOrDefault(word, 0) + 1);

//                        Hier lese ich alle einzelnen Wörter in das HashSet ein.
                        wordHashSet.add(word);
                    }
                }
            }
//            System.out.println(wortMap);
            SortedMap<Integer, ArrayList<String>> sortedWortArrList = new TreeMap<>(Comparator.reverseOrder());

            int countSet = 0;
            for (Map.Entry<String, Integer> entry : wortMap.entrySet()) {
//                hier zähle ich die Häufigkeit
                int count = entry.getValue();
                sortedWortArrList.putIfAbsent(count, new ArrayList<>());
                sortedWortArrList.get(count).add(entry.getKey());
            }

            for (String einzelnesWort : wordHashSet) {
                if (wordHashSet.contains(einzelnesWort)) {
                    countSet++;
                }
            }

//            Ausgabe wie viele verschiedene Worte es gibt
            System.out.println("Es gibt " + countSet + " verschiedene Wörter. ");

//            Ausgabe für die Anzahl von jedem einzelnen Wort
//            for (Map.Entry<Integer, ArrayList<String>> entry : sortedWortArrList.entrySet()) {
//                System.out.println("Häufigkeit: " + entry.getKey() + " Wort: " + entry.getValue());
//            }


        } catch (FileNotFoundException e) {
            System.out.println("Die Datei wurde nicht gefunden: " + e.getMessage());
        }

//        for (Map.Entry<String, Integer> entry : wortMap.entrySet()){
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }


    }
}

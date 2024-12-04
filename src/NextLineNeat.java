import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NextLineNeat {
    static ArrayList<String> wordArrList = new ArrayList<>();
    static HashSet<String> wordHashSet = new HashSet<>();
    static Map<String, Integer> wordMap = new TreeMap<>();
    static SortedMap<Integer, ArrayList<String>> sortedWordArrList = new TreeMap<>(Comparator.reverseOrder());
//    static ArrayList<String> arrayList = new ArrayList<>();

    public static String eingabe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Pfad ein: ");
        return scan.nextLine();
    }

    public static File datei(String filePath) {
        Scanner scan = new Scanner(System.in);
        if (filePath.isEmpty()) {
            System.out.println("""
                    1 = Windows Maschine Schule
                    2 = Windows Maschine Zuhause
                    3 = Linux
                    """);
            int eingabe = scan.nextInt();
            switch (eingabe) {
                case 1:
                    return new File("C:/Users/Administrator/OneDrive - BFW Berlin Brandenburg e" +
                            ".V/Dokumente/Fink2401Speicherdaten/IdeaProjects/11_November/HashMapsWordCount/src/bible2.txt");
                case 2:
                    return new File("C:/Users/Djibr/OneDrive - BFW Berlin Brandenburg e" +
                            ".V/Dokumente/Fink2401Speicherdaten/IdeaProjects/11_November/HashMapsWordCount/src/bible2.txt");
                case 3:
                    return new File("/home/django/Downloads/bible2.txt");
                default:
                    return null;
            }
        } else {
            return new File(filePath);
        }
    }

    public static void splitten(File datei) {
        if (datei.exists()) {
            System.out.println("Datei gefunden: " + datei.getAbsolutePath());
        } else {
            System.out.println("Datei nicht gefunden. ");
        }
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

    public static void dateiErstellen() {
        try {
            if (!wordMap.isEmpty()) {
                FileWriter fileWriter = new FileWriter("C:/Users/Administrator/Desktop/testerstellt.txt");    // WINDOWS MASCHINE
//                FileWriter fileWriter = new FileWriter("/home/django/Schreibtisch/testerstellt.txt");   // LINUX LAPTOP
                for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                    int count = entry.getValue();
//                    arrayList.add(String.valueOf(entry.getValue()));
                    sortedWordArrList.putIfAbsent(count, new ArrayList<>());
                    sortedWordArrList.get(count).add(entry.getKey());
                }
                for (Map.Entry<Integer, ArrayList<String>> entry : sortedWordArrList.entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    fileWriter.write(key + value + " \n");
                }
                fileWriter.close();
                System.out.println("Datei erstellt. ");
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    //##################################################################################################################
    public static void main(String[] args) {

        String filePath = eingabe();
        File datei = datei(filePath);
        splitten(datei);
        einlesenAusgebenWordMap();
        einlesenAusgebenWordHashSet();
        ausgabeWoerterInsgesamt();
//        TODO mit bufferedReader alle wörter in eine datei persistent machen
        dateiErstellen();
    }
}

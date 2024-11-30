import java.util.HashMap;
import java.util.*;

public class HashEntries {
        public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apfel", 3);
        map.put("Banane", 2);
        map.put("Clementine", 2);
        map.put("Xantippe", 2);
        map.put("Zote", 2);

        System.out.println(map.entrySet());

        for (Map.Entry<String,Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " ... " + e.getValue());
        }
    }
}

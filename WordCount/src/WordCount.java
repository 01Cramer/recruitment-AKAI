import java.util.ArrayList;
import java.util.HashMap;

public class WordCount {
    public static void main (String[] args) {
        String[] sentences = {
                "Taki mamy klimat",
                "Wszędzie dobrze ale w domu najlepiej",
                "Wyskoczył jak Filip z konopii",
                "Gdzie kucharek sześć tam nie ma co jeść",
                "Nie ma to jak w domu",
                "Konduktorze łaskawy zabierz nas do Warszawy",
                "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
                "Bez pracy nie ma kołaczy",
                "Kto sieje wiatr ten zbiera burzę",
                "Być szybkim jak wiatr",
                "Kopać pod kimś dołki",
                "Gdzie raki zimują",
                "Gdzie pieprz rośnie",
                "Swoją drogą to gdzie rośnie pieprz?",
                "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
                "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
        };

        ArrayList<String> singleWordsOfAllSentences = new ArrayList<>();

        for (String singleSentence : sentences) {
            String[] wordsInSingleSentence = singleSentence.split(" ");
            for (String word : wordsInSingleSentence) {
                singleWordsOfAllSentences.add(word.toLowerCase());
            }
        }

        int length = singleWordsOfAllSentences.size();
        String[] arrayOfSingleWordsOfAllSentences = new String[length];
        singleWordsOfAllSentences.toArray(arrayOfSingleWordsOfAllSentences);

        HashMap<Integer, String> map = new HashMap<>();//number of occurrences - key, word - value

        int max_1 = 0;
        int max_2 = 0;
        int max_3 = 0;

        for (int i = 0; i < length; i++) {
            int counter = 0;
            for (int j = 0; j < length; j++) {
                if (arrayOfSingleWordsOfAllSentences[i].equals(arrayOfSingleWordsOfAllSentences[j])) {
                    counter++;
                }
            }
            map.put(counter, arrayOfSingleWordsOfAllSentences[i]);
        }
        for (Integer key : map.keySet()){
            if (key > max_1){
                max_1 = key;
            }
        }
        for (Integer key : map.keySet()){
            if (key > max_2 && key!= max_1){
                max_2 = key;
            }
        }
        for (Integer key : map.keySet()){
            if (key > max_3 && key != max_1 && key != max_2){
                max_3 = key;
            }
        }
        System.out.println("1. " + "\"" + map.get(max_1) + "\"" + " - " + max_1);
        System.out.println("2. " + "\"" + map.get(max_2) + "\"" + " - " + max_2);
        System.out.println("3. " + "\"" + map.get(max_3) + "\"" + " - " + max_3);
    }
}
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Wspp {
    public static void main(final String[] args) {
        int countDW = 0;
        int countW = 1;
        Map<String, Pair> words = new LinkedHashMap<>();
        try {
            Scanner in = new Scanner(new FileInputStream(args[0]));
            try {
                while (in.hasNextWord()) {
                    String word = in.nextWord();
                    if (!word.isEmpty()) {
                        if (words.containsKey(word)) {
                            words.get(word).inAdd(Integer.toString(countW));
                            words.put(word, words.get(word));
                        } else {
                            words.put(word, new Pair(1, Integer.toString(countW)));
                            countDW++;
                        }
                        countW++;
                    }
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.out.println("Can not found " + e.getMessage());
        }
        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            "utf-8"
                    )
            );
            try {
                Set<Map.Entry<String, Pair>> entries = words.entrySet();
                for (Map.Entry<String, Pair> entry : entries) {
                    out.write(entry.getKey() + " " + entry.getValue().toString() + '\n');
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Can not work with output file " + e.getMessage());
        }
    }
}


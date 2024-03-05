import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Set;
import java.util.Map;

public class WsppSortedPosition {
    public static void main(final String[] args) {
        int countS = 1;
        Map<String, Triple> words = new TreeMap<>();
        try {
            Scanner in = new Scanner(new FileInputStream(args[0]));
            try {
                while (in.hasNextLine()) {
                    int countW = 1;
                    while (in.lineNumber() == countS) {
                        if (in.lineNumberPP() || in.isEnd()) {
                            break;
                        }
                        String word = in.nextWord();
                        if (word.isEmpty()) {
                            break;
                        }
                        if (words.containsKey(word)) {
                            words.get(word).inAdd(countS, countW);
                        } else {
                            words.put(word, new Triple(1, countS, countW));
                        }
                        countW++;
                    }
                    countS++;
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
                Set<Map.Entry<String, Triple>> entries = words.entrySet();
                for (Map.Entry<String, Triple> entry : entries) {
                    Triple xx = entry.getValue();
                    out.write(entry.getKey() + " " + xx.count());
                    for (int i = 0; i < xx.sLength(); i++) {
                        out.write(" " + xx.pair(i));
                    }
                    out.write(System.lineSeparator());
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Can not work with output file " + e.getMessage());
        }
    }
}


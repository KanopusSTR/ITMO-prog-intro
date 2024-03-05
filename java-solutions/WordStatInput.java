import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class WordStatInput {
    public static void main(final String[] args) {
        int count = 0;
        String[] words = new String[25];
        int[] countWords = new int[25];
        try {
            Scanner in = new Scanner(new FileInputStream(args[0]));
            try {
                while (in.hasNextWord()) {
                    boolean end = false;
                    if (count == words.length) {
                        words = Arrays.copyOf(words, 2 * words.length);
                        countWords = Arrays.copyOf(countWords, 2 * countWords.length);
                    }
                    while (true) {
                        String word = in.nextWord();
                        if (!word.isEmpty()) {
                            boolean flag = false;
                            for (int i = 0; i < count; i++) {
                                String c = word.toString();
                                if (c.equals(words[i])) {
                                    countWords[i]++;
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                words[count] = word.toString();
                                countWords[count] = 1;
                                count++;
                            }
                            break;
                        }
                    }
                    if (end) {
                        break;
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
                for (int i = 0; i < count; i++){
                    out.write(words[i] + ' ' + countWords[i] + "\n");
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Can not work with output file " + e.getMessage());
        }
    }
}

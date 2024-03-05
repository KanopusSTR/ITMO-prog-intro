import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class WordStatCount {
    
    static boolean isWordChar(char ch){
        return (Character.getType(ch) == Character.DASH_PUNCTUATION || Character.isLetter(ch) || ch == '\'');
    }
    
    public static void main(final String[] args) {
        int count = 0;
        String[] words = new String[100];
        int[] countWords = new int[100];
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]), 
                    "utf-8"
                )
            );
            try {
                while (true) {
                    boolean end = false;
                    if (count == words.length) {
                        words = Arrays.copyOf(words, 2 * words.length);
                        countWords = Arrays.copyOf(countWords, 2 * countWords.length);
                    }
                    StringBuilder word = new StringBuilder();
                    while (true) {
                        int letter = in.read();
                        if (letter == -1) {
                            end = true;
                            break;
                        }
                        if (isWordChar((char) letter)) {
                            word.append((char) Character.toLowerCase(letter));
                        } else {
                            String sWord = word.toString();
                            if (!sWord.isEmpty()) {
                                boolean flag = false;
                                for (int i = 0; i < count; i++) {
                                    if (sWord.equals(words[i])) {
                                        countWords[i]++;
                                        flag = true;
                                        break;
                                    }
                                }
                                if (!flag) {
                                    words[count] = sWord;
                                    countWords[count] = 1;
                                    count++;
                                }
                                break;
                            }
                        }
                    }
                    if (end) {
                        break;
                    }
                }
                for (int i = 1; i < count; i++) {
                    int j = i;
                    while (countWords[j] < countWords[j - 1]) {
                        int d = countWords[j];
                        countWords[j] = countWords[j - 1];
                        countWords[j - 1] = d;
                        String f = words[j];
                        words[j] = words[j - 1];
                        words[j - 1] = f;
                        j--;
                        if (j == 0) {
                            break;
                        }                                     
                    }
                }
            } finally {
                in.close();
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
                        out.write(words[i] + ' ' + countWords[i] + System.lineSeparator());
                    }
                } finally {
                    out.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Output file not found" + e.getMessage());
            } catch (IOException e) {
                System.out.println("output file error (can not write) " + e.getMessage());
            } 
        } catch (FileNotFoundException e) {
            System.out.println("input file not found " + e.getMessage());
        } catch (IOException e) {
            System.out.println("input file error (can not read) " + e.getMessage());
        } 
    }
}

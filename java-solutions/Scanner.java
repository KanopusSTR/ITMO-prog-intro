import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.InputStream;
import java.io.StringReader;
import java.io.FileInputStream;

public class Scanner {
    Reader in;
    int i = 0;
    char[] buffer = new char[1024];
    int read;
    int lineNum = 1;
    
    public Scanner(InputStream input) throws IOException{
        in = new InputStreamReader(
                input, 
                "utf-8"
        );
        buffer = bufferCr();
    }
     
    public Scanner(String input) throws IOException{
        in = new StringReader(input);
        buffer = bufferCr();
    }
    
    private static boolean isCharNumHex(char ch){
        return (ch == 'A' || ch == 'B' || ch == 'C' || ch == 'D' || ch == 'E' || ch == 'F' || Character.isDigit(ch));
    }
    
    private static boolean isNotCharNewLine(char ch){
        return (ch != 10 && ch != 11 && ch != 12 && ch != 13 && ch != 133 && ch != 8232 && ch != 8233);
    }
    
    private char[] bufferCr() throws IOException{
        char[] buffer = new char[1024];
        read = in.read(buffer);
        return buffer;
    } 

    public int nextInt() throws IOException{
        StringBuilder numberInt = new StringBuilder();
        int sign = 1;
        while (true) {
            if (read == i) {
                break;
            }
            if (buffer[i] == '-') {
                sign = -1;
                moveI();
            }
            while (Character.isDigit(buffer[i])) {
                numberInt.append((char) buffer[i]);
                moveI();
            }
            moveI();
            if (!numberInt.toString().isEmpty()) {
                break;
            } 
        }
        return Integer.parseInt(numberInt.toString()) * sign;
    }
    
    public int nextIntHex() throws IOException{
        int sign = 1;
        StringBuilder numberIntHex = new StringBuilder();
        while (true) {
            if (read == i) {
                break;
            }
            if (buffer[i] == '-') {
                sign = -1;
                moveI();
            }
            while (isCharNumHex(Character.toUpperCase(buffer[i]))) {
                numberIntHex.append((char) (buffer[i]));
                moveI();
            }
            moveI();
            if (!numberIntHex.toString().isEmpty()) {
                break;
            } 
        }
        return Integer.parseUnsignedInt(numberIntHex.toString(), 16) * sign;
    }
    
    public boolean hasNextInt() throws IOException{
        while (!Character.isDigit(buffer[i]) && read != -1 && buffer[i] != '-') {
           moveI();
        }
        if (Character.isDigit(buffer[i]) || buffer[i] == '-') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasNextIntHex() throws IOException{
        while (!isCharNumHex(Character.toUpperCase(buffer[i])) && read != -1 && buffer[i] != '-') {
            moveI();
        }
        if (isCharNumHex(Character.toUpperCase(buffer[i])) || buffer[i] == '-') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasNextLine() throws IOException{
        if (read > i) {
            return true;
        } else {
            buffer = bufferCr();
            if (read == -1) {
                return false;
            } else {
                return true;
            }
        }
    }
    
    private boolean isWordChar(char ch) {
        return (Character.getType(ch) == Character.DASH_PUNCTUATION || Character.isLetter(ch) || ch == '\'');
    }
    
    public String nextWord() throws IOException{
        StringBuilder word = new StringBuilder();
        while (read != i) {
            if (isWordChar((char) buffer[i])) {
                word.append((char) Character.toLowerCase(buffer[i]));
                moveI();
            } else {
                if (word.toString().isEmpty()) {
                     moveI();
                } else {
                    break;
                }
            }
        }
        return word.toString();
    }
    
    public boolean hasNextWord() throws IOException{
        while (true) {
            if (isWordChar((char) buffer[i])) {
                return true;
            } else {
                if (read != -1) {
                    moveI();
                } else {
                    return false;
                }
            }
        }
    }
    
    public String nextLine() throws IOException{
        StringBuilder string = new StringBuilder();
        while (isNotCharNewLine(buffer[i]) && read != i) {
            string.append((char) buffer[i]);
            moveI();
        }
        char c = buffer[i];
        moveI();
        if ((buffer[i] == 10 || buffer[i] == 13) && (buffer[i] != c)) {
            moveI();
        }
        return string.toString();
    }
    
    public void close() throws IOException{
        in.close();
    }
    
    private void moveI() throws IOException{
        if (i == 1023) {
            i = 0;
            buffer = bufferCr();
        } else {
            i++;
        }
    }
    
    public boolean lineNumberPP() throws IOException{
        boolean ans = true;
        while (isNotCharNewLine(buffer[i]) && !isWordChar(buffer[i])) {
            moveI();
        }
        if (isNotCharNewLine(buffer[i])) {
            return false;
        } else {
            ans = true;
            lineNum++;
            char c = buffer[i];
            moveI();
            if ((buffer[i] == 10 || buffer[i] == 13) && (buffer[i] != c)) {
                moveI();
            }
        }
        return ans;
    }
    
    public int lineNumber() {
        return lineNum;
    }
    public boolean isEnd() {
        if (read == -1) {
            return true;
        }else {
            return false;
        }
    }
}

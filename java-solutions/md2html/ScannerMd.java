package md2html;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ScannerMd {
    Reader in;
    int i = 0;
    String string = "";
    int read;

    public ScannerMd(InputStream input) throws IOException {
        in = new InputStreamReader(
                input,
                StandardCharsets.UTF_8
        );
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[1];
        while (true) {
            read = in.read(chars);
            if (read != -1) {
                sb.append(chars[0]);
            } else {
                break;
            }
        }
        string = sb.toString();
    }

    public ScannerMd(String input) throws IOException {
        in = new StringReader(input);
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[1];
        while (true) {
            read = in.read(chars);
            if (read != -1) {
                sb.append(chars[0]);
            } else {
                break;
            }
        }
        string = sb.toString();
    }

    private boolean isCharNewLine(char ch) {
        return (ch == 10 || ch == 11 || ch == 12 || ch == 13 || ch == 133 || ch == 8232 || ch == 8233);
    }

    public StringBuilder nextParagraph() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (hasNextLine() && isCharNewLine(string.charAt(i))) {
            i++;
        }
        while(hasNextLine()) {
            if(string.charAt(i) != System.lineSeparator().charAt(0)) {
                sb.append(string.charAt(i));
                i++;
            } else {
                i += System.lineSeparator().length();
                if (!hasNextLine()) {
                    return sb;
                }
                if (string.charAt(i) != System.lineSeparator().charAt(0)) {
                    sb.append(System.lineSeparator());
                } else {
                    i += System.lineSeparator().length();
                    return sb;
                }
            }
        }
        return sb;
    }

    public boolean hasNextLine() throws IOException {
        return string.length() > i;
    }

    public void close() throws IOException {
        in.close();
    }

    private boolean isMDChar() {
        return string.charAt(i) == '*' || string.charAt(i) == '_' || string.charAt(i) == '-'
                || string.charAt(i) == '`' || string.charAt(i) == '\'';
    }

    public Markup nextBlock() throws IOException {
        List<Markup> markups = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (isMDChar()) {
            char k = string.charAt(i);
            i++;
            markups.add(new Text(k + ""));
            if (string.charAt(i) == k) {
                i++;
                while (hasNextLine()) {
                    if (string.charAt(i) == k) {
                        i++;
                        if (!hasNextLine()) {
                            markups.add(new Text(sb.append(string.charAt(i - 1)).toString()));
                            return new MultiText(markups);
                        }
                        if (string.charAt(i) == k) {
                            i++;
                            markups.remove(0);
                            if (k == '*' || k == '_') {
                                return new Strong(markups);
                            }
                            if (k == '-') {
                                return new Strikeout(markups);
                            }
                            if (k == '\'') {
                                return new Quote(markups);
                            }
                        } else {
                            i--;
                            markups.add(nextBlock());
                        }
                    } else if (isMDChar()) {
                        markups.add(nextBlock());
                    } else {
                        sb = getText(markups, sb);
                    }
                }
            } else {
                while (hasNextLine()) {
                    if (string.charAt(i) == k && string.charAt(i) != '\'') {
                        i++;
                        if (hasNextLine() && string.charAt(i) == k) {
                            i--;
                            markups.add(nextBlock());
                        } else {
                            markups.remove(0);
                            if (k == '*' || k == '_') {
                                return new Emphasis(markups);
                            } else if (k == '`') {
                                return new Code(markups);
                            }
                        }
                    } else if (isMDChar()) {
                        markups.add(nextBlock());
                    } else {
                        sb = getText(markups, sb);
                    }
                }
                return new MultiText(markups);
            }
        } else {
            while(hasNextLine() && !isMDChar()) {
                if (string.charAt(i) == '\\') {
                    i++;
                    if (!hasNextLine()) {
                        markups.add(new Text(sb.append(string.charAt(i - 1)).toString()));
                        return new MultiText(markups);
                    }
                    sb.append(string.charAt(i));
                } else screen(sb);
                i++;
            }
            return new Text(sb.toString());
        }
        return new Text(sb.toString());
    }

    private StringBuilder getText(List<Markup> markups, StringBuilder sb) throws IOException {
        while(hasNextLine() && !isMDChar()) {
            if (string.charAt(i) == '\\') {
                i++;
                if (!hasNextLine()) {
                    return sb.append(string.charAt(i-1));
                }
                sb.append(string.charAt(i));
            } else screen(sb);
            i++;
        }
        markups.add(new Text(sb.toString()));
        sb = new StringBuilder();
        return sb;
    }

    private void screen(StringBuilder sb) {
        if (string.charAt(i) == '<') {
            sb.append("&lt;");
        } else if (string.charAt(i) == '>') {
            sb.append("&gt;");
        } else if (string.charAt(i) == '&') {
            sb.append("&amp;");
        } else {
            sb.append(string.charAt(i));
        }
    }
}
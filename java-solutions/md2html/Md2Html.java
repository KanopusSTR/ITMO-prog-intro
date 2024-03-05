package md2html;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static void main(String[] args) {
        try {
            ScannerMd in = new ScannerMd(new FileInputStream(args[0]));
            try {
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                "utf-8"
                        )
                );
                while (in.hasNextLine()) {
                    List<Markup> markups = new ArrayList<>();
                    int count = 0;
                    int white = 0;
                    StringBuilder paragraph = in.nextParagraph();
                    //System.err.println(paragraph.toString());
                    while (count < paragraph.length() && paragraph.charAt(count) == '#') {
                        count++;
                    }
                    if(count != 0 && !Character.isWhitespace(paragraph.charAt(count))) {
                        count = 0;
                    } else if (count != 0){
                        white = 1;
                    }
                    ScannerMd in2 = new ScannerMd(paragraph.substring(count + white));
                    while (in2.hasNextLine()) {
                        markups.add(in2.nextBlock());
                    }
                    StringBuilder sbb = new StringBuilder();
                    if (count != 0) {
                        new Heading(markups, count).toHtml(sbb);
                    } else {
                        new Paragraph(markups).toHtml(sbb);
                    }
                    //System.err.println(sbb + System.lineSeparator());
                    out.write(sbb + System.lineSeparator());
                }
                out.close();
                in.close();
            } catch (IOException e) {
                System.out.println("Can't open/find output file " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Can't open/find input file " + e.getMessage());
        }
    }
}

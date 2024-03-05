package md2html;

import java.util.List;

public class Paragraph extends AbstractMarkup {

    public Paragraph(List<Markup> list) {
        super(list);
        super.string1 = "<p>";
        super.string2 = "</p>";
    }
}
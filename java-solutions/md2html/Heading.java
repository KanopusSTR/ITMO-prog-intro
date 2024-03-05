package md2html;

import java.util.List;

public class Heading extends AbstractMarkup {

    public Heading(List<Markup> list, int i) {
        super(list);
        super.string1 = String.format("<h%d>", i);
        super.string2 = String.format("</h%d>", i);
    }
}

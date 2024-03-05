package md2html;

import java.util.List;

public class Strikeout extends AbstractMarkup {
    public Strikeout(List<Markup> list) {
        super(list);
        super.string1 = "<s>";
        super.string2 = "</s>";
    }
}

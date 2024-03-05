package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {
    public Emphasis(List<Markup> list) {
        super(list);
        super.string1 = "*";
        super.string2 = "<em>";
        super.string3 = "</em>";
    }
}

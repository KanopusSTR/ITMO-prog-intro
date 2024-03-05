package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup {
    public Strikeout(List<Markup> list) {
        super(list);
        super.string1 = "~";
        super.string2 = "<s>";
        super.string3 = "</s>";
    }
}

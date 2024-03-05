package md2html;

import java.util.List;

public class Quote extends AbstractMarkup{
    public Quote (List<Markup> list) {
        super(list);
        super.string1 = "<q>";
        super.string2 = "</q>";
    }
}

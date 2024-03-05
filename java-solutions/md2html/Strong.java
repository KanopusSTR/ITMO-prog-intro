package md2html;

import java.util.List;

public class Strong extends AbstractMarkup {
    public Strong(List<Markup> list) {
        super(list);
        super.string1 = "<strong>";
        super.string2 = "</strong>";
    }
}
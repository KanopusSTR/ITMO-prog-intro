package markup;

import java.util.List;

public class Strong extends AbstractMarkup {
    public Strong(List<Markup> list) {
        super(list);
        super.string1 = "__";
        super.string2 = "<strong>";
        super.string3 = "</strong>";
    }
}
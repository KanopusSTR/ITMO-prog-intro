package md2html;

import java.util.List;

public abstract class AbstractMarkup implements Markup {
    protected List<Markup> list;
    protected String string1 = "";
    protected String string2 = "";

    public AbstractMarkup(List<Markup> list) {
        this.list = list;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(string1);
        for (Markup markup : list) {
            markup.toHtml(sb);
        }
        sb.append(string2);
    }
}

package markup;

import java.util.List;

public abstract class AbstractMarkup implements Markup{
    protected List<Markup> list;
    protected String string1 = "";
    protected String string2 = "";
    protected String string3 = "";

    public AbstractMarkup(List<Markup> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(string1);
        for (Markup markup : list) {
            markup.toMarkdown(sb);
        }
        sb.append(string1);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(string2);
        for (Markup markup : list) {
            markup.toHtml(sb);
        }
        sb.append(string3);
    }
}

package markup;

public class Text implements Markup {
    String string;

    public Text (String string) {
        this.string = string;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(string);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(string);
    }
}

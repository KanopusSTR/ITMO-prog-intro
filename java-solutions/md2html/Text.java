package md2html;

public class Text implements Markup {
    String string;

    public Text (String string) {
        this.string = string;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(string);
    }
}

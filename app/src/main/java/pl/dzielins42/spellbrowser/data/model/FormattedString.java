package pl.dzielins42.spellbrowser.data.model;

public class FormattedString {

    private String plain;
    private String formatted;

    public FormattedString(String plain, String formatted) {
        super();
        this.plain = plain;
        this.formatted = formatted;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getFormattedOrPlain() {
        return formatted == null ? plain : formatted;
    }

    public static FormattedString plain(String text) {
        return new FormattedString(text, null);
    }

}

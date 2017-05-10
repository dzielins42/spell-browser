package pl.dzielins42.spellbrowser.data.model;

public class SourceLevelExtraTriplet {
    private String source;
    private int level;
    private String extra;

    public SourceLevelExtraTriplet(String source, int level, String extra) {
        super();
        this.source = source;
        this.level = level;
        this.extra = extra;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
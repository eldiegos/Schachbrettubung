/**
 * Created by e4_sevgi on 21.05.2015.
 */
public enum Figur {
    Bauer ("bauer"),
    Turm ("turm"),
    Pferd ("pferd"),
    Laufer ("laeufer"),
    Koenig ("koenig"),
    Koenigin ("dame");
    private final String bildName;

    private Figur(String bildName){
        this.bildName = bildName;
    }

    public String getBildName(boolean istSchwarz) {
        return "/images/" + (istSchwarz?"schwarz_": "weiss_") + bildName + ".png";
    }
}

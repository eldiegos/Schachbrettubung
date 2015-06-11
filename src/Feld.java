import javax.swing.*;
import java.awt.*;

/**
 * Created by e4_sevgi on 07.05.2015.
 */
public class Feld extends JLabel {

    public Feld(boolean istFeldSchwarz) {

        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        setBackground(istFeldSchwarz ? Color.BLACK : Color.WHITE);
        setOpaque(true);
    }



    public Feld(boolean istFeldSchwarz, Figur figur, boolean istFigurSchwarz) {

        this(istFeldSchwarz);
        if (figur != null){
            String bildName = figur.getBildName(istFigurSchwarz);
            setIcon(createImageIcon(bildName));
        }
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
    }

    public ImageIcon createImageIcon(String pfad) {
        java.net.URL imgURL = getClass().getResource(pfad);
        if (imgURL !=
                null) {
            return new ImageIcon(imgURL, "");
        }
        else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }
}

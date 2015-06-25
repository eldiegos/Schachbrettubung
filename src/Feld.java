import javax.swing.*;
import java.awt.*;

/**
 * Created by e4_sevgi on 07.05.2015.
 */
public class Feld extends JLabel {

    private Figur figur;
    private boolean istFigurSchwarz;
    private Color feldFarbe;
    private boolean isSelected, isMarked;

    public Feld(boolean istFeldSchwarz) {

        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        feldFarbe = istFeldSchwarz ? Color.BLACK : Color.WHITE;
        setOpaque(true);
    }


    public Feld(boolean istFeldSchwarz, Figur figur, boolean istFigurSchwarz) {

        this(istFeldSchwarz);
        this.figur = figur;
        this.istFigurSchwarz = istFigurSchwarz;
        if (this.figur != null) {
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
        } else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }

    public Figur getFigur() {
        return figur;
    }

    public boolean isIstFigurSchwarz() {
        return istFigurSchwarz;
    }

    @Override
    public Color getBackground() {
        return isSelected ? Color.RED : isMarked? Color.CYAN: feldFarbe;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        repaint();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
        repaint();
    }

    public void setSpielFigur(Figur figur, boolean istFigurSchwarz) {
        this.figur = figur;
        this.istFigurSchwarz = istFigurSchwarz;
        if (figur == null) {
            setIcon(null);
        }
        else {
            String bildName = figur.getBildName(istFigurSchwarz);
            setIcon(createImageIcon(bildName));
        }
        repaint();
    }
}

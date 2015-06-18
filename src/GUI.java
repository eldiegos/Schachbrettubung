import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by e4_sevgi on 07.05.2015.
 */
public class GUI extends JFrame {

    public GUI(String title) {
        super(title);
        setSize(800, 800);
        setLayout(new GridLayout(8, 8));


        final Feld[][] schachbrett = new Feld[8][8];


        boolean istSchwarz = true;
        for (int zeile = 0; zeile < 8; zeile++) {
            for (int spalte = 0; spalte < 8; spalte++) {
                // alle Bauern:
                if (zeile == 1 || zeile == 6){
                    schachbrett [zeile][spalte] = new Feld(istSchwarz, Figur.Bauer, zeile == 1);
                }
                // oberste oder unterste Zeile, viele verschiedene Figuren:
                else if (zeile == 0 || zeile == 7){
                    if (spalte == 1 || spalte == 6){
                        schachbrett [zeile][spalte] = new Feld(istSchwarz, Figur.Pferd, zeile == 0);
                    }
                    else if (spalte == 0 || spalte == 7){
                        schachbrett [zeile][spalte] = new Feld(istSchwarz, Figur.Turm, zeile == 0);
                    }
                    //TODO
                    else if (spalte == 2 || spalte == 5) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Laufer, zeile == 0);
                    }
                    else if (spalte == 3 ) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Koenig, zeile == 0);
                    }
                    else if (spalte == 4 ) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Koenigin, zeile == 0);
                    }


                    else{
                        schachbrett [zeile][spalte] = new Feld(istSchwarz);
                    }
                }
                // für alle anderen leeren Felder:
                else{
                    schachbrett [zeile][spalte] = new Feld(istSchwarz);
                }

                this.add(schachbrett[zeile][spalte]);
                istSchwarz = !istSchwarz;
            }
            istSchwarz = ! istSchwarz;
        }

        for (Feld[] zeile : schachbrett) {
            for (final Feld feld : zeile) {
                feld.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        Feld ausgewaehltesFeld = sucheSelektiertesFeld(schachbrett);
                        // falls das ausgewählte Feld das selbe ist wie das Feld, für das der Listener erstellt wird: ABBRUCH!

                        if (ausgewaehltesFeld == null){
                            feld.setSelected(true);

                        }
                        else{
                            Figur zuBewegendeFigur = ausgewaehltesFeld.getFigur();
                            boolean istFigurSchwarz = ausgewaehltesFeld.isIstFigurSchwarz();

                            feld.setSpielFigur(zuBewegendeFigur, istFigurSchwarz);
                            ausgewaehltesFeld.setSpielFigur(null, false);
                            ausgewaehltesFeld.setSelected(false);

                        }


                    }

                });
            }
        }
        schachbrett[0][0].setText("HALLO");
        setVisible(true);

    }

    private Feld sucheSelektiertesFeld(Feld[][] schachbrett){
        for (Feld[] felds : schachbrett) {
            for (Feld feld : felds) {
                if (feld.isSelected()){
                    return  feld;
                }
            }
        }
        return null;
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


    private Figur getFigurFuerFeld(int zeile, int spalte){
        //TODO
        return null;
    }
}

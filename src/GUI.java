import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

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

                        markiereSchachbrett(schachbrett, feld );
                        Feld ausgewaehltesFeld = sucheSelektiertesFeld(schachbrett);
                        // falls das ausgewählte Feld das selbe ist wie das Feld, für das der Listener erstellt wird: ABBRUCH!
                           if (ausgewaehltesFeld == null){
                               if (feld.getFigur() != null) {
                                   feld.setSelected(true);
                               }
                               else{
                                   return;
                               }
                           }
                           else{
                               if (ausgewaehltesFeld.equals(feld)){
                                   feld.setSelected(false);
                                   return;
                               }
                               Figur zuBewegendeFigur = ausgewaehltesFeld.getFigur();
                               boolean istFigurSchwarz = ausgewaehltesFeld.isIstFigurSchwarz();

                               if (feld.getFigur() == null || feld.isIstFigurSchwarz() != ausgewaehltesFeld.isIstFigurSchwarz()){


                               //TODO: beim feld schauen, ob eine Figur draufsteht. Falls ja, farbe der figur checken und mit der
                               // farbe der von ausgewaehltesFeld vergleichen. Nur wenn Unterschiedlch, soll Figur bewegt werden:

                               feld.setSpielFigur(zuBewegendeFigur, istFigurSchwarz);
                               ausgewaehltesFeld.setSpielFigur(null, false);
                               ausgewaehltesFeld.setSelected(false);
                               }
                           }
                    }
                });
            }
        }
    }

    private void markiereSchachbrett(final Feld[][] schachbrett, Feld feld){
        Figur figur = feld.getFigur();
        if (figur == null){
            return;
            //TODO: löschen von vorhandenen bewegungsmarkierungen?
        }

        switch (figur){

            case Bauer:
                break;
            case Turm:
                for (Feld[] felds : schachbrett) {
                    if (Arrays.asList(felds).contains(feld)){
                        for (Feld feld1 : felds) {
                            feld1.setSelected(true);
                        }
                    }
                }
            case Pferd:
                break;
            case Laufer:
                break;
            case Koenig:
                break;
            case Koenigin:
                break;
        }

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

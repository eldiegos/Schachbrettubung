import javax.swing.*;
import java.awt.*;

/**
 * Created by e4_sevgi on 07.05.2015.
 */
public class GUI extends JFrame {

    public GUI(String title) {
        super(title);
        setSize(800, 800);
        setLayout(new GridLayout(8, 8));


        JLabel[][] schachbrett = new JLabel[8][8];


        boolean istSchwarz = true;
        for (int zeile = 0; zeile < 8; zeile++) {
            for (int spalte = 0; spalte < 8; spalte++) {
                schachbrett [zeile][spalte] = zeile == 1 ? new Feld(istSchwarz, Figur.Bauer, true) : new Feld(istSchwarz);

                schachbrett [zeile][spalte] = zeile == 0 ? new Feld(istSchwarz, Figur.Pferd, true) : new Feld(istSchwarz);
                this.add(schachbrett[zeile][spalte]);

//                schachbrett [zeile][spalte] = zeile == 5 ? new Feld(istSchwarz, Figur.Pferd, true) : new Feld(istSchwarz);
//                this.add(schachbrett[zeile][spalte]);




                istSchwarz = !istSchwarz;

            }

            istSchwarz = ! istSchwarz;
         //   schachbrett[0][2].add(Figur.Pferd);

            setVisible(true);
        }
        schachbrett[0][0].setText("HALLO");

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

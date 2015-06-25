import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

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
                if (zeile == 1 || zeile == 6) {
                    schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Bauer, zeile == 1);
                }
                // oberste oder unterste Zeile, viele verschiedene Figuren:
                else if (zeile == 0 || zeile == 7) {
                    if (spalte == 1 || spalte == 6) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Pferd, zeile == 0);
                    } else if (spalte == 0 || spalte == 7) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Turm, zeile == 0);
                    } else if (spalte == 2 || spalte == 5) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Laufer, zeile == 0);
                    } else if (spalte == 3) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Koenig, zeile == 0);
                    } else if (spalte == 4) {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz, Figur.Koenigin, zeile == 0);
                    } else {
                        schachbrett[zeile][spalte] = new Feld(istSchwarz);
                    }
                }
                // für alle anderen leeren Felder:
                else {
                    schachbrett[zeile][spalte] = new Feld(istSchwarz);
                }

                this.add(schachbrett[zeile][spalte]);
                istSchwarz = !istSchwarz;
            }
            istSchwarz = !istSchwarz;
        }

        for (Feld[] zeile : schachbrett) {
            for (final Feld feld : zeile) {
                feld.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        boolean marked = feld.isMarked();
                        markiereSchachbrett(schachbrett, feld);
                        Feld ausgewaehltesFeld = sucheSelektiertesFeld(schachbrett);
                        // falls das ausgewählte Feld das selbe ist wie das Feld, für das der Listener erstellt wird: ABBRUCH!
                        if (ausgewaehltesFeld == null) {
                            if (feld.getFigur() != null) {
                                feld.setSelected(true);
                            } else {
                                return;
                            }
                        } else {
                            if (ausgewaehltesFeld.equals(feld)) {
                                feld.setSelected(false);
                                return;
                            }
                            if (ausgewaehltesFeld.getFigur() == Figur.Turm && !marked) {
                                return;
                            }
                            Figur zuBewegendeFigur = ausgewaehltesFeld.getFigur();
                            boolean istFigurSchwarz = ausgewaehltesFeld.isIstFigurSchwarz();

                            if (feld.getFigur() == null || feld.isIstFigurSchwarz() != ausgewaehltesFeld.isIstFigurSchwarz()) {


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

    private void markiereSchachbrett(final Feld[][] schachbrett, Feld feld) {
        Figur figur = feld.getFigur();
        if (figur == null) {
            //TODO: löschen von vorhandenen bewegungsmarkierungen?
            for (Feld[] zeile : schachbrett) {
                for (Feld einzelnesFeld : zeile) {
                    einzelnesFeld.setMarked(false);
                }
            }
            return;
        }

        int zeilenNummer = getZeilenNummer(feld, schachbrett);
        int spaltenNummer = getSpaltenNummer(feld, schachbrett);
        List<Feld> zeilenListe = Arrays.asList(schachbrett[zeilenNummer]);
        List<Feld> spaltenListe = Arrays.asList(schachbrett[spaltenNummer]);

        switch (figur) {

            case Bauer:
                break;

            case Turm:
//Nach rechts
                for (int i = spaltenNummer + 1; i < zeilenListe.size(); i++) {
                    Feld nachbarFeld = zeilenListe.get(i);
                    if (nachbarFeld.getFigur() == null) {
                        nachbarFeld.setMarked(true);
                    } else {
                        if (feld.isIstFigurSchwarz() != nachbarFeld.isIstFigurSchwarz()) {
                            nachbarFeld.setMarked(true);
                        }
                        break;
                    }
                }
 //Nach links
                for (int i = spaltenNummer - 1; i >= 0; i--) {
                    Feld nachbarFeld = zeilenListe.get(i);
                    if (nachbarFeld.getFigur() == null) {
                        nachbarFeld.setMarked(true);
                    } else {
                        if (feld.isIstFigurSchwarz() != nachbarFeld.isIstFigurSchwarz()) {
                            nachbarFeld.setMarked(true);
                        }
                        break;
                    }
                }
// Nach oben
                for (int i = zeilenNummer + 1; i < spaltenListe.size(); i ++){
                    Feld nachbarFeld = spaltenListe.get(i);
                  if (nachbarFeld.getFigur() == null){

                    //     for (Feld[] andereZeile : schachbrett) {
                             nachbarFeld.setMarked(true);
                          //  andereZeile[spaltenNummer].setMarked(true);
                     //   }
                      break;

//                  }
//                  else {
//                      if (feld.isIstFigurSchwarz() != nachbarFeld.isIstFigurSchwarz()) {
//                          nachbarFeld.setMarked(true);
//                      }
                 }

                }
            case Pferd:
                break;
            case Laufer:
                break;
            case Koenig:
                break;
            case Koenigin:
                for (int i = spaltenNummer + 1; i < zeilenListe.size(); i++) {
                    Feld nachbarFeld = zeilenListe.get(i);
                    if (nachbarFeld.getFigur() == null) {
                        nachbarFeld.setMarked(true);
                    } else {
                        if (feld.isIstFigurSchwarz() != nachbarFeld.isIstFigurSchwarz()) {
                            nachbarFeld.setMarked(true);
                        }
                        break;
                    }
                }
                for (int i = spaltenNummer - 1; i >= 0; i--) {
                    Feld nachbarFeld = zeilenListe.get(i);
                    if (nachbarFeld.getFigur() == null) {
                        nachbarFeld.setMarked(true);
                    } else {
                        if (feld.isIstFigurSchwarz() != nachbarFeld.isIstFigurSchwarz()) {
                            nachbarFeld.setMarked(true);
                        }
                        break;
                    }
                }

        }

    }

    private int getZeilenNummer(Feld feld, final Feld[][] schachbrett) {
        java.util.List<Feld[]> alleZeilen = Arrays.asList(schachbrett);
        for (Feld[] felds : alleZeilen) {
            java.util.List<Feld> zeilenListe = Arrays.asList(felds);
            if (zeilenListe.contains(feld)) {
                return alleZeilen.indexOf(felds);
            }
        }
        return -1;
    }

    private int getSpaltenNummer(Feld feld, final Feld[][] schachbrett) {
        int zeilenNummer = getZeilenNummer(feld, schachbrett);
        Feld[] felds = schachbrett[zeilenNummer];
        return Arrays.asList(felds).indexOf(feld);
    }

    private Feld sucheSelektiertesFeld(Feld[][] schachbrett) {
        for (Feld[] felds : schachbrett) {
            for (Feld feld : felds) {
                if (feld.isSelected()) {
                    return feld;
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
        } else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }


    private Figur getFigurFuerFeld(int zeile, int spalte) {
        //TODO
        return null;
    }
}

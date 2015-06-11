import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by e4_sevgi on 21.05.2015.
 */
public class StartFenster extends JFrame{

    public StartFenster(String title) {
        super(title);
       setSize(300, 200);
        setVisible(true);
        setLayout(new GridLayout(1 , 2));
        JButton einSpieler = new JButton("Ein Spieler");
        JButton zweiSpieler = new JButton("Zwei Spieler");

        this.add(einSpieler);
        this.add(zweiSpieler);

        ActionListener neuessSpiel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI hansolo = new GUI("KK");
                setVisible(false);
            }
        };




        einSpieler.addActionListener(neuessSpiel);






    }
}

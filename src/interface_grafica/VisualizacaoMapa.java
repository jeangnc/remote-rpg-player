package interface_grafica;

import modelos.Mapa;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VisualizacaoMapa extends JPanel {

    private Mapa mapa;

    /**
     *
     * @param m
     */
    VisualizacaoMapa(Mapa m) {
        mapa = m;
    }

    JPanel renderizar() {
        GridLayout experimentLayout = new GridLayout(mapa.retornarLargura(), mapa.retornarAltura());

        JPanel p = new JPanel();
        p.setLayout(experimentLayout);

        for (int i = 0; i < mapa.retornarLargura(); i++) {
            for (int j = 0; j < mapa.retornarAltura(); j++) {
                Border border = BorderFactory.createLineBorder(Color.RED, 1);
                JLabel label = new JLabel();
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.setBorder(border);

                int finalI = i;
                int finalJ = j;
                label.addMouseListener(new MouseAdapter()  {
                    @Override
                    public void mouseClicked(MouseEvent e)  {
                        System.out.println(finalI + " - " + finalJ);

                    }
                });

                p.add(label);
            }
        }

        return p;
    }

}
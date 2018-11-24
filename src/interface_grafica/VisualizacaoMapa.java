package interface_grafica;

import modelos.Mapa;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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
                JLabel label = new JLabel(i + " - " + j);
                label.setBorder(border);
                p.add(label);
            }
        }

        return p;
    }

}
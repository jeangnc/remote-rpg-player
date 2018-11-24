package interface_grafica;

import modelos.Mapa;

import javax.swing.*;

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
        JPanel p = new JPanel();
        p.add(new JLabel("Teste"));

        return p;
    }

}
package interface_grafica.visualizacoes;

import interface_grafica.Visualizacao;

import javax.swing.*;
import java.awt.*;

public class VisualizacaoPartida extends Visualizacao {

    private JPanel panel;

    public VisualizacaoPartida(JPanel barraStatus, JPanel mapa) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 10));
        panel.add(barraStatus, BorderLayout.PAGE_START);
        panel.add(mapa, BorderLayout.CENTER);
        mapa.setPreferredSize(new Dimension(800, 500));
    }

    @Override
    public JPanel renderizar() {
        return panel;
    }
}

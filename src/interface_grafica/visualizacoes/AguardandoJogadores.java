package interface_grafica.visualizacoes;

import interface_grafica.Visualizacao;

import javax.swing.*;

public class AguardandoJogadores extends Visualizacao {
    private JPanel panel;

    @Override
    public JPanel renderizar() {
        return panel;
    }
}

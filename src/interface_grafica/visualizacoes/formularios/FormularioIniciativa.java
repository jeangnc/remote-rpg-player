package interface_grafica.visualizacoes.formularios;

import interface_grafica.Visualizacao;

import javax.swing.*;

public class FormularioIniciativa extends Visualizacao {
    private JPanel panel;


    public JPanel renderizar() {
        return panel;
    }

    public void iniciativaPreenchida(String iniciativa) {}
}

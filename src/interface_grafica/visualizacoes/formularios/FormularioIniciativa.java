package interface_grafica.visualizacoes.formularios;

import interface_grafica.Visualizacao;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioIniciativa extends Visualizacao {
    private JPanel panel;
    private JTextField campoIniciativa;
    private JButton enviarButton;

    public FormularioIniciativa() {
        enviarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iniciativaPreenchida(Integer.parseInt(campoIniciativa.getText()));
            }
        });
    }

    @Override
    public JPanel renderizar() {
        return panel;
    }

    public void iniciativaPreenchida(int iniciativa) {}
}

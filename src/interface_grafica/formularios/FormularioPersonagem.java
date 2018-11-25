package interface_grafica.formularios;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioPersonagem {
    private JPanel panel;
    private JTextField campoNome;
    private JTextField campoHp;
    private JCheckBox campoInimigo;
    private JButton criarButton;

    public FormularioPersonagem() {
        criarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                personagemCriado(campoNome.getText(), Integer.parseInt(campoHp.getText()), campoInimigo.isSelected());
            }
        });
    }

    public JPanel renderizar() {
        return panel;
    }

    public void personagemCriado(String nome, int hpMaximo, boolean inimigo) {}
}

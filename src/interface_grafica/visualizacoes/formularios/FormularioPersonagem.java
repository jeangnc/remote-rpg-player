package interface_grafica.visualizacoes.formularios;

import interface_grafica.Visualizacao;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioPersonagem extends Visualizacao {
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

    @Override
    public JPanel renderizar() {
        return panel;
    }

    public void personagemCriado(String nome, int hpMaximo, boolean inimigo) {}
}

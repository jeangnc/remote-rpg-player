package interface_grafica.formularios;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioConexao {
    private JTextField campoNome;
    private JPanel panel;
    private JButton conectarButton;

    public FormularioConexao() {
        conectarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                conexaoSolicitada(campoNome.getText());
            }
        });
    }

    public JPanel renderizar() {
        return panel;
    }

    public void conexaoSolicitada(String nome) {}
}

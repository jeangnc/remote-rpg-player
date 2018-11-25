package interface_grafica;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioConexao {
    private JTextField campoNome;
    private JTextField campoId;
    private JPanel panel;
    private JButton conectarButton;

    FormularioConexao() {
        conectarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                conexaoSolicitada(Integer.parseInt(campoId.getText()), campoNome.getText());
            }
        });
    }

    void conexaoSolicitada(int id, String nome) { }

    JPanel renderizar() {
        return panel;
    }
}

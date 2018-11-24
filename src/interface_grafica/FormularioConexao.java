package interface_grafica;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class FormularioConexao {
    private JTextField campoNome;
    private JTextField campoId;
    private JPanel panel;
    private JButton conectarButton;

    void escutarEventosConectar(MouseAdapter adapter) {
        conectarButton.addMouseListener(adapter);
    }

    JPanel renderizar() {
        return panel;
    }

    String retornarNome() {
        return campoNome.getText();
    }

    String retornarId() {
        return campoId.getText();
    }
}

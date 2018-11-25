package interface_grafica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;

abstract class Janela {
    protected Controlador controlador;

    private JFrame frame;
    private String titulo;

    Janela(Controlador c) {
        this(c, "Old Dragon");
    }

    Janela(Controlador c, String t) {
        controlador = c;
        titulo = t;
    }

    void abrir() {
        frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        popularConteudo();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    void redimensionar(int largura, int altura) {
        frame.setSize(largura, altura);
        frame.setLocationRelativeTo(null);
    }

    void recarregar() {
        popularConteudo();
    }

    void fechar () {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    abstract JPanel renderizar();
    abstract JMenuBar renderizarMenu();

    private void popularConteudo() {
        JPanel conteudo = renderizar();
        conteudo.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(conteudo);

        JMenuBar barraMenu = renderizarMenu();
        if (barraMenu != null) {
            barraMenu.setMargin(new Insets(0,0,5,0));
            frame.setJMenuBar(barraMenu);
        }

        frame.setVisible(true);
    }
}
package interface_grafica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;

abstract class Janela {
    private static String prefixoTitulo;

    protected Controlador controlador;

    private JFrame frame;
    private String titulo;
    private boolean tamanhoFixado = false;

    static void definirPrefixoTitulo(String prefixo) {
        prefixoTitulo = prefixo;
    }

    Janela(Controlador c) {
        this(c, null);
    }

    Janela(Controlador c, String t) {
        controlador = c;
        titulo = prefixoTitulo;

        if (t != null) {
            titulo += " - " + t;
        }
    }

    /**
     *
     */
    void abrir() {
        frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        popularConteudo();
    }

    /**
     *
     * @param largura
     * @param altura
     */
    void redimensionar(int largura, int altura) {
        tamanhoFixado = true;
        frame.setSize(largura, altura);
        frame.setLocationRelativeTo(null);
    }

    /**
     *
     * @param t
     */
    void mudarTitulo(String t) {
        frame.setTitle(String.format("%s / %s", titulo, t));
    }

    /**
     *
     */
    void recarregar() {
        popularConteudo();
    }

    /**
     *
     */
    void fechar() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     *
     * @return
     */
    abstract protected JPanel renderizar();

    /**
     *
     * @return
     */
    abstract protected JMenuBar renderizarMenu();

    /**
     *
     */
    private void popularConteudo() {
        tamanhoFixado = false;

        JMenuBar barraMenu = renderizarMenu();
        if (barraMenu != null) {
            barraMenu.setMargin(new Insets(0, 0, 5, 0));
            frame.setJMenuBar(barraMenu);
        }

        JPanel conteudo = renderizar();
        conteudo.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame.setContentPane(conteudo);

        if (!tamanhoFixado) {
            frame.pack();
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
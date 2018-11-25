package interface_grafica;

import interface_grafica.eventos.EventoInterface;

import javax.swing.*;
import java.util.function.Consumer;

abstract class Janela {
    private JFrame frame;
    private Consumer<EventoInterface> ouvinte;

    void escutarEventos(Consumer<EventoInterface> o) {
        ouvinte = o;
    }

    void emitirEvento(EventoInterface e) {
        ouvinte.accept(e);
    }

    void abrir() {
        frame = new JFrame("Old Dragon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(renderizar());
        frame.pack();

        JMenuBar barraMenu = renderizarMenu();
        if (barraMenu != null) {
            frame.setJMenuBar(barraMenu);
        }

        frame.setVisible(true);
    }

    void redimensionar(int largura, int altura) {
        frame.setSize(largura, altura);
        frame.setLocationRelativeTo(null);
    }

    void recarregar() {
        frame.setContentPane(renderizar());
        frame.setVisible(true);
    }

    abstract JPanel renderizar();
    abstract JMenuBar renderizarMenu();
}
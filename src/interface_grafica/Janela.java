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
        frame.setContentPane(renderizarConteudo());
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void redimensionar(int largura, int altura) {
        frame.setSize(largura, altura);
        frame.setLocationRelativeTo(null);
    }

    void recarregar() {
        frame.setContentPane(renderizarConteudo());
        frame.setVisible(true);
    }

    abstract JPanel renderizarConteudo();
}
package interface_grafica;

import interface_grafica.eventos.EventoInterface;
import modelos.Partida;
import modelos.Personagem;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Controlador {
    private Partida partida;
    private JanelaPrincipal janelaPrincipal;
    private ArrayList<Consumer<EventoInterface>> ouvintes = new ArrayList<>();

    /**
     *
     * @param p
     */
    public Controlador(Partida p) {
        partida = p;
    }

    /**
     *
     * @param c
     */
    public void escutarEventos(Consumer<EventoInterface> c) {
        ouvintes.add(c);
    }

    public void iniciar() {
        janelaPrincipal = new JanelaPrincipal(partida);
        janelaPrincipal.abrir();
        propagarEventos(janelaPrincipal);
    }

    public void solicitarIniciativas() {
        // TODO - implement JanelaPrincipal.solicitarIniciativas
        throw new UnsupportedOperationException();
    }

    public void finalizarJogada() {
        // TODO - implement JanelaPrincipal.finalizarJogada
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param vencedor
     */
    public void exibirVencedor(String vencedor) {
        // TODO - implement JanelaPrincipal.exibirVencedor
        throw new UnsupportedOperationException();
    }

    public void recarregar() {
        janelaPrincipal.recarregar();
    }

    private void criarPersonagem() {
        // TODO - implement JanelaPrincipal.criarPersonagem
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param p
     */
    private void curar(Personagem p) {
        // TODO - implement JanelaPrincipal.curar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param p
     */
    private void atacar(Personagem p) {
        // TODO - implement JanelaPrincipal.atacar
        throw new UnsupportedOperationException();
    }

    private void desconectar() {
        // TODO - implement JanelaPrincipal.desconectar
        throw new UnsupportedOperationException();
    }

    private void propagarEventos(Janela j) {
        j.escutarEventos((e) -> {
            for (Consumer<EventoInterface> ouvinte : ouvintes) {
                ouvinte.accept(e);
            }
        });
    }
}
package interface_grafica;

import interface_grafica.eventos.EventoInterface;
import modelos.Partida;
import modelos.Personagem;

import java.util.ArrayList;
import java.util.function.Consumer;

public class JanelaPrincipal extends Janela {

    private ArrayList<Consumer<EventoInterface>> ouvintes;
    private VisualizacaoMapa mapa;

    /**
     *
     * @param p
     */
    public JanelaPrincipal(Partida p) {
        // TODO - implement JanelaPrincipal.JanelaPrincipal
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param c
     */
    public void escutarEventos(Consumer<EventoInterface> c) {
        // TODO - implement JanelaPrincipal.escutarEventos
        throw new UnsupportedOperationException();
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
        // TODO - implement JanelaPrincipal.recarregar
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

    private void criarPersonagem() {
        // TODO - implement JanelaPrincipal.criarPersonagem
        throw new UnsupportedOperationException();
    }

    private void desconectar() {
        // TODO - implement JanelaPrincipal.desconectar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param e
     */
    private void publicarEvento(EventoInterface e) {
        // TODO - implement JanelaPrincipal.publicarEvento
        throw new UnsupportedOperationException();
    }

}
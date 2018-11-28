package interface_grafica;

import interface_grafica.eventos.EventoInterface;
import modelos.Partida;
import modelos.Personagem;
import modelos.Posicao;

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

        Janela.definirPrefixoTitulo("Old Dragon");
    }

    void publicarEvento(EventoInterface e) {
        for (Consumer<EventoInterface> ouvinte : ouvintes) {
            ouvinte.accept(e);
        }
    }

    /**
     *
     * @param c
     */
    public void escutarEventos(Consumer<EventoInterface> c) {
        ouvintes.add(c);
    }

    public void iniciar() {
        janelaPrincipal = new JanelaPrincipal(this, partida);
        janelaPrincipal.abrir();
    }

    public void solicitarIniciativas() {
        ArrayList<Personagem> personagens = partida.retornarMeusPersonagens();

        personagens.forEach(personagem -> {
            new JanelaIniciativa(this, personagem).abrir();
        });
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
        // TODO precisa chamar recarregar em todas as janelas abertas
        janelaPrincipal.recarregar();
    }

    void criarPersonagem(Posicao pos) {
        JanelaCriacaoPersonagem j = new JanelaCriacaoPersonagem(this, pos);
        j.abrir();
    }

    /**
     *
     * @param p
     */
    void curar(Personagem p) {
        // TODO - implement JanelaPrincipal.curar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param p
     */
    void atacar(Personagem p) {
        // TODO - implement JanelaPrincipal.atacar
        throw new UnsupportedOperationException();
    }

    void desconectar() {
        // TODO - implement JanelaPrincipal.desconectar
        throw new UnsupportedOperationException();
    }
}
package modelos;

import modelos.eventos.EventoPartida;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Partida {

    private Mapa mapa;
    private Jogador jogador;
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private ArrayList<Turno> turnosPassados = new ArrayList<>();
    private Turno turnoAtual;
    private Iniciativa[] iniciativasRecebidas;
    private ArrayList<Consumer<EventoPartida>> ouvintes = new ArrayList<>();
    private boolean aguardandoIniciativas = false;
    private boolean iniciada = false;

    public Partida() {
        // TODO improve
        mapa = new Mapa(20, 20);
    }

    /**
     *
     * @param c
     */
    public void escutarEventos(Consumer<EventoPartida> c) {
    }

    /**
     *
     * @param id
     * @param nome
     */
    public void conectadoComo(int id, String nome) {
        Jogador j = new Jogador(id, nome);

        jogador = j;
        jogadores.add(j);
    }

    /**
     *
     * @return
     */
    public Jogador retornarJogador() {
        return jogador;
    }

    public Mapa retornarMapa() {
        return mapa;
    }

    /**
     *
     * @param j
     */
    public void adicionarJogador(Jogador j) {
        // TODO - implement Partida.adicionarJogador
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param p
     */
    public void adicionarPersonagem(Personagem p) {
        // TODO - implement Partida.adicionarPersonagem
        throw new UnsupportedOperationException();
    }

    public void iniciar() {
        // TODO - implement Partida.iniciar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param iniciativas
     */
    public void registrarIniciativas(Iniciativa[] iniciativas) {
        // TODO - implement Partida.registrarIniciativas
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param alvo
     * @param dano
     */
    public void atacar(Personagem alvo, int dano) {
        // TODO - implement Partida.atacar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param p
     * @param pontos
     */
    public void curar(Personagem p, int pontos) {
        // TODO - implement Partida.curar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param p
     * @param destino
     */
    public void mover(Personagem p, Posicao destino) {
        // TODO - implement Partida.mover
        throw new UnsupportedOperationException();
    }

    public void passarVez() {
        // TODO - implement Partida.passarVez
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param jogador
     */
    public void removerJogador(Jogador jogador) {
        // TODO - implement Partida.removerJogador
        throw new UnsupportedOperationException();
    }

    public boolean verificarAguardandoIniciativas() {
        // TODO - implement Partida.verificarAguardandoIniciativas
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param e
     */
    private void publicarEvento(EventoPartida e) {
        // TODO - implement Partida.publicarEvento
        throw new UnsupportedOperationException();
    }

    private void proximoTurno() {
        // TODO - implement Partida.proximoTurno
        throw new UnsupportedOperationException();
    }

    private void iniciarTurno() {
        // TODO - implement Partida.iniciarTurno
        throw new UnsupportedOperationException();
    }

    private void finalizarPartida() {
        // TODO - implement Partida.finalizarPartida
        throw new UnsupportedOperationException();
    }

}
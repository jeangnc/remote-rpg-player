package modelos;

import modelos.eventos.EventoPartida;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Partida {

    private boolean conectado = false;
    private boolean preparando = false;
    private boolean iniciada = false;
    private boolean aguardandoIniciativas = false;

    private Mapa mapa;
    private Jogador jogador;
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private ArrayList<Turno> turnosPassados = new ArrayList<>();
    private Turno turnoAtual;
    private Iniciativa[] iniciativasRecebidas;
    private ArrayList<Consumer<EventoPartida>> ouvintes = new ArrayList<>();

    public Partida() {
        // TODO improve
        mapa = new Mapa(20, 20);
    }

    /**
     * @param c
     */
    public void escutarEventos(Consumer<EventoPartida> c) {
        ouvintes.add(c);
    }

    /**
     *
     * @param id
     * @param nome
     */
    public void conectadoComo(String id, String nome) {
        conectado = true;
        jogador = new Jogador(id, nome);

        adicionarJogador(jogador);
    }

    /**
     * @param j
     */
    public void adicionarJogador(Jogador j) {
        if (!jogadores.contains(j)) {
            jogadores.add(j);
        }
    }

    /**
     *
     */
    public void iniciarPreparacao() {
        preparando = true;
    }

    /**
     * @param nome
     * @param hpMaximo
     * @param inimigo
     */
    public void adicionarPersonagem(String idJogador, String nome, int hpMaximo, boolean inimigo, int x, int y) {
        if (!preparando) {
            // TODO lancar exceção
        }

        // TODO atribuir jogador ao personagem
        // TODO adicionar personagem no jogador

        Personagem p = new Personagem(nome, hpMaximo, inimigo);
        p.mover(mapa.retornarPosicao(x, y));
        personagens.add(p);
    }

    public void iniciar() {
        preparando = false;
        // TODO - implement Partida.iniciar
        throw new UnsupportedOperationException();
    }

    /**
     * @param iniciativas
     */
    public void registrarIniciativas(Iniciativa[] iniciativas) {
        // TODO - implement Partida.registrarIniciativas
        throw new UnsupportedOperationException();
    }

    /**
     * @param alvo
     * @param dano
     */
    public void atacar(Personagem alvo, int dano) {
        // TODO - implement Partida.atacar
        throw new UnsupportedOperationException();
    }

    /**
     * @param p
     * @param pontos
     */
    public void curar(Personagem p, int pontos) {
        // TODO - implement Partida.curar
        throw new UnsupportedOperationException();
    }

    /**
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
     * @param jogador
     */
    public void removerJogador(Jogador jogador) {
        // TODO - implement Partida.removerJogador
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public boolean retornarConectado() {
        return conectado;
    }

    /**
     * @return
     */
    public boolean retornarIniciada() {
        return iniciada;
    }

    /**
     * @return
     */
    public boolean retornarEmPreparacao() {
        return preparando;
    }

    /**
     * @return
     */
    public boolean retornarAguardandoIniciativas() {
        return aguardandoIniciativas;
    }

    /**
     * @return
     */
    public Mapa retornarMapa() {
        return mapa;
    }

    /**
     * @return
     */
    public Jogador retornarJogador() {
        return jogador;
    }

    /**
     * @param e
     */
    private void publicarEvento(EventoPartida e) {
        for (Consumer<EventoPartida> ouvinte : ouvintes) {
            ouvinte.accept(e);
        }
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
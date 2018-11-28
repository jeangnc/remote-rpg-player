package modelos;

import modelos.eventos.EventoPartida;
import modelos.eventos.SolicitarIniciativa;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public class Partida {

    private boolean conectado = false;
    private boolean preparando = false;
    private boolean iniciada = false;
    private boolean aguardandoIniciativas = false;

    private Mapa mapa;
    private Jogador jogador;
    private Turno turnoAtual;
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private ArrayList<Turno> turnosPassados = new ArrayList<>();
    private ArrayList<Iniciativa> iniciativasRecebidas = new ArrayList<>();
    private ArrayList<Consumer<EventoPartida>> ouvintes = new ArrayList<>();

    public Partida() {
        // TODO melhorar
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
     *
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
     *
     * @param idJogador
     * @param nome
     * @param hpMaximo
     * @param inimigo
     * @param x
     * @param y
     */
    public void adicionarPersonagem(String idJogador, String nome, int hpMaximo, boolean inimigo, int x, int y) {
        if (!preparando) {
            // TODO lançar exceção
        }

        Jogador j = encontrarJogador(idJogador);
        Personagem p = new Personagem(nome, hpMaximo, inimigo);
        p.definirDono(j);
        p.mover(mapa.retornarPosicao(x, y));
        personagens.add(p);
        j.adicionarPersonagem(p);
    }

    public void iniciar() {
        preparando = false;
        iniciada = true;

        prepararTurno();
    }

    /**
     *
     * @param idPersonagem
     * @param iniciativa
     */
    public void registrarIniciativas(String idPersonagem, int iniciativa) {
        Personagem p = encontrarPersonagem(idPersonagem);
        Iniciativa i = new Iniciativa(p, iniciativa);

        iniciativasRecebidas.add(i);

        // TODO precisa ser igual a quantidade de personagens VIVOS
        if (iniciativasRecebidas.size() == personagens.size()) {
            iniciarTurno();
        }
    }

    /**
     *
     * @param idPersonagem
     * @param dano
     */
    public void atacar(String idPersonagem, int dano) {
        // TODO - implement Partida.atacar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idPersonagem
     * @param pontos
     */
    public void curar(String idPersonagem, int pontos) {
        // TODO - implement Partida.curar
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idPersonagem
     * @param x
     * @param y
     */
    public void mover(String idPersonagem, int x, int y) {
        // TODO - implement Partida.mover
        throw new UnsupportedOperationException();
    }

    /**
     *
     */
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
     *
     * @return
     */
    public ArrayList<Personagem> retornarMeusPersonagens() {
        return jogador.retornarPersonagens();
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

    private void prepararTurno() {
        if (turnoAtual != null) {
            turnosPassados.add(turnoAtual);
            turnoAtual = null;
        }

        aguardandoIniciativas = true;
        publicarEvento(new SolicitarIniciativa());
    }

    private void iniciarTurno() {
        Turno t = new Turno(iniciativasRecebidas);

        aguardandoIniciativas = false;
        turnoAtual = t;
    }

    private void finalizarPartida() {
        // TODO - implement Partida.finalizarPartida
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @return
     */
    private Jogador encontrarJogador(String id) {
        Optional<Jogador> opt = jogadores.stream()
                .filter(o -> o.retornaId().equals(id))
                .findFirst();

        return opt.orElse(null);
    }

    /**
     *
     * @param id
     * @return
     */
    private Personagem encontrarPersonagem(String id) {
        Optional<Personagem> opt = personagens.stream()
                .filter(o -> o.retornaId().equals(id))
                .findFirst();

        return opt.orElse(null);
    }
}
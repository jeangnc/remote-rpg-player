package comunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import modelos.Jogador;

import java.util.ArrayList;
import java.util.function.Consumer;

class NetGamesProxy {
    private static final String DATA_SEPARATOR = "/";

    private boolean conectado = false;
    private int qtdeJogadores = 0;
    private int qtdeJogadoresConectados = 0;

    private Proxy proxy;
    private ArrayList<Consumer<Jogada>> consumidoresJogadas = new ArrayList<>();
    private ArrayList<Consumer<ArrayList<Jogador>>> consumidoresJogadores = new ArrayList<>();

    NetGamesProxy(int q) {
        qtdeJogadores = q;

        proxy = Proxy.getInstance();
        proxy.addOuvinte(new NetGamesOuvinte(this));

    }

    void conectar(String hostServidor, String idJogador, String nomeJogador)
            throws ArquivoMultiplayerException, NaoConectadoException, JahConectadoException, NaoPossivelConectarException {
        // abre a conexão com o servidor do NetGames
        // o netgames não possúi um identificador global para o jogador
        // então emulei um
        proxy.conectar(hostServidor, idJogador + DATA_SEPARATOR + nomeJogador);

        // solicita uma partida para `n` jogadores
        proxy.iniciarPartida(qtdeJogadores);

        // notifica que o jogador está pronto
        proxy.iniciarNovaPartida(0);

        conectado = true;
    }

    void ouvirJogadas(Consumer<Jogada> c) {
        consumidoresJogadas.add(c);
    }

    void ouvirNovosJogadores(Consumer<ArrayList<Jogador>> c) {
        consumidoresJogadores.add(c);
    }

    void enviarJogada(Jogada j) throws NaoJogandoException {
        if (!this.verificarTodosProntos()) {
            throw new NaoJogandoException();
        }

        proxy.enviaJogada(j);
    }

    void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException e) {
            e.printStackTrace();
        }
    }

    private void notificarNovoJogador() {
        qtdeJogadoresConectados = proxy.obterNomeAdversarios().size() + 1;

        if (verificarTodosProntos()) {
            notificarTodosConectados();
        }
    }

    private void notificarTodosConectados() {
        // TODO remover esse acoplamento com Jogador
        ArrayList<Jogador> jogadores = new ArrayList<>();

        // o netgames não me manda todas as notificações de conexão...
        // nos testes que fiz com partidas de 3 jogadores, o primeiro jogador não recebe a notificação de conexão do segundo
        // portando, vou emitir apenas um evento quando todos já estiverem conectados, dessa forma eu consigo garantir também
        // que o jogador terá um identificador global
        for (int i = 0; i < qtdeJogadoresConectados; i++) {
            String[] data = proxy.obterNomeAdversario(i + 1).split(DATA_SEPARATOR);
            String id = data[0];
            String nome = data[1];

            jogadores.add(new Jogador(id, nome));
        }

        consumidoresJogadores.forEach((e -> e.accept(jogadores)));
    }

    /**
     *
     * @return
     */
    private boolean verificarTodosProntos() {
        return conectado && qtdeJogadoresConectados >= qtdeJogadores;
    }

    /**
     *
     * @param j
     */
    private void notificarJogadaRecebida(Jogada j) {
        consumidoresJogadas.forEach((e -> e.accept(j)));
    }

    /**
     *
     */
    class NetGamesOuvinte implements OuvidorProxy {
        private NetGamesProxy netGames;

        NetGamesOuvinte(NetGamesProxy n) {
            netGames = n;
        }

        @Override
        public void receberJogada(Jogada j) {
            System.out.println("Recebi uma jogada");
            System.out.println(j);

            netGames.notificarJogadaRecebida(j);
        }

        @Override
        public void receberMensagem(String msg) {
            System.out.println("Mensagem recebida: " + msg);
        }

        @Override
        public void iniciarNovaPartida(Integer posicao) {
            netGames.notificarNovoJogador();
        }

        @Override
        public void finalizarPartidaComErro(String message) {
            System.out.println("Finalizado com erro: " + message);
        }

        @Override
        public void tratarPartidaNaoIniciada(String message) {
            System.out.println("Partida ainda não foi iniciada");
        }

        @Override
        public void tratarConexaoPerdida() {
            System.out.println("A conexão com o servidor foi perdida!");
        }

    }
}

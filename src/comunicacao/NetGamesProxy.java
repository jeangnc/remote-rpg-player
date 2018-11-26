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

class NetGamesProxy  {
    private boolean conectado = false;
    private int qtdeJogadores = 0;
    private int qtdeJogadoresConectados = 0;

    private Proxy proxy;
    private Consumer<Jogada> consumidorJogadas;
    private Consumer<ArrayList<Jogador>> consumidorJogadores;

    NetGamesProxy(int q) {
        qtdeJogadores = q;

        proxy = Proxy.getInstance();
        proxy.addOuvinte(new NetGamesOuvinte(this));

    }

    void conectar(String hostServidor, String nomeJogador)
            throws ArquivoMultiplayerException, NaoConectadoException, JahConectadoException, NaoPossivelConectarException
    {
        // abre a conexão com o servidor do NetGames
        proxy.conectar(hostServidor, nomeJogador);
        conectado = true;

        // solicita uma partida para `n` jogadores
        proxy.iniciarPartida(qtdeJogadores);

        // notifica que o jogador está pronto
        proxy.iniciarNovaPartida(0);
    }

    void ouvirJogadas(Consumer<Jogada> c) {
        consumidorJogadas = c;
    }

    void ouvirNovosJogadores(Consumer<ArrayList<Jogador>> c) {
        consumidorJogadores = c;
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
        int novaQtdeJogadores = proxy.obterNomeAdversarios().size() + 1;

        // algumas vezes eu recebo duas vezes a notificação de entrada do mesmo jogador
        if (novaQtdeJogadores > qtdeJogadoresConectados) {
            qtdeJogadoresConectados = novaQtdeJogadores;

            if (verificarTodosProntos()) {
                notificarTodosConectados();
            }
        }
    }

    private void notificarTodosConectados() {
        // TODO remover esse acoplamento com Jogador
        ArrayList<Jogador> jogadores = new ArrayList<>();

        // o netgames não me manda todas as notificações de conexão...
        // nos testes que fiz com partidas de 3 jogadores, o primeiro jogador não recebe a notificação de conexão do segundo
        // portando, vou emitir apenas um evento quando todos já estiverem conectados, dessa forma eu consigo garantir também
        // que o jogador terá um identificador global
        for (int i = 0; i < qtdeJogadoresConectados; i++ ) {
            int id = i + 1;
            String nome = proxy.obterNomeAdversario(id);

            jogadores.add(new Jogador(id, nome));
        }

        consumidorJogadores.accept(jogadores);
    }

    private boolean verificarTodosProntos() {
        return conectado && qtdeJogadoresConectados >= qtdeJogadores;
    }

    private void notificarJogadaRecebida(Jogada j) {
        consumidorJogadas.accept(j);
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

package comunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

import java.util.function.Consumer;

class NetGamesProxy  {
    private boolean conectado = false;
    private int qtdeJogadores = 0;

    private Proxy proxy;
    private Consumer<Jogada> consumidorJogadas;

    NetGamesProxy(int q) {
        qtdeJogadores = q;

        proxy = Proxy.getInstance();
        proxy.addOuvinte(new NetGamesOuvinte(this));

    }

    void conectar(String hostServidor, int idJogador, String nomeJogador)
            throws ArquivoMultiplayerException, NaoConectadoException, JahConectadoException, NaoPossivelConectarException
    {
        // abre a conexão com o servidor do NetGames
        proxy.conectar(hostServidor, nomeJogador);
        conectado = true;

        // solicita uma partida para `n` jogadores
        proxy.iniciarPartida(qtdeJogadores);

        // notifica que o jogador está pronto
        proxy.iniciarNovaPartida(idJogador);
    }

    void ouvirJogadas(Consumer<Jogada> c) {
        consumidorJogadas = c;
    }

    void notificarJogadaRecebida(Jogada j) {
        consumidorJogadas.accept(j);
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

    private boolean verificarTodosProntos() {
        return conectado && proxy.obterNomeAdversarios().size() + 1 >= qtdeJogadores;
    }
}

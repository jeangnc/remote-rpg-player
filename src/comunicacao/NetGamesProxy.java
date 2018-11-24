package comunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

import java.util.function.Consumer;

public class NetGamesProxy implements OuvidorProxy {
    private boolean conectado = false;
    private int qtdeJogadores = 0;

    private Proxy proxy;
    private Consumer<Jogada> consumidor;

    NetGamesProxy(int q) {
        qtdeJogadores = q;

        proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
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
        consumidor = c;
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

    @Override
    public void receberJogada(Jogada j) {
        consumidor.accept(j);
    }

    @Override
    public void receberMensagem(String msg) {
        System.out.println("Mensagem recebida: " + msg);
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        String text = String.format("Jogador %s está pronto. %d de %d", posicao, (proxy.obterNomeAdversarios().size() + 1), qtdeJogadores);
        System.out.println(text);
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

    private boolean verificarTodosProntos() {
        return conectado && proxy.obterNomeAdversarios().size() + 1 >= qtdeJogadores;
    }
}

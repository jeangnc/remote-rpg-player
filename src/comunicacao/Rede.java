package comunicacao;

import br.ufsc.inf.leobr.cliente.exception.*;
import modelos.Jogador;

import java.util.function.Consumer;

public class Rede {
    private NetGamesProxy netGames;

    public Rede(int nJogadores) {
        netGames = new NetGamesProxy(nJogadores);
    }

    /**
     *
     * @param hostServidor
     * @param idJogador
     * @param nomeJogador
     * @return
     * @throws NaoPossivelConectarException
     * @throws ArquivoMultiplayerException
     * @throws JahConectadoException
     * @throws NaoConectadoException
     */
    public Jogador conectar(String hostServidor, int idJogador, String nomeJogador)
            throws NaoPossivelConectarException, ArquivoMultiplayerException, JahConectadoException, NaoConectadoException
    {
        netGames.conectar(hostServidor, idJogador, nomeJogador);
        return new Jogador(idJogador, nomeJogador);
    }

    public void escutarEventos(Class<?> tipo, Consumer<Object> c) {
        netGames.ouvirJogadas((jogada) -> {
            Mensagem mensagem = (Mensagem) jogada;
            Evento evento = mensagem.recuperarEvento();

            if (tipo.isInstance(evento)) {
                c.accept(tipo.cast(evento));
            }
        });
    }

    /**
     *
     * @param e
     * @throws NaoJogandoException
     */
    public void transmitirEvento(Evento e) throws NaoJogandoException  {
        netGames.enviarJogada(new Mensagem(e));
    }

    public void desconectar() {
        // TODO - implement Rede.desconectar
        throw new UnsupportedOperationException();
    }
}
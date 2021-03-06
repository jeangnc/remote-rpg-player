package comunicacao;

import br.ufsc.inf.leobr.cliente.exception.*;
import modelos.Jogador;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Rede {
    private String idJogadorConectado;
    private NetGamesProxy netGames;

    public Rede(int nJogadores) {
        netGames = new NetGamesProxy(nJogadores);
    }

    /**
     *
     * @param hostServidor
     * @param idJogador
     * @param nomeJogador
     * @throws NaoPossivelConectarException
     * @throws ArquivoMultiplayerException
     * @throws JahConectadoException
     * @throws NaoConectadoException
     */
    public void conectar(String hostServidor, String idJogador, String nomeJogador)
            throws NaoPossivelConectarException, ArquivoMultiplayerException, JahConectadoException, NaoConectadoException {
        netGames.conectar(hostServidor, idJogador, nomeJogador);
        idJogadorConectado = idJogador;
    }

    public void ouvirEventos(Class<?> tipo, BiConsumer<String, Object> c) {
        netGames.ouvirJogadas((jogada) -> {
            Mensagem mensagem = (Mensagem) jogada;
            String remetente = mensagem.retornarRemetente();
            Evento evento = mensagem.retornarEvento();

            if (tipo.isAssignableFrom(evento.getClass())) {
                c.accept(remetente, tipo.cast(evento));
            }
        });
    }

    public void ouvirNovosJogadores(Consumer<ArrayList<Jogador>> c) {
        netGames.ouvirNovosJogadores(c);
    }

    /**
     * @param e
     * @throws NaoJogandoException
     */
    public void transmitirEvento(Evento e) throws NaoJogandoException {
        netGames.enviarJogada(new Mensagem(idJogadorConectado, e));
    }

    public void desconectar() {
        netGames.desconectar();
    }
}
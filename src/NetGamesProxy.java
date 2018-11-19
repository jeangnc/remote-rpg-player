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
    private Proxy proxy;
    private Consumer<Jogada> consumidor;
    private int idJogador;
    private String nomeJogador;
    private int qtdeJogadores;

	NetGamesProxy(int q, int i, String n) {
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);

		qtdeJogadores = q;
		idJogador = i;
		nomeJogador = n;
	}

	void ouvir(Consumer<Jogada> c) {
        consumidor = c;

        abrirConexao("localhost");
        requererPartida();
        notificarProntidao();
    }

	void enviarJogada(Jogada j) throws NaoJogandoException {
	    if (!this.todosProntos()) {
	        throw new NaoJogandoException();
        }

		try {
			proxy.enviaJogada(j);
		} catch (NaoJogandoException e) {
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
		String text = String.format("Jogador %s está pronto. %d de %d", posicao, (proxy.obterNomeAdversarios().size() + 1), this.qtdeJogadores);
        System.out.println(text);
    }

	@Override
	public void finalizarPartidaComErro(String message) {
		System.out.println("Finalizado com erro: " + message);
	}

	@Override
	public void tratarConexaoPerdida() {
		System.out.println("A conexão com o servidor foi perdida!");
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		System.out.println("Partida ainda não foi iniciada");
	}

    private void abrirConexao(String servidor) {
        try {
            proxy.conectar(servidor, nomeJogador);

        } catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
            e.printStackTrace();
        }
    }

    private void requererPartida() {
        try {
            proxy.iniciarPartida(qtdeJogadores);
        } catch (NaoConectadoException e) {
            e.printStackTrace();
        }
    }

    private void notificarProntidao() {
        proxy.iniciarNovaPartida(idJogador);
    }

    private boolean todosProntos() {
        return proxy.obterNomeAdversarios().size() + 1 >= qtdeJogadores;
    }
}

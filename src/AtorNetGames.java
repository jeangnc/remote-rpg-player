import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetGames implements OuvidorProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Proxy proxy;

	public AtorNetGames() {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	/**
	 *
	 * @param servidor
	 * @param nome
	 * @return
	 */
	public boolean conectar(String servidor, String nome) {
		try {
			proxy.conectar(servidor, nome);
			return true;
		} catch (JahConectadoException e) {
			e.printStackTrace();
			return false;
		} catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			return false;
		} catch (ArquivoMultiplayerException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean desconectar() {
		try {
			proxy.desconectar();
			return true;
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return false;
		}
	}

//	public void enviarJogada(Lance lance) {
//		try {
//			proxy.enviaJogada(lance);
//		} catch (NaoJogandoException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param idUsuario
	 */
	public List<String> informarNomeAdversarios() {
		List<String> aux = proxy.obterNomeAdversarios();
		return aux;
	}

	@Override
	public void iniciarNovaPartida(Integer i) {
//		interfaceGrafica.tratarIniciarPartida();
	}

	public void iniciarPartida(int nrJogadores) {
		try {
			proxy.iniciarPartida(nrJogadores);
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void receberJogada(Jogada jogada) {
//		Lance estado = (Lance) jogada;
//		interfaceGrafica.receberJogada(estado);
	}

	@Override
	public void receberMensagem(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

}
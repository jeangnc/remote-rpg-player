import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import comunicacao.Rede;
import interface_grafica.Controlador;
import interface_grafica.eventos.ConexaoSolicitada;
import interface_grafica.eventos.EventoInterface;
import modelos.Partida;
import modelos.eventos.EventoPartida;

class Barramento {
    private Partida partida;
    private Controlador controlador;
    private Rede rede;

    /**
     *
     * @param p
     * @param c
     * @param r
     */
    Barramento(Partida p, Controlador c, Rede r) {
        partida = p;
        controlador = c;
        rede = r;

        escutarEventos();
    }

    private void escutarEventos() {
        controlador.escutarEventos(this::processarEventoInterface);
        partida.escutarEventos(this::processarEventoPartida);

        rede.escutarEventos(EventoInterface.class, e -> {
            this.processarEventoInterface((EventoInterface) e);
        });

        rede.escutarEventos(EventoPartida.class, e -> {
            this.processarEventoPartida((EventoPartida) e);
        });
    }

    /**
     *
     * @param eventoInterface
     */
    private void processarEventoInterface(EventoInterface eventoInterface) {
        if (eventoInterface instanceof ConexaoSolicitada) {
            ConexaoSolicitada e = (ConexaoSolicitada) eventoInterface;

            try {
                rede.conectar("localhost", e.getId(), e.getNome());
                partida.conectadoComo(e.getId(), e.getNome());
                controlador.recarregar();

            } catch (NaoPossivelConectarException | ArquivoMultiplayerException | JahConectadoException | NaoConectadoException e1) {
                e1.printStackTrace();
            }
        }

//        rede.transmitirEvento(e);
    }

    /**
     *
     * @param eventoPartida
     */
    private void processarEventoPartida(EventoPartida eventoPartida) {
    }
}
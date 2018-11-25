import br.ufsc.inf.leobr.cliente.exception.*;
import comunicacao.Rede;
import interface_grafica.Controlador;
import interface_grafica.eventos.ConexaoSolicitada;
import interface_grafica.eventos.EventoInterface;
import interface_grafica.eventos.PersonagemAdicionado;
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
                rede.conectar("localhost", e.retornaId(), e.retornaNome());
                partida.conectadoComo(e.retornaId(), e.retornaNome());
                controlador.recarregar();

            } catch (NaoPossivelConectarException | ArquivoMultiplayerException | JahConectadoException | NaoConectadoException e1) {
                e1.printStackTrace();
            }
        }

        if (eventoInterface instanceof PersonagemAdicionado) {
            PersonagemAdicionado e = (PersonagemAdicionado) eventoInterface;

            try {
                partida.adicionarPersonagem(e.retornaNome(), e.retornaHpMaximo(), e.retornaInimigo());
                controlador.recarregar();
                rede.transmitirEvento(eventoInterface);

            } catch (NaoJogandoException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     *
     * @param eventoPartida
     */
    private void processarEventoPartida(EventoPartida eventoPartida) {
    }
}
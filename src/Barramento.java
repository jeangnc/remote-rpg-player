import br.ufsc.inf.leobr.cliente.exception.*;
import comunicacao.Rede;
import interface_grafica.Controlador;
import interface_grafica.eventos.ConexaoSolicitada;
import interface_grafica.eventos.EventoInterface;
import interface_grafica.eventos.PersonagemAdicionado;
import modelos.Jogador;
import modelos.Partida;
import modelos.eventos.EventoPartida;
import modelos.eventos.SolicitarIniciativa;

class Barramento {
    private Partida partida;
    private Controlador controlador;
    private Rede rede;

    /**
     * @param p
     * @param c
     * @param r
     */
    Barramento(Partida p, Controlador c, Rede r) {
        partida = p;
        controlador = c;
        rede = r;

        ouvirEventos();
    }

    private void ouvirEventos() {
        controlador.escutarEventos((eventoInterface) -> {
            // evento de solicitação de conexão é uma exceção.. ele não é treatado pela partida e sim pela rede
            // então ele não é transmitido para a rede
            if (eventoInterface instanceof ConexaoSolicitada) {
                ConexaoSolicitada e = (ConexaoSolicitada) eventoInterface;

                try {
                    rede.conectar("localhost", e.retornaNome());
                    partida.conectadoComo(e.retornaNome());
                    controlador.recarregar();

                } catch (NaoPossivelConectarException | ArquivoMultiplayerException | JahConectadoException | NaoConectadoException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    System.out.println("Recebi evento da interface");
                    processarEventoInterface(eventoInterface);
                    rede.transmitirEvento(eventoInterface);
                } catch (NaoJogandoException e1) {
                    e1.printStackTrace();
                }
            }
        });

        partida.escutarEventos((e) -> {
            System.out.println("Recebi evento da partida");
            processarEventoPartida(e);
        });

        rede.ouvirEventos(EventoInterface.class, e -> {
            System.out.println("Recebi evento da interface via rede");
            processarEventoInterface((EventoInterface) e);
        });

        rede.ouvirEventos(EventoPartida.class, e -> {
            System.out.println("Recebi evento da partida via rede");
            processarEventoPartida((EventoPartida) e);
        });

        rede.ouvirNovosJogadores((jogadores) -> {
            System.out.println("Recebendo novos jogadores");

            // TODO setar o ID do meu jogador, quando me conectei eu ainda não tinha
            for(Jogador j : jogadores) {
                partida.adicionarJogador(j);
            }

            partida.iniciarPreparacao();
            controlador.recarregar();
        });
    }

    /**
     * @param eventoInterface
     */
    private void processarEventoInterface(EventoInterface eventoInterface) {
        if (eventoInterface instanceof PersonagemAdicionado) {
            PersonagemAdicionado e = (PersonagemAdicionado) eventoInterface;
            partida.adicionarPersonagem(e.retornaNome(), e.retornaHpMaximo(), e.retornaInimigo());
            controlador.recarregar();
        }
    }

    /**
     * @param eventoPartida
     */
    private void processarEventoPartida(EventoPartida eventoPartida) {
        if (eventoPartida instanceof SolicitarIniciativa) {
            SolicitarIniciativa e = (SolicitarIniciativa) eventoPartida;
        }
    }
}
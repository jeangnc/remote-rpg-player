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

import java.util.UUID;

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
                abrirConexao((ConexaoSolicitada) eventoInterface);
                return;
            }

            // eventos que precisam ser transmitidos na rede
            try {
                processarEventoInterface(partida.retornarJogador().retornaId(), eventoInterface);
                rede.transmitirEvento(eventoInterface);
            } catch (NaoJogandoException e1) {
                e1.printStackTrace();
            }
        });

        partida.escutarEventos((e) -> {
            processarEventoPartida(partida.retornarJogador().retornaId(), e);
        });

        rede.ouvirEventos(EventoInterface.class, (idJogador, evento) -> {
            processarEventoInterface(idJogador, (EventoInterface) evento);
        });

        // a partida não deveria, em teoria, publicar eventos na rede
        // rede.ouvirEventos(EventoPartida.class, (idJogador, evento) -> {
        //    processarEventoPartida(idJogador, (EventoPartida) evento);
        // });

        rede.ouvirNovosJogadores((jogadores) -> {
            for(Jogador j : jogadores) {
                partida.adicionarJogador(j);
            }

            partida.iniciarPreparacao();
            controlador.recarregar();
        });
    }

    private void abrirConexao(ConexaoSolicitada evento) {
        try {
            String idJogador = UUID.randomUUID().toString();
            String nomeJogador = evento.retornaNome();

            rede.conectar("localhost", idJogador, nomeJogador);
            partida.conectadoComo(idJogador, nomeJogador);
            controlador.recarregar();

        } catch (NaoPossivelConectarException | ArquivoMultiplayerException | JahConectadoException | NaoConectadoException e1) {
            e1.printStackTrace();
        }
    }

    /**
     *
     * @param idJogador
     * @param eventoInterface
     */
    private void processarEventoInterface(String idJogador, EventoInterface eventoInterface) {
        System.out.println("Processando evento de " + idJogador);

        if (eventoInterface instanceof PersonagemAdicionado) {
            PersonagemAdicionado e = (PersonagemAdicionado) eventoInterface;
            partida.adicionarPersonagem(e.retornaNome(), e.retornaHpMaximo(), e.retornaInimigo());
            controlador.recarregar();
        }
    }

    /**
     *
     * @param idJogador
     * @param eventoPartida
     */
    private void processarEventoPartida(String idJogador, EventoPartida eventoPartida) {
        System.out.println("Processando evento de " + idJogador);

        if (eventoPartida instanceof SolicitarIniciativa) {
            SolicitarIniciativa e = (SolicitarIniciativa) eventoPartida;
        }
    }
}
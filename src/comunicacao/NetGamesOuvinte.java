package comunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;


class NetGamesOuvinte implements OuvidorProxy {
    private NetGamesProxy netGames;

    NetGamesOuvinte(NetGamesProxy n) {
        netGames = n;
    }

    @Override
    public void receberJogada(Jogada j) {
        netGames.notificarJogadaRecebida(j);
    }

    @Override
    public void receberMensagem(String msg) {
        System.out.println("Mensagem recebida: " + msg);
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        String text = String.format("Jogador %s está pronto.", posicao);
        System.out.println(text);


        // TODO separar a implementação das ações de quem recebe os eventos
        /*
        for (StackTraceElement t : Thread.currentThread().getStackTrace()) {
            System.out.println(t);
        }
        */
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

}

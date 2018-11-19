import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;

import java.io.Serializable;
import java.util.function.Consumer;

class Rede {
    private NetGamesProxy netGames;

    Rede(NetGamesProxy p) {
        netGames = p;
    }

    void enviarObjeto(Serializable o) throws NaoJogandoException {
        netGames.enviarJogada(new Mensagem(o));
    }

    void escutarObjetos(Class<?> tipo, Consumer<Object> c) {
        netGames.ouvir((jogada) -> {
            Mensagem mensagem = (Mensagem) jogada;
            Object objeto = mensagem.recuperarObjeto();

            if (tipo.isInstance(objeto)) {
                c.accept(tipo.cast(objeto));
            }
        });
    }
}

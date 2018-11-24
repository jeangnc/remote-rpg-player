package comunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;

class Mensagem implements Jogada {
    private Evento evento;

    Mensagem(Evento e) {
        super();
        evento = e;
    }

    Evento retornarEvento() {
        return evento;
    }
}
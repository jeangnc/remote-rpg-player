package comunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;

class Mensagem implements Jogada {
    private String remetente;
    private Evento evento;

    Mensagem(String r, Evento e) {
        super();
        remetente = r;
        evento = e;
    }

    String retornarRemetente() {
        return remetente;
    }

    Evento retornarEvento() {
        return evento;
    }
}
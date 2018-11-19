import br.ufsc.inf.leobr.cliente.Jogada;

import java.io.Serializable;

class Mensagem implements Jogada {
	private Object objeto;

	Mensagem(Serializable o) {
		super();
        objeto = o;
    }

    Object recuperarObjeto() {
	    return objeto;
    }
}
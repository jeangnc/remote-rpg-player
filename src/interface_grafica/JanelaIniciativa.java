package interface_grafica;

import interface_grafica.eventos.IniciativaInformada;
import interface_grafica.visualizacoes.formularios.FormularioIniciativa;
import modelos.Personagem;

import javax.swing.*;

class JanelaIniciativa extends Janela {

    Personagem personagem;

    JanelaIniciativa(Controlador c, Personagem p) {
        super(c, "Iniciativa: " + p.retornaNome());
        personagem = p;
    }

    @Override
    protected JPanel renderizar() {
        return new FormularioIniciativa() {
            @Override
            public void iniciativaPreenchida(int iniciativa) {
                controlador.publicarEvento(new IniciativaInformada(personagem.retornaId(), iniciativa));
                fechar();
            }
        }.renderizar();
    }

    @Override
    protected JMenuBar renderizarMenu () {
        return null;
    }
}
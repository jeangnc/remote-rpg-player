package interface_grafica;

import interface_grafica.eventos.PersonagemAdicionado;
import interface_grafica.visualizacoes.formularios.FormularioPersonagem;
import modelos.Posicao;

import javax.swing.*;

class JanelaCriacaoPersonagem extends Janela {
    private Posicao posicao;

    JanelaCriacaoPersonagem(Controlador c, Posicao pos) {
        super(c, "Criação de personagem");
        posicao = pos;
    }

    @Override
    protected JPanel renderizar() {
        return new FormularioPersonagem() {
            @Override
            public void personagemCriado(String nome, int hpMaximo, boolean inimigo) {
                PersonagemAdicionado e = new PersonagemAdicionado(nome, hpMaximo, inimigo);
                e.definirPosicao(posicao);
                controlador.publicarEvento(e);
                fechar();
            }
        }.renderizar();
    }

    @Override
    protected JMenuBar renderizarMenu () {
        return null;
    }
}
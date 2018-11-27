package interface_grafica;

import interface_grafica.eventos.PersonagemAdicionado;
import interface_grafica.visualizacoes.FormularioPersonagem;
import modelos.Posicao;

import javax.swing.*;

class JanelaCriacaoPersonagem extends Janela {
    private Posicao posicao;

    JanelaCriacaoPersonagem(Controlador c, Posicao pos) {
        super(c, "Criação de personagem");
        posicao = pos;
    }

    JPanel renderizar() {
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

    JMenuBar renderizarMenu () {
        return null;
    }
}
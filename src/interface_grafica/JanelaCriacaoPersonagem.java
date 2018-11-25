package interface_grafica;

import interface_grafica.eventos.PersonagemAdicionado;
import interface_grafica.formularios.FormularioPersonagem;

import javax.swing.*;

class JanelaCriacaoPersonagem extends Janela {
    JanelaCriacaoPersonagem(Controlador c) {
        super(c, "Criação de personagem");
    }

    JPanel renderizar() {
        return new FormularioPersonagem() {
            @Override
            public void personagemCriado(String nome, int hpMaximo, boolean inimigo) {
                controlador.publicarEvento(new PersonagemAdicionado(nome, hpMaximo, inimigo));
            }
        }.renderizar();
    }

    JMenuBar renderizarMenu () {
        return null;
    }
}
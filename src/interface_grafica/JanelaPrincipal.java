package interface_grafica;

import interface_grafica.eventos.ConexaoSolicitada;
import modelos.Partida;

import javax.swing.*;

class JanelaPrincipal extends Janela {
    private Partida partida;

    JanelaPrincipal(Partida p) {
        partida = p;
    }

    @Override
    JPanel renderizar() {
        redimensionar(300, 100);

        if (partida.retornarJogador() == null) {
            FormularioConexao f = new FormularioConexao() {
                @Override
                void conexaoSolicitada(int id, String nome) {
                    emitirEvento(new ConexaoSolicitada(id, nome));
                }
            };

            return f.renderizar();
        }

        redimensionar(800, 600);

        VisualizacaoMapa m = new VisualizacaoMapa(partida.retornarMapa()) {
            @Override
            public void personagemAtacado() {
                System.out.println("Personagem atacado");
            }

            @Override
            public void personagemCurado() {
                System.out.println("Personagem curado");
            }

            @Override
            public void personagemMovido() {
                System.out.println("Movido");
            }
        };

        return m.renderizar();
    }
}

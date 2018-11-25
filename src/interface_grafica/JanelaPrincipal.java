package interface_grafica;

import interface_grafica.eventos.ConexaoSolicitada;
import interface_grafica.formularios.FormularioConexao;
import modelos.Partida;

import javax.swing.*;
import java.awt.event.KeyEvent;

class JanelaPrincipal extends Janela {
    private Partida partida;

    JanelaPrincipal(Controlador c, Partida p) {
        super(c);
        partida = p;
    }

    @Override
    JPanel renderizar() {
        redimensionar(300, 150);

        if (partida.retornarJogador() == null) {
            FormularioConexao f = new FormularioConexao() {
                @Override
                void conexaoSolicitada(int id, String nome) {
                    controlador.publicarEvento(new ConexaoSolicitada(id, nome));
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

    JMenuBar renderizarMenu () {
        if (partida.retornarJogador() == null) {
            return null;
        }

        JMenu menu = new JMenu("Jogo");

        JMenuItem menuItem;
        menuItem = new JMenuItem("Adicionar jogador", KeyEvent.VK_A);
        menuItem.addActionListener(e -> {
            controlador.criarPersonagem();
        });
        menu.add(menuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        return menuBar;
    }
}

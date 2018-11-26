package interface_grafica;

import interface_grafica.eventos.ConexaoSolicitada;
import interface_grafica.visualizacoes.FormularioConexao;
import interface_grafica.visualizacoes.AguardandoJogadores;
import interface_grafica.visualizacoes.VisualizacaoMapa;
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
        if (!partida.retornarConectado()) {
            return renderizarFormularioConexao();
        }

        if (!partida.retornarIniciada() && !partida.retornarEmPreparacao()) {
            return renderizarAguardandoJogadores();
        }

        return renderizarMapa();
    }

    JMenuBar renderizarMenu () {
        if (!partida.retornarEmPreparacao()) {
            return null;
        }

        JMenu menu = new JMenu("Jogo");

        JMenuItem menuItem;
        menuItem = new JMenuItem("Adicionar personagem", KeyEvent.VK_A);
        menuItem.addActionListener(e -> {
            controlador.criarPersonagem();
        });
        menu.add(menuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel renderizarFormularioConexao() {
        return new FormularioConexao() {
            @Override
            public void conexaoSolicitada(String nome) {
                controlador.publicarEvento(new ConexaoSolicitada(nome));
            }
        }.renderizar();
    }

    private JPanel renderizarAguardandoJogadores() {
        return new AguardandoJogadores().renderizar();
    }

    private JPanel renderizarMapa() {
        redimensionar(800, 600);

        return new VisualizacaoMapa(partida.retornarMapa()) {
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
        }.renderizar();
    }
}

package interface_grafica;

import interface_grafica.eventos.ConexaoSolicitada;
import interface_grafica.eventos.DesconexaoSolicitada;
import interface_grafica.eventos.InicioSolicitado;
import interface_grafica.eventos.PersonagemMovido;
import interface_grafica.visualizacoes.formularios.FormularioConexao;
import interface_grafica.visualizacoes.AguardandoJogadores;
import interface_grafica.visualizacoes.VisualizacaoMapa;
import modelos.Partida;
import modelos.Personagem;
import modelos.Posicao;

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

        mudarTitulo(partida.retornarJogador().retornarNome());

        return renderizarMapa();
    }

    JMenuBar renderizarMenu () {
        if (!partida.retornarEmPreparacao()) {
            return null;
        }

        JMenu menu = new JMenu("Jogo");
        JMenuItem menuItem;

        menuItem = new JMenuItem("Iniciar partida", KeyEvent.VK_A);
        menuItem.addActionListener(e -> {
            controlador.publicarEvento(new InicioSolicitado());
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Desconectar", KeyEvent.VK_A);
        menuItem.addActionListener(e -> {
            controlador.publicarEvento(new DesconexaoSolicitada());
        });
        menu.add(menuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel renderizarMapa() {
        redimensionar(800, 600);

        return new VisualizacaoMapa(partida.retornarMapa(), partida.retornarIniciada()) {
            public void adicionarPersonagem(Posicao pos) {
                controlador.criarPersonagem(pos);
            }

            @Override
            public void atacarPersonagem(Personagem p) {
                 controlador.atacar(p);
            }

            @Override
            public void curarPersonagem(Personagem p) {
                 controlador.curar(p);
            }

            @Override
            public void moverPersonagem(Personagem p, Posicao pos) {
                PersonagemMovido e = new PersonagemMovido(p.retornaId(), pos.retornarCoordenadaX(), pos.retornarCoordenadaY());
                controlador.publicarEvento(e);
            }
        }.renderizar();
    }

    private JPanel renderizarAguardandoJogadores() {
        return new AguardandoJogadores().renderizar();
    }

    private JPanel renderizarFormularioConexao() {
        return new FormularioConexao() {
            @Override
            public void conexaoSolicitada(String nome) {
                controlador.publicarEvento(new ConexaoSolicitada(nome));
            }
        }.renderizar();
    }
}

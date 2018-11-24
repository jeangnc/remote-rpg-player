package interface_grafica;

import interface_grafica.eventos.ConexaoSolicitada;
import modelos.Partida;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class JanelaPrincipal extends Janela {
    private Partida partida;

    JanelaPrincipal(Partida partida) {
        this.partida = partida;
    }

    @Override
    JPanel renderizarConteudo() {
        if (partida.retornarJogador() == null) {
            FormularioConexao f = new FormularioConexao();

            f.escutarEventosConectar(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    emitirEvento(new ConexaoSolicitada(Integer.parseInt(f.retornarId()), f.retornarNome()));
                }
            });

            return f.renderizar();
        }

        VisualizacaoMapa m = new VisualizacaoMapa(partida.retornarMapa());
        return m.renderizar();
    }
}

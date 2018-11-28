package interface_grafica.visualizacoes;

import interface_grafica.Visualizacao;
import modelos.Mapa;
import modelos.Personagem;
import modelos.Posicao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizacaoMapa extends Visualizacao {
    private Mapa mapa;
    private boolean partidaIniciada;

    /**
     *
     * @param m
     */
    public VisualizacaoMapa(Mapa m, boolean i) {
        mapa = m;
        partidaIniciada = i;
    }

    @Override
    public JPanel renderizar() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(mapa.retornarLargura(), mapa.retornarAltura()));

        for (int i = 0; i < mapa.retornarLargura(); i++) {
            for (int j = 0; j < mapa.retornarAltura(); j++) {
                Posicao posicao = mapa.retornarPosicao(i, j);
                Personagem personagem = posicao.retornarOcupante();

                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

                if (personagem != null) {
                    label.setText(personagem.retornaNome());
                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if (e.isPopupTrigger()) {
                            mostrarMenuContexto(e);
                        }
                    }

                    private void mostrarMenuContexto(MouseEvent e){
                        JPopupMenu menu = posicao.retornarDisponivel()
                                ? new PopupPosicaoDisponivel(personagem, posicao)
                                : new PopupPosicaoOcupada(personagem);

                        menu.show(e.getComponent(), e.getX(), e.getY());
                    }
                });

                panel.add(label);
            }
        }

        return panel;
    }

    public void adicionarPersonagem(Posicao pos) { }

    public void atacarPersonagem(Personagem p) { }

    public void curarPersonagem(Personagem p) { }

    public void moverPersonagem(Personagem p, Posicao pos) { }

    private class PopupPosicaoOcupada extends JPopupMenu {
        PopupPosicaoOcupada(Personagem p) {
            JMenuItem atacar = new JMenuItem("Atacar");
            atacar.addActionListener(e -> atacarPersonagem(p));
            add(atacar);

            JMenuItem curar = new JMenuItem("Curar");
            curar.addActionListener(e -> curarPersonagem(p));
            add(curar);
        }
    }

    private class PopupPosicaoDisponivel extends JPopupMenu {
        PopupPosicaoDisponivel(Personagem p, Posicao pos) {
            if (partidaIniciada) {
                JMenuItem mover = new JMenuItem("Mover pra cá");
                mover.addActionListener(e -> moverPersonagem(p, pos));
                add(mover);
            } else {
                JMenuItem adicionar = new JMenuItem("Adicionar personagem aqui");
                adicionar.addActionListener(e -> adicionarPersonagem(pos));
                add(adicionar);
            }
        }
    }
}

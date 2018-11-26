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

    /**
     *
     * @param m
     */
    public VisualizacaoMapa(Mapa m) {
        mapa = m;
    }

    public JPanel renderizar() {
        GridLayout experimentLayout = new GridLayout(mapa.retornarLargura(), mapa.retornarAltura());

        JPanel panel = new JPanel();
        panel.setLayout(experimentLayout);

        for (int i = 0; i < mapa.retornarLargura(); i++) {
            for (int j = 0; j < mapa.retornarAltura(); j++) {
                Posicao pos = mapa.retornarPosicao(i, j);
                Personagem personagem = pos.retornaOcupante();

                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

                if (personagem != null) {
                    label.setText(personagem.retornaNome());
                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                label.setName(i + "," + j);
                label.addMouseListener(new MouseAdapter () {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if (e.isPopupTrigger()) {
                            doPop(e);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        JLabel destino = (JLabel) panel.getComponentAt(e.getPoint());

                        // TODO FIXME
                        if (personagem != null && destino != null) {
                            String[] coordDestino = destino.getName().split(",");
                            Posicao posDestino = mapa.retornarPosicao(Integer.parseInt(coordDestino[0]), Integer.parseInt(coordDestino[1]));

                            if (posDestino != null) {
//                                personagemMovido(personagem, destino);
                            }
                        }
                    }

                    private void doPop(MouseEvent e){
                        PopupMenu menu = new PopupMenu();
                        menu.show(e.getComponent(), e.getX(), e.getY());

                        // TODO só mostrar quando posicao está ocupada
                    }
                });

                panel.add(label);
            }
        }

        return panel;
    }

    public void personagemAtacado() { }

    public void personagemCurado() { }

    public void personagemMovido() { }


    private class PopupMenu extends JPopupMenu {
        PopupMenu() {
            JMenuItem atacar = new JMenuItem("Atacar");
            atacar.addActionListener(e -> personagemAtacado());
            add(atacar);

            JMenuItem curar = new JMenuItem("Curar");
            curar.addActionListener(e -> personagemCurado());
            add(curar);
        }
    }
}
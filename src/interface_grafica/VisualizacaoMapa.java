package interface_grafica;

import modelos.Mapa;
import modelos.Personagem;
import modelos.Posicao;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VisualizacaoMapa extends JPanel {

    private Mapa mapa;

    /**
     *
     * @param m
     */
    VisualizacaoMapa(Mapa m) {
        mapa = m;
    }

    JPanel renderizar() {
        GridLayout experimentLayout = new GridLayout(mapa.retornarLargura(), mapa.retornarAltura());

        JPanel panel = new JPanel();
        panel.setLayout(experimentLayout);

        for (int i = 0; i < mapa.retornarLargura(); i++) {
            for (int j = 0; j < mapa.retornarAltura(); j++) {
                Posicao pos = mapa.retornarPosicao(i, j);

                JLabel label = new JLabel();
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

                if (!pos.retornaDisponivel()) {
                    Personagem p = pos.retornaOcupante();
                    label.setText(p.retornaNome());
                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }

                // int x = i;
                // int y = j;

                label.addMouseListener(new MouseAdapter () {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if (e.isPopupTrigger()) {
                            doPop(e);
                            return;
                        }

                        personagemMovido();
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
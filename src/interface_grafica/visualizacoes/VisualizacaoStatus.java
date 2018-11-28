package interface_grafica.visualizacoes;

import interface_grafica.Visualizacao;
import modelos.Partida;

import javax.swing.*;

public class VisualizacaoStatus extends Visualizacao {
    private Partida partida;
    private JPanel panel;

    public VisualizacaoStatus(Partida p) {
        partida = p;

        System.out.println("Iniciada: " + partida.retornarIniciada());
        System.out.println("Preparando: " + partida.retornarEmPreparacao());
        System.out.println("Aguardando iniciativas: " + partida.retornarAguardandoIniciativas());
    }

    @Override
    public JPanel renderizar() {
        return panel;
    }
}

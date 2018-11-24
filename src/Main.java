import comunicacao.Rede;
import interface_grafica.Controlador;
import modelos.Partida;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
            throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        int nJogadores = args.length == 0 ? 1 : 2;

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Rede rede = new Rede(nJogadores);
        Partida partida = new Partida();
        Controlador controlador = new Controlador(partida);

        new Barramento(partida, controlador, rede);

        controlador.iniciar();
    }
}

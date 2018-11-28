package interface_grafica;

import javax.swing.*;

class JanelaAtaque extends Janela {

    JanelaAtaque(Controlador c) {
        super(c);
    }

    @Override
    protected JPanel renderizar() {
        return null;
    }

    @Override
    protected JMenuBar renderizarMenu () {
        return null;
    }
}
package interface_grafica;

import javax.swing.*;

class JanelaCura extends Janela {
    JanelaCura(Controlador c) {
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
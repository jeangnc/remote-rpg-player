package interface_grafica;

import javax.swing.*;

class JanelaPartidaFinalizada extends Janela {
    JanelaPartidaFinalizada(Controlador c) {
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
package modelos;

public class Posicao {

    private Personagem ocupante;
    private Mapa tabuleiro;
    private int coordenada_x;
    private int coordenada_y;

    Posicao(int x, int y) {
        coordenada_x = x;
        coordenada_y = y;
    }

    public boolean verificarDisponibilidade() {
        // TODO - implement Posicao.verificarDisponibilidade
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ocupante
     */
    public void definirOcupante(Personagem ocupante) {
        // TODO - implement Posicao.definirOcupante
        throw new UnsupportedOperationException();
    }

    public void removerOcupante() {
        // TODO - implement Posicao.removerOcupante
        throw new UnsupportedOperationException();
    }

}
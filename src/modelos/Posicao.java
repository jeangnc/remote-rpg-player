package modelos;

public class Posicao {

    private int coordX;
    private int coordY;
    private Personagem ocupante = null;

    Posicao(int x, int y) {
        coordX = x;
        coordY = y;
    }

    /**
     *
     * @return
     */
    public int retornarCoordenadaX() {
        return coordX;
    }

    /**
     *
     * @return
     */
    public int retornarCoordenadaY() {
        return coordY;
    }

    /**
     *
     * @return
     */
    public boolean retornarDisponivel() {
        return ocupante == null;
    }

    /**
     *
     * @return
     */
    public Personagem retornarOcupante() {
        return ocupante;
    }

    /**
     *
     * @param p
     */
    void definirOcupante(Personagem p) {
        ocupante = p;
    }

    /**
     *
     */
    void removerOcupante() {
        ocupante = null;
    }
}